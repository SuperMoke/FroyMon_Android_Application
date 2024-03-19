package com.froyo.froymon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;


public class userAdapter extends FirebaseRecyclerAdapter<user, userAdapter.MyViewHolder> {


    private List<user> userList;

    public userAdapter(@NonNull FirebaseRecyclerOptions<user> option) {
        super(option);
        this.userList = new ArrayList<>();
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull user model) {
        holder.studentname.setText(model.getStudentname());
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_participants, parent, false);
        return new MyViewHolder(v);
    }


    public List<user> getCurrentList() {
        return userList;
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
        userList.clear();
        for (int i = 0; i < getItemCount(); i++) {
            userList.add(getItem(i));
        }
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView studentname;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            studentname = itemView.findViewById(R.id.tvstudentname);
        }
    }




}

