package com.example.prayaas.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prayaas.Model.Student;
import com.example.prayaas.R;
import com.example.prayaas.ShowStudentActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

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

          holder.class_name.setText(student.getGrade());
          holder.center_name.setText(student.getCenter());
        String ss =student.getProfilepic();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("profile pictures").child("studentPhoto").child(student.getPhone());

       storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
           @Override
           public void onSuccess(Uri uri) {
               Log.d("data","Success");
               Picasso.get().load(uri).placeholder(R.drawable.profile).into(holder.pp);
           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Log.d("data","Failure");
           }
       });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ShowStudentActivity.class);
             //   Student stu=student;
             intent.putExtra("name", student.getName());
             intent.putExtra("class", student.getClass());
             intent.putExtra("father", student.getFather_name());
             intent.putExtra("center", student.getCenter());
             intent.putExtra("mobile", student.getPhone());
             intent.putExtra("age", student.getAge());
             intent.putExtra("teacher", student.getTeacher());
             intent.putExtra("profile", student.getProfilepic());
             context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,class_name,center_name;
        ImageView pp;
        LinearLayout layout;
        View vv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.layout);
            name=itemView.findViewById(R.id.name_id);
            pp=itemView.findViewById(R.id.profie_img_id);
            class_name=itemView.findViewById(R.id.class_name_id);
            center_name=itemView.findViewById(R.id.center_name_id);
            vv=itemView;

        }
    }
}
