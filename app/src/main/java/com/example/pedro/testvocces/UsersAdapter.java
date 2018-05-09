package com.example.pedro.testvocces;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pedro.testvocces.model.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private List<User> users;
    private Context context;

    class MyViewHolder extends RecyclerView.ViewHolder {

        private User dato;
        private ImageView ivPicture;
        private TextView tvTitle;
        private TextView tvName;
        private ImageView ivGender;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.ivPicture);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvName = itemView.findViewById(R.id.tvName);
            ivGender = itemView.findViewById(R.id.ivGender);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, UserDetailActivity.class);
                    dato.setVisits(dato.getVisits()+1);
                    intent.putExtra("user", dato);
                    context.startActivity(intent);
                }
            });
        }

        public void bind(User dato) {
            this.dato = dato;
            Glide.with(context).load(dato.getPicture().getLarge()).into(ivPicture);
            String title = dato.getName().getTitle().substring(0, 1).toUpperCase() + dato.getName().getTitle().substring(1);
            tvTitle.setText(title);
            String first = dato.getName().getFirst().substring(0, 1).toUpperCase() + dato.getName().getFirst().substring(1);
            String last = dato.getName().getLast().substring(0, 1).toUpperCase() + dato.getName().getLast().substring(1);
            tvName.setText(first + " " + last);

            if("male".equals(dato.getGender())){
                ivGender.setImageResource(R.drawable.gender_male);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ivGender.setColorFilter(context.getColor(R.color.male));
                } else {
                    ivGender.setColorFilter(context.getResources().getColor(R.color.male));
                }
            } else {
                ivGender.setImageResource(R.drawable.gender_female);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ivGender.setColorFilter(context.getColor(R.color.female));
                } else {
                    ivGender.setColorFilter(context.getResources().getColor(R.color.female));
                }
            }
        }


    }

    public UsersAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public UsersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_adapter, parent, false);

        return new UsersAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final UsersAdapter.MyViewHolder holder, final int position) {
        final User dato = users.get(position);
        holder.bind(dato);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}