package com.froyo.froymon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ComputerAdapter extends FirebaseRecyclerAdapter<Computer, ComputerAdapter.ComputerViewHolder> {

    public ComputerAdapter(@NonNull FirebaseRecyclerOptions<Computer> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ComputerViewHolder holder, int position, @NonNull Computer model) {
        holder.computerNumberTextView.setText(model.getComputernumber());
        holder.computerStatusTextView.setText(model.getComputerstatus());
    }

    @NonNull
    @Override
    public ComputerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_computer_details, parent, false);
        return new ComputerViewHolder(view);
    }

    public static class ComputerViewHolder extends RecyclerView.ViewHolder {
        TextView computerNumberTextView;
        TextView computerStatusTextView;

        public ComputerViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views from the item layout
            computerNumberTextView = itemView.findViewById(R.id.tvcomputername);
            computerStatusTextView = itemView.findViewById(R.id.tvcomputerstatus);
        }
    }
}

