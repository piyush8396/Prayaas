package com.example.prayaas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.prayaas.Model.User;
import com.example.prayaas.Model.UserAdmin;
import com.example.prayaas.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {
   ActivitySignUpBinding binding;
   String name,email,pass,phone;
   FirebaseAuth auth;
   FirebaseDatabase database;
   FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=binding.txtName.getText().toString();
                email=binding.txtEmail.getText().toString();
                pass=binding.txtPassword.getText().toString();
                phone=binding.txtPhoneNumber.getText().toString();
                if(name.isEmpty()||email.isEmpty()||pass.isEmpty())
                {
                    Toast.makeText(SignUpActivity.this, "Fields Can't be Empty", Toast.LENGTH_SHORT).show();
                }
                else {


                    auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {


//                                database.getReference().child("Admin").child(email).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
//                                        if(task.isSuccessful())
//                                        {
//                                            DataSnapshot documentSnapshot=task.getResult();
//                                            if(documentSnapshot.exists())
//                                            {
//                                                UserAdmin userAdmin=new UserAdmin(name,auth.getCurrentUser().getUid(),email,pass,phone,true);
//                                                database.getReference().child("UserAdmin").child(email).setValue(userAdmin);
//
//
//                                                Intent intent= new Intent(SignUpActivity.this,AdminProfileActivity.class);
//                                                intent.putExtra("name",name);
//                                                intent.putExtra("mail",email);
//                                                intent.putExtra("pass",pass);
//                                                startActivity(intent);
//                                                finish();
//
//                                            }
//                                            else {
//                                                startActivity(new Intent(SignUpActivity.this,DashActivity.class));
//                                                finish();
//                                                User user=new User(name,email,pass,phone);
//                                                fstore.collection("User").document(email).set(user);
//
//                                            }
//                                        }
//                                    }
//                                });




                                fstore.collection("Admin").document(email).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if(task.isSuccessful())
                                        {
                                            DocumentSnapshot documentSnapshot=task.getResult();
                                            if(documentSnapshot.exists())
                                            {

                                                UserAdmin userAdmin=new UserAdmin(name,auth.getCurrentUser().getUid(),email,pass,phone,true);
                                                fstore.collection("UserAdmin").document(email).set(userAdmin);
                                               Intent intent= new Intent(SignUpActivity.this,AdminProfileActivity.class);
                                               intent.putExtra("name",name);
                                               intent.putExtra("mail",email);
                                               intent.putExtra("pass",pass);
                                                startActivity(intent);
                                                finish();


                                            }
                                            else {
                                                startActivity(new Intent(SignUpActivity.this,DashActivity.class));
                                                finish();
                                                User user=new User(name,email,pass,phone);
                                                fstore.collection("User").document(email).set(user);

                                            }
                                        }


                                    }
                                });


                            }
                            else {
                                Toast.makeText(SignUpActivity.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



                }
            }
        });
        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                finish();
            }
        });



    }
}