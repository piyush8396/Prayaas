package com.example.prayaas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.prayaas.databinding.ActivityDashBinding;
import com.google.firebase.auth.FirebaseAuth;

public class DashActivity extends AppCompatActivity {
    ActivityDashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.cardStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashActivity.this,ClassActivity.class));
            }
        });
       binding.cardView2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(FirebaseAuth.getInstance().getCurrentUser()!=null)
               {
                   FirebaseAuth.getInstance().signOut();

               }
               startActivity(new Intent(DashActivity.this,MainActivity.class));
               finish();

           }
       });

    }
}