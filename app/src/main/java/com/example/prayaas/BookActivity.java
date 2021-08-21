package com.example.prayaas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.prayaas.Adapter.bookAdapter;
import com.example.prayaas.Model.Student;
import com.example.prayaas.Model.bookclass;
import com.example.prayaas.databinding.ActivityBookBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    private RecyclerView courseRV;
   FirebaseFirestore fstore;
   FirebaseStorage storage;
   ActivityBookBinding binding;
  Uri path;
    ProgressDialog progressDialog;
  int no;
     ArrayList<bookclass> courseModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBookBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        Intent intent=getIntent();
        storage=FirebaseStorage.getInstance();
        no=intent.getIntExtra("class",1);

        fstore=FirebaseFirestore.getInstance();
        courseRV=findViewById(R.id.book_recycler_view);
        courseModelArrayList = new ArrayList<>();
        bookAdapter courseAdapter = new bookAdapter(this, courseModelArrayList);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(courseAdapter);

         progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Uploading File..");
        progressDialog.setTitle("Please wait ");
        progressDialog.setCancelable(false);
         binding.addBookFab.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Intent intent = new Intent();
                 intent.setType("application/pdf");
                 intent.setAction(Intent.ACTION_GET_CONTENT);
                 startActivityForResult(Intent.createChooser(intent, "Select PDF"), 1);



             }
         });


        fstore.collection("Books").whereEqualTo("grade", ""+no).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    binding.totalCount.setText(task.getResult().size()+"\n Books");
                    for(QueryDocumentSnapshot document:task.getResult())
                    {

                        try{
                            bookclass s= document.toObject(bookclass.class);
                            courseModelArrayList.add(s);
                            courseAdapter.notifyDataSetChanged();
                        }
                        catch (Exception e) {
                            Log.d("getlost",e.getMessage());
                            e.printStackTrace();
                        }
                    }

                }
                else {
                    Toast.makeText(BookActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(BookActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }


          @Override
        public void onActivityResult(int requestCode, int resultCode, Intent result) {
              super.onActivityResult(requestCode, resultCode, result);
              progressDialog.show();
              if (resultCode == RESULT_OK) {
                  if (requestCode == 1) {
                      Toast.makeText(this, "Enter", Toast.LENGTH_SHORT).show();
                      Uri uri = result.getData();
                      String uriString = uri.toString();
                      File myFile = new File(uriString);
                      String path = myFile.getAbsolutePath();
                      String displayName = null;

                      if (uriString.startsWith("content://")) {
                          Cursor cursor = null;
                          try {
                              cursor = getApplicationContext().getContentResolver().query(uri, null, null, null, null);
                              if (cursor != null && cursor.moveToFirst()) {
                                  displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                              }
                          } finally {
                              cursor.close();
                          }
                      } else if (uriString.startsWith("file://")) {
                          displayName = myFile.getName();
                      }

              bookclass book =new bookclass( displayName,""+no);
                      fstore.collection("Books").document(displayName).set(book).addOnSuccessListener(new OnSuccessListener<Void>() {
                          @Override
                          public void onSuccess(Void aVoid) {


                              Toast.makeText(BookActivity.this, "Uplaoded", Toast.LENGTH_SHORT).show();
                          }
                      }).addOnFailureListener(new OnFailureListener() {
                          @Override
                          public void onFailure(@NonNull Exception e) {
                              Toast.makeText(BookActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                          }
                      });

                      storage.getReference().child("Books").child(displayName).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                          @Override
                          public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                              Toast.makeText(BookActivity.this, "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                          }
                      });
                  }


              }
           progressDialog.dismiss();
    }
}