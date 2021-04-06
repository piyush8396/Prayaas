package com.example.prayaas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.prayaas.Adapter.classAdapter;
import com.example.prayaas.Model.Classs;

import java.util.ArrayList;

public class ClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        ArrayList<Classs> myListData = new ArrayList<>();
        myListData.add(new Classs("0",0));
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
        classAdapter adapter = new classAdapter(myListData,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}