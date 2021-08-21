package com.example.prayaas.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prayaas.BookActivity;
import com.example.prayaas.Model.Classs;
import com.example.prayaas.R;
import com.example.prayaas.StudentActivity;

import java.util.ArrayList;

public class classAdapter extends RecyclerView.Adapter<classAdapter.ViewHolder>{
    @NonNull


    ArrayList<Classs> list;
    Context context;
    Boolean tr;

    public classAdapter(@NonNull ArrayList<Classs> list, Context context,Boolean tr) {
        this.list = list;
        this.context = context;
        this.tr=tr;
    }

    @Override
    public classAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_class_view,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull classAdapter.ViewHolder holder, int position) {
        final Classs users=list.get(position);
        holder.name.setText(users.getName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cc=users.getNo();
                Intent intent;
                if(tr) {
                     intent=new Intent(context, StudentActivity.class);
                } else {
                     intent=new Intent(context, BookActivity.class);
                }
                intent.putExtra("class",cc);
                context.startActivity(intent);
            }
        });





    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
              layout=itemView.findViewById(R.id.number);
            name=itemView.findViewById(R.id.namee_id);

        }
    }
}
