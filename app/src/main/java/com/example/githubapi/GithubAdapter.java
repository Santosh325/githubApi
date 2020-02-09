package com.example.githubapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.GithubViewHolder>{


        Context mContext;
        List<Githubmodel> mEmployeModels;

        public GithubAdapter(Context context, List<Githubmodel> employelist) {
            this.mContext = context;
            this.mEmployeModels = employelist;
        }


        @NonNull
        @Override
        public GithubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.item, parent, false);
            return new GithubViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull GithubViewHolder holder, int position) {
            final Githubmodel data = mEmployeModels.get(position);
            holder.name.setText(data.getLogin());

            Picasso.get().load(data.getAvatarUrl()).into(holder.profilepic);

//
        }

        @Override
        public int getItemCount() {
            return mEmployeModels.size();
        }

        public class GithubViewHolder extends RecyclerView.ViewHolder {

            CircleImageView profilepic;
            TextView name;

            public GithubViewHolder(@NonNull View itemView) {
                super(itemView);
                profilepic = itemView.findViewById(R.id.image);
                name = itemView.findViewById(R.id.name);

            }
        }
}
