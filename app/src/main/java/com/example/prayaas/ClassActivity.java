package com.example.prayaas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.example.prayaas.Adapter.classAdapter;
import com.example.prayaas.Model.Classs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ClassActivity extends AppCompatActivity {
     TextView view;
     FirebaseFirestore fstore;

     Boolean tr=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        String ss=getIntent().getStringExtra("from");
        if(ss.equals("student"))
            tr=true;
        fstore=FirebaseFirestore.getInstance();
        view=findViewById(R.id.total_student);


        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Fetching Data......");
        progressDialog.setTitle("Please wait ");
        progressDialog.setCancelable(false);
        progressDialog.show();
        if(tr){
        fstore.collection("Students").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    view.setText(task.getResult().size()+" \n Students ");
                }
                progressDialog.dismiss();
            }
        });}
        else {
            fstore.collection("Books").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful())
                    {
                        view.setText(task.getResult().size()+" \n Books ");
                    }
                    progressDialog.dismiss();
                }
            });



        }
        ArrayList<Classs> myListData = new ArrayList<>();
        myListData.add(new Classs("Primary",0));
        myListData.add(new Classs("1st Class",1));
        myListData.add(new Classs("2nd Class",2));
        myListData.add(new Classs("3rd Class",3));
        myListData.add(new Classs("4th Class",4));
        myListData.add(new Classs("5th Class",5));
        myListData.add(new Classs("6th Class",6));
        myListData.add(new Classs("7th Class",7));
        myListData.add(new Classs("8th Class",8));
        myListData.add(new Classs("9th Class",9));
        myListData.add(new Classs("10th Class",10));
        myListData.add(new Classs("11th Class",11));
        myListData.add(new Classs("12th Class",12));



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.class_recycler_view);
        classAdapter adapter = new classAdapter(myListData,this,tr);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}