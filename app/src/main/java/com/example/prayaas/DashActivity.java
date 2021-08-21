package com.example.prayaas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.prayaas.databinding.ActivityDashBinding;
import com.google.firebase.auth.FirebaseAuth;

public class DashActivity extends AppCompatActivity {
    ActivityDashBinding binding;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        binding.headerTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(auth.getCurrentUser()!=null)
                {
                    auth.signOut();
                    startActivity(new Intent(DashActivity.this,MainActivity.class));
                }
            }
        });

        binding.cardStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashActivity.this,ClassActivity.class);
                intent.putExtra("from","student");
                startActivity(intent);
            }
        });
          binding.donate.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  startActivity(new Intent(DashActivity.this,DonateActivity.class));
              }
          });
        binding.cardVolunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashActivity.this,VolunteerActivity.class));

            }
        });
        binding.bookView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(DashActivity.this,ClassActivity.class);
                intent2.putExtra("from","book");
                startActivity(intent2);

            }
        });



    }
}