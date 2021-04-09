package com.example.prayaas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.prayaas.Model.Student;
import com.example.prayaas.databinding.ActivityAddStudentBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class AddStudentActivity extends AppCompatActivity {
    ActivityAddStudentBinding binding;
    String name = null, center = null, profilepic = null, phone = null, address = null, father_name = null, mother_name = null, father_number = null, teacher = null, gradee = null;
    FirebaseFirestore fstore;
    String age;
    FirebaseStorage storage;
    Uri uri, cropuri=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fstore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        String[] center_type = new String[]{"Amanakpur", "Bidhipur", "NIT", "Other"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_menu_popup_item,
                        center_type);

        AutoCompleteTextView editTextFilledExposedDropdown =
                findViewById(R.id.filled_exposed_dropdown);
        editTextFilledExposedDropdown.setAdapter(adapter);


        String[] gender_type = new String[]{"Male", "Female", "other"};
        ArrayAdapter<String> adapter1 =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_menu_popup_item,
                        gender_type);
        binding.txtGender.setAdapter(adapter1);


        String[] grade_type = new String[]{"Below 1", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        ArrayAdapter<String> adapter2 =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_menu_popup_item,
                        grade_type);
        binding.txtClass.setAdapter(adapter2);


        binding.profieImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.startPickImageActivity(AddStudentActivity.this);
            }
        });

        binding.btnSignup.setOnClickListener(view -> {
            name = binding.txtName.getText().toString();
            center = binding.filledExposedDropdown.getText().toString();
            age = binding.txtAge.getText().toString();
            phone = binding.txtPhone.getText().toString();
            address = binding.txtAddress.getText().toString();
            father_name = binding.txtFather.getText().toString();
            mother_name = binding.txtMother.getText().toString();
            father_number = binding.txtFatherNumber.getText().toString();
            teacher = binding.txtTeacher.getText().toString();
            gradee = binding.txtClass.getText().toString();

            if (phone.isEmpty() || center.isEmpty() || name.isEmpty() || gradee.isEmpty()) {
                Toast.makeText(AddStudentActivity.this, "Please Fill Madatory Fields", Toast.LENGTH_SHORT).show();
            } else {
                final StorageReference reference = storage.getReference().child("profile pictures")
                        .child("studentPhoto").child(phone);

                reference.putFile(cropuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    }
                });
                Student student = new Student(name, center, profilepic, phone, address, father_name, mother_name, father_number, teacher, (gradee), (age));
                fstore.collection("Students").document(phone).set(student);
                Log.d("data","After");

            }


        });


    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data.getData() != null) {
                Uri sFile = CropImage.getPickImageResultUri(this, data);
                if (CropImage.isReadExternalStoragePermissionsRequired(this, sFile)) {
                    uri = sFile;
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);


                } else {
                    startCrop(sFile);
                }

            }
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                cropuri = result.getUri();
                binding.profieImg.setImageURI(cropuri);

            } else {
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