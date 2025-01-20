package com.mahakumbh.dishanirdesh.adapter;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mahakumbh.dishanirdesh.R;
import com.mahakumbh.dishanirdesh.fragment.More.FAQFragment;
import com.mahakumbh.dishanirdesh.models.FAQModel;

import java.util.List;

public class FAQListAdapter extends RecyclerView.Adapter<FAQListAdapter.ViewHolder>{

    private final List<FAQModel> faqModelList;
    public final Activity activity;
    public final FAQFragment faqFragment;
    public FAQListAdapter(Activity activity,FAQFragment faqFragment,List<FAQModel> faqModelList) {
        this.activity=activity;
        this.faqModelList = faqModelList;
        this.faqFragment=faqFragment;
    }

    @NonNull
    @Override
    public FAQListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_announcement, parent, false);
        return new FAQListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQListAdapter.ViewHolder holder, int position) {
        holder.bindData(faqModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return faqModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView tv_details;
        private final ImageView iv_openDetails;
        private final LinearLayout ll_details;
        private final Button btn_action;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title= itemView.findViewById(R.id.title);
            tv_details= itemView.findViewById(R.id.tv_details);
            btn_action= itemView.findViewById(R.id.btn_action);
            iv_openDetails= itemView.findViewById(R.id.iv_openDetails);
            ll_details= itemView.findViewById(R.id.ll_details);
        }

        public void bindData(FAQModel model){
            title.setText(model.getTitle());
            tv_details.setText(model.getDetails());
            btn_action.setVisibility(GONE);
            if(model.getBtn()!=0){
                btn_action.setVisibility(VISIBLE);
                btn_action.setText(model.getBtn());
                btn_action.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        faqFragment.openFragment(model.getFragment());
                    }
                });
            }

            iv_openDetails.setOnClickListener(v -> toggleDetails());
            itemView.setOnClickListener(v -> toggleDetails());




        }

        @SuppressLint("UseCompatLoadingForDrawables")
        private void toggleDetails(){
            if(ll_details.getVisibility()== GONE) {
                ll_details.setVisibility(VISIBLE);
                iv_openDetails.setImageDrawable(activity.getDrawable(R.drawable.arrow_up));
                title.setSingleLine(false);
            }
            else {
                ll_details.setVisibility(GONE);
                title.setSingleLine(true);
                iv_openDetails.setImageDrawable(activity.getDrawable(R.drawable.arrow_down));
            }
        }
    }
}
