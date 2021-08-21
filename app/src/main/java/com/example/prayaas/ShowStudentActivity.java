package com.example.prayaas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.prayaas.Model.Student;
import com.example.prayaas.databinding.ActivityShowStudentBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ShowStudentActivity extends AppCompatActivity {
     ActivityShowStudentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityShowStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent i = getIntent();

       binding.name.setText("Name :"+ i.getStringExtra("name"));
      binding.classTxt.setText("Class :"+i.getStringExtra("class"));
      binding.fatherTxt.setText("Father Name"+i.getStringExtra("father"));
      binding.centerTxt.setText("Center :"+i.getStringExtra("center"));
      binding.MobileTxt.setText("Mobile No. :"+i.getStringExtra("mobile"));
      binding.ageTxt.setText("Age :"+i.getStringExtra("age"));
      binding.teacherTxt.setText("Teacher :"+i.getStringExtra("teacher"));
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("profile pictures").child("studentPhoto").child(i.getStringExtra("mobile"));

        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d("data","Success");
                Picasso.get().load(uri).placeholder(R.drawable.profile).into(binding.profieImg);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("data","Failure");
            }
        });
    }
}