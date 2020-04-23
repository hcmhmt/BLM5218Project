package com.hcmhmt.blm5218project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {

    ArrayList<Users> mUserList;
    LayoutInflater inflater;

    public UserListAdapter(Context context, ArrayList<Users> users) {
        inflater = LayoutInflater.from(context);
        this.mUserList = users;
    }

    @Override
    public UserListAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.onerow_user, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder( UserListAdapter.MyViewHolder holder, int position) {
        Users selectedUser = mUserList.get(position);
        holder.setData(selectedUser, position);
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView username, password;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            username = (TextView)itemView.findViewById(R.id.tw_onerow_username);
            password = (TextView)itemView.findViewById(R.id.tw_onerow_password);
            imageView = (ImageView) itemView.findViewById(R.id.iv_onerow_image);
        }

        public void setData(Users selectedUser,int position){
            this.username.setText(selectedUser.getUsername());
            this.password.setText(selectedUser.getPassword());
            this.imageView.setImageResource(selectedUser.getImage());
        }
    }
}
