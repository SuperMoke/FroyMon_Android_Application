package com.froyo.froymon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class LaboratoryAdapter extends FirebaseRecyclerAdapter<laboratory, LaboratoryAdapter.MyViewHolder> {

    private OnItemClickListener onItemClickListener;
    public LaboratoryAdapter(@NonNull FirebaseRecyclerOptions<laboratory> options, OnItemClickListener onItemClickListener) {
        super(options);
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull laboratory model) {
        holder.labname.setText(model.getLabName());
        holder.labid.setText(model.getLabId());
        holder.itemView.setOnClickListener(view -> {
            if(onItemClickListener != null){
                onItemClickListener.onItemClick(model.getId() , model.getLabId());
            }
        });
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.laboratory_card, parent, false);
        return new MyViewHolder(v);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView labid, labname;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            labid = itemView.findViewById(R.id.labidtf);
            labname = itemView.findViewById(R.id.labnametf);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(String id, String labid);
    }
}
