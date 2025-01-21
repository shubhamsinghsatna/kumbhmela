package com.mahakumbh.dishanirdesh.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mahakumbh.dishanirdesh.R;
import com.mahakumbh.dishanirdesh.activity.LocationDetailsActivity;
import com.mahakumbh.dishanirdesh.models.EntityLocationModel;

import java.util.List;

public class EntitiesLocationListAdapter extends RecyclerView.Adapter<EntitiesLocationListAdapter.ViewHolder> {

    private final List<EntityLocationModel> entityLocationModels;
    public final Activity activity;

    public EntitiesLocationListAdapter(Activity activity, List<EntityLocationModel> entityLocationModels) {
        this.activity = activity;
        this.entityLocationModels = entityLocationModels;
    }

    @NonNull
    @Override
    public EntitiesLocationListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_entities_location, parent, false);
        return new EntitiesLocationListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntitiesLocationListAdapter.ViewHolder holder, int position) {
        holder.bindData(entityLocationModels.get(position));
    }

    @Override
    public int getItemCount() {
        return entityLocationModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final ImageView iv_details;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            iv_details = itemView.findViewById(R.id.iv_details);


        }

        public void bindData(EntityLocationModel model) {
            title.setText(model.getTitle());

            iv_details.setOnClickListener(v->showFullDetails(model));
            itemView.setOnClickListener(v->showFullDetails(model));


        }

        private void showFullDetails(EntityLocationModel model) {
            Intent intent = new Intent(activity, LocationDetailsActivity.class);
            intent.putExtra("currentLocation", model);
            activity.startActivity(intent);

        }


    }
}
