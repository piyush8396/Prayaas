package com.example.prayaas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.prayaas.Adapter.studentAdapter;
import com.example.prayaas.Adapter.userAdminAdapter;
import com.example.prayaas.Model.Student;
import com.example.prayaas.Model.UserAdmin;
import com.example.prayaas.databinding.ActivityVolunteerBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class VolunteerActivity extends AppCompatActivity {
    ActivityVolunteerBinding binding;
    FirebaseFirestore fstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

  binding=ActivityVolunteerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
          fstore=FirebaseFirestore.getInstance();


        ArrayList<UserAdmin> myListData = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.volunteer_recycler_view);
        userAdminAdapter adapter = new userAdminAdapter(myListData,this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Fetching Data......");
        progressDialog.setTitle("Please wait ");
        progressDialog.setCancelable(false);
        progressDialog.show();
        fstore.collection("UserAdmin").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    binding.totalCount.setText(task.getResult().size()+" \n Volunteers ");
                    for(QueryDocumentSnapshot document:task.getResult())
                    {

                        try{
                            UserAdmin s= document.toObject(UserAdmin.class);
                            myListData.add(s);
                            adapter.notifyDataSetChanged();}
                        catch (Exception e) {
                            Log.d("ss",e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
                else {
                    Toast.makeText(VolunteerActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(VolunteerActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}