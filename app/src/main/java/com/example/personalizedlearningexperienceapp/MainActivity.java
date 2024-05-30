package com.example.personalizedlearningexperienceapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.personalizedlearningexperienceapp.api.ApiService;
import com.example.personalizedlearningexperienceapp.model.User;
import com.example.personalizedlearningexperienceapp.model.response.LoginResponse;
import com.example.personalizedlearningexperienceapp.utils.TinyDB;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ApiService apiService;
    TinyDB tinyDB ;
    String haveAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tinyDB = new TinyDB(this);
        haveAccount = tinyDB.getString("isUser").toString();
        EditText editTextUsername = findViewById(R.id.email_et);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        TextView textViewRegister = findViewById(R.id.textViewRegister);
        apiService = ApiClient.getInstance().getApi();


buttonLogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String email = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        loginNow(email,password);
    }
});

textViewRegister.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(MainActivity.this,CreateAccountActivity.class));
    }
});


    }

    private void loginNow(String email, String password) {
        Call<LoginResponse> call = apiService.loginUser(email, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        LoginResponse loginResponse = response.body();
                        Log.d("ResponseDebug", "Raw JSON Response: " + response.body().toString()); // Log the raw JSON response

                        if (loginResponse.getError() == 200) {

                            User user = loginResponse.getUser();
                            String message = loginResponse.getMessage();
                            Log.d("zainab123", "onResponse: " + message);
                            Toast.makeText(MainActivity.this, "login Successful", Toast.LENGTH_SHORT).show();

                            tinyDB.putString("isUser","yes");
                            tinyDB.putString("username",user.getUsername());
                            startActivity(new Intent(MainActivity.this, InterestsActivity.class));

                        } else {
                            // Handle API error, show error message
                            String errorMessage = loginResponse.getMessage();
                            Log.d("zainab123", "onResponse: " + errorMessage);
                        }
                    } else {
                        // Handle null response body
                        Log.d("zainab123", "onResponse: null response body");
                    }
                } else {
                    try {
                        Log.e("ResponseDebug", "Error response: " + response.errorBody().string()); // Log error response body
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("zainab123", "onResponse: "+e);

                    }
                    // Handle unsuccessful response (HTTP error)
                    Log.d("zainab123", "onResponse: http error");
                }
            }



            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Handle failure

                Log.d("zainab123", "onResponse: "+t.getCause());

            }
        });

    }
}