package com.example.prayaas.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prayaas.Model.UserAdmin;
import com.example.prayaas.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class userAdminAdapter extends RecyclerView.Adapter<userAdminAdapter.ViewHolder> {

   ArrayList<UserAdmin> userAdmins;
   Context context;

    public userAdminAdapter(ArrayList<UserAdmin> userAdmins, Context context) {
        this.userAdmins = userAdmins;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_useradmin,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       UserAdmin userAdmin=userAdmins.get(position);
       holder.name.setText(userAdmin.getUsername());
        Picasso.get().load(userAdmin.getProfilepic()).placeholder(R.drawable.profile).into(holder.pp);
        holder.center_name.setText(userAdmin.getCenter());
        holder.class_name.setText(userAdmin.getCurYear());

    }

    @Override
    public int getItemCount() {
        return userAdmins.size();
    }


    public class ViewHolder  extends RecyclerView.ViewHolder{
        TextView name,class_name,center_name;
        ImageView pp;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.layout);
            name=itemView.findViewById(R.id.name_id);
            pp=itemView.findViewById(R.id.profie_img_id);
            class_name=itemView.findViewById(R.id.class_name_id);
            center_name=itemView.findViewById(R.id.center_name_id);



        }
    }
}
