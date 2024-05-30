package com.example.personalizedlearningexperienceapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.personalizedlearningexperienceapp.api.ApiService;
import com.example.personalizedlearningexperienceapp.model.response.RegisterResponse;
import com.example.personalizedlearningexperienceapp.utils.TinyDB;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountActivity extends AppCompatActivity {
    private ApiService apiService;
    TinyDB tinyDB;
    private EditText editTextUsername, editTextEmail,
            editTextConfirmEmail, editTextPassword, editTextConfirmPassword, editTextPhoneNumber;

    private ImageView imageViewProfile;
    private static final int REQUEST_IMAGE_PICK = 1;

    String haveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        tinyDB = new TinyDB(this);
        haveAccount = tinyDB.getString("isUser").toString();

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmEmail = findViewById(R.id.editTextConfirmEmail);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        Button buttonCreateAccount = findViewById(R.id.buttonCreateAccount);
        imageViewProfile = findViewById(R.id.imageViewProfile);


        buttonCreateAccount.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String confirmEmail = editTextConfirmEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String confirmPassword = editTextConfirmPassword.getText().toString().trim();
            String phone = editTextPhoneNumber.getText().toString().trim();
            apiService = ApiClient.getInstance().getApi();
            Toast.makeText(this, "Please wait a second", Toast.LENGTH_SHORT).show();
            registerNewUser(username, email, password, phone, confirmEmail, confirmPassword);
        });

        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pickImageFromGallery();

            }
        });
    }


    private void pickImageFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_IMAGE_PICK);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK && data != null) {

            Uri imageUri = data.getData();
            if (imageUri != null) {
                try {

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    imageViewProfile.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void registerNewUser(String username, String email, String password, String phone, String confirmEmail, String confirmPassword) {

        if (username.isEmpty() || email.isEmpty() || confirmEmail.isEmpty() ||
                password.isEmpty() || confirmPassword.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!email.equals(confirmEmail)) {
            Toast.makeText(this, "Emails do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }


        Call<RegisterResponse> call = apiService.createUser(username, email, phone, password);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RegisterResponse apiResponse = response.body();

                    int error = apiResponse.getError();
                    String message = apiResponse.getMessage();
                    Log.d("zainab123", "onResponse: " + message);
                    tinyDB.putString("isUser", "yes");
                    tinyDB.putString("username", username);
                    startActivity(new Intent(CreateAccountActivity.this, InterestsActivity.class));
                } else {
                    Log.d("zainab123", "onResponse: " + response.errorBody());

                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                // Handle failure
                Log.d("zainab123", "onResponse: " + t.getCause());

            }
        });


    }
}
