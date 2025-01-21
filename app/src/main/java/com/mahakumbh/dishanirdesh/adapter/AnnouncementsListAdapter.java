package com.mahakumbh.dishanirdesh.adapter;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
import com.mahakumbh.dishanirdesh.models.AnnouncementModel;

import java.util.List;

public class AnnouncementsListAdapter extends RecyclerView.Adapter<AnnouncementsListAdapter.ViewHolder>{

    private final List<AnnouncementModel> announcementList;
    public final Activity activity;
    public AnnouncementsListAdapter(Activity activity,List<AnnouncementModel> announcementList) {
        this.activity=activity;
        this.announcementList = announcementList;
    }

    @NonNull
    @Override
    public AnnouncementsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_announcement, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementsListAdapter.ViewHolder holder, int position) {
      holder.bindData(announcementList.get(position));
    }

    @Override
    public int getItemCount() {
        return announcementList.size();
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

        public void bindData(AnnouncementModel model){
            title.setText(model.getTitle());
            tv_details.setText(model.getDetails());
            btn_action.setVisibility(GONE);
            if(model.getBtn()!=0){
                btn_action.setVisibility(VISIBLE);
                btn_action.setText(model.getBtn());
                btn_action.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(model.getLink()));

                        // Start the Intent
                        activity.startActivity(intent);
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
