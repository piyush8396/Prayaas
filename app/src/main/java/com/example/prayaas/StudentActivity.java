package com.example.prayaas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.prayaas.Adapter.classAdapter;
import com.example.prayaas.Adapter.studentAdapter;
import com.example.prayaas.Model.Classs;
import com.example.prayaas.Model.Student;
import com.example.prayaas.databinding.ActivityStudentBinding;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {
    ActivityStudentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=getIntent();
        int no=intent.getIntExtra("class",1);
        Toast.makeText(this, ""+no, Toast.LENGTH_SHORT).show();

      binding.addStudentFab.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(StudentActivity.this,AddStudentActivity.class));
          }
      });



        ArrayList<Student> myListData = new ArrayList<>();
        myListData.add(new Student("Piyush","Center",4,"123454612"));




        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.student_recycler_view);
        studentAdapter adapter = new studentAdapter(myListData,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);















    }
}