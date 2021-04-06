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

import com.example.prayaas.Model.Student;
import com.example.prayaas.R;

import java.util.ArrayList;

public class studentAdapter extends RecyclerView.Adapter<studentAdapter.ViewHolder>{
    ArrayList<Student> students;
    Context context;

    public studentAdapter(ArrayList<Student> students, Context context) {
        this.students = students;
        this.context = context;
    }

    @NonNull
    @Override
    public studentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_student_view,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull studentAdapter.ViewHolder holder, int position) {
          Student student=students.get(position);
          holder.name.setText(student.getName());

          holder.class_name.setText(""+student.getGrade());
          holder.center_name.setText(student.getCenter());

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,class_name,center_name;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.layout);
            name=itemView.findViewById(R.id.name_id);

            class_name=itemView.findViewById(R.id.class_name_id);
            center_name=itemView.findViewById(R.id.center_name_id);


        }
    }
}
