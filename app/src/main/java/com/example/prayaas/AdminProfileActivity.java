package com.example.prayaas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.prayaas.Model.UserAdmin;
import com.example.prayaas.databinding.ActivityAdminProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;
import java.util.Map;

public class AdminProfileActivity extends AppCompatActivity {
    ActivityAdminProfileBinding binding;
    String phone, branch, center, year, profilepic = null;
    FirebaseFirestore fstore;
    FirebaseStorage storage;
    FirebaseAuth auth;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] type = new String[] {"1st Year", "2nd Year", "3rd Year", "4th Year","Other"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_menu_popup_item,
                        type);

        AutoCompleteTextView editTextFilledExposedDropdown =
                findViewById(R.id.filled_exposed_dropdown);
        editTextFilledExposedDropdown.setAdapter(adapter);
        String name, mail, password;
        name = getIntent().getStringExtra("name");
        mail = getIntent().getStringExtra("mail");
        password = getIntent().getStringExtra("pass");
        fstore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();





        binding.profieImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.startPickImageActivity(AdminProfileActivity.this);
            }
        });

        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = binding.txtPhone.getText().toString();
                branch = binding.txtBranch.getText().toString();
                center = binding.txtCenter.getText().toString();
                year =editTextFilledExposedDropdown.getText().toString();
                if (phone.isEmpty() || branch.isEmpty() || center.isEmpty() || year.isEmpty()) {
                    Toast.makeText(AdminProfileActivity.this, "Enter Valid Information", Toast.LENGTH_SHORT).show();
                } else {

//                    UserAdmin userAdmin=new UserAdmin(profilepic,name, auth.getUid(), mail, password, phone,branch,center, year,true);
                    Map<String, Object> mp = new HashMap<>();
                    mp.put("profilepic", profilepic);
                    mp.put("branch", branch);
                    mp.put("center", center);
                    mp.put("curYear", year);
                    fstore.collection("UserAdmin").document(mail).update(mp).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(AdminProfileActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AdminProfileActivity.this, DashActivity.class));
                                finish();
                            }
                        }
                    });


                }
            }
        });


        binding.txtBranch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (binding.txtBranch.getText().toString().trim().length() < 1)
                        binding.txtBranch.setError("Enter Something");
                    else
                        binding.txtBranch.setError(null);
                }
            }
        });
        binding.txtPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (binding.txtPhone.getText().toString().trim().length() != 10)
                        binding.txtPhone.setError("Enter Valid Number");
                    else
                        binding.txtPhone.setError(null);
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data.getData() != null) {
                Uri sFile = CropImage.getPickImageResultUri(this, data);
                if (CropImage.isReadExternalStoragePermissionsRequired(this, sFile))
                {
                    uri = sFile;
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);


                }
            else {
                    startCrop(sFile);
                }
















            }
        }
      if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
      {
          CropImage.ActivityResult result=CropImage.getActivityResult(data);
          if(resultCode==RESULT_OK)
          {
              binding.profieImg.setImageURI(result.getUri());
              Toast.makeText(this, "Image Updated", Toast.LENGTH_SHORT).show();



              final StorageReference reference = storage.getReference().child("profile pictures")
                      .child(auth.getUid());
              reference.putFile(result.getUri()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                  @Override
                  public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                      reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                          @Override
                          public void onSuccess(Uri uri) {
                              profilepic=uri.toString();
                          }
                      });


                  }
              });





          }   else {
              Toast.makeText(this, "Failed! Try Again", Toast.LENGTH_SHORT).show();
          }
      }

    }

    private void startCrop(Uri imageuri) {
         CropImage.activity(imageuri)
                 .setGuidelines(CropImageView.Guidelines.ON)
                 .setMultiTouchEnabled(true)
                 .start(this);
    }

}