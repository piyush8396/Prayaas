package com.example.prayaas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prayaas.Adapter.classAdapter;
import com.example.prayaas.Adapter.studentAdapter;
import com.example.prayaas.Model.Classs;
import com.example.prayaas.Model.Student;
import com.example.prayaas.databinding.ActivityStudentBinding;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {
    ActivityStudentBinding binding;
    FirebaseFirestore fstore;



    ShimmerFrameLayout layout;
    ImageView imageView;
    TextView title,subtitle;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=getIntent();
        int no=intent.getIntExtra("class",1);
        fstore=FirebaseFirestore.getInstance();
      binding.addStudentFab.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(StudentActivity.this,AddStudentActivity.class));
          }
      });
        ArrayList<Student> myListData = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.student_recycler_view);
        studentAdapter adapter = new studentAdapter(myListData,this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Fetching Data......");
        progressDialog.setTitle("Please wait ");
        progressDialog.setCancelable(false);
        progressDialog.show();

      fstore.collection("Students").whereEqualTo("grade", ""+no).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
          @Override
          public void onComplete(@NonNull Task<QuerySnapshot> task) {
              if(task.isSuccessful())
              {
                            binding.totalCount.setText(task.getResult().size()+" \n Students ");
                  for(QueryDocumentSnapshot document:task.getResult())
                  {

                      try{
                      Student s= document.toObject(Student.class);
                      myListData.add(s);
                      adapter.notifyDataSetChanged();}
                      catch (Exception e) {
                         Log.d("ss",e.getMessage());
                          e.printStackTrace();
                      }
                  }
                  progressDialog.dismiss();
              }
              else {
                  Toast.makeText(StudentActivity.this, "Failed", Toast.LENGTH_SHORT).show();
              }
          }
      }).addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
              Toast.makeText(StudentActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
          }
      });

    }
}