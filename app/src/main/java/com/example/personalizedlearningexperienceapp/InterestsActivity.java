package com.example.personalizedlearningexperienceapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalizedlearningexperienceapp.api.ApiService;
import com.example.personalizedlearningexperienceapp.model.response.RegisterResponse;
import com.example.personalizedlearningexperienceapp.utils.TinyDB;

import java.util.HashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterestsActivity extends AppCompatActivity {
    private int userId;
    private ApiService apiService;
    TinyDB tinyDB;
    HashSet<Integer> selectedInterests = new HashSet<>();
    TextView textView1, textView2, textView3, textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tinyDB = new TinyDB(this);

        setContentView(R.layout.activity_interests);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);

        textView1.setOnClickListener(view -> handleInterestSelection(1, textView1));
        textView2.setOnClickListener(view -> handleInterestSelection(2, textView2));
        textView3.setOnClickListener(view -> handleInterestSelection(3, textView3));
        textView4.setOnClickListener(view -> handleInterestSelection(4, textView4));

        Button nextButton = findViewById(R.id.buttonNext);
        nextButton.setOnClickListener(view -> {
            if (!selectedInterests.isEmpty()) {
                saveUserInterests();
            } else {
                Toast.makeText(InterestsActivity.this, "Please select at least one interest.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleInterestSelection(int interestId, TextView textView) {
        textView.setSelected(!textView.isSelected());
        updateTextViewBackground(textView);
        if (textView.isSelected()) {
            selectedInterests.add(interestId);
        } else {
            selectedInterests.remove(interestId);
        }
    }

    private void updateTextViewBackground(TextView textView) {
        textView.setBackgroundResource(R.drawable.selector_interest_background);
    }

    private void saveUserInterests() {
        String interest1 = selectedInterests.contains(1) ? textView1.getText().toString() : "no";
        String interest2 = selectedInterests.contains(2) ? textView2.getText().toString() : "no";
        String interest3 = selectedInterests.contains(3) ? textView3.getText().toString() : "no";
        String interest4 = selectedInterests.contains(4) ? textView4.getText().toString() : "no";
        apiService = ApiClient.getInstance().getApi();


        Call<RegisterResponse> call = apiService.saveInterest(tinyDB.getString("username").toString().trim(), interest1, interest2, interest3, interest4);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RegisterResponse apiResponse = response.body();

                    int error = apiResponse.getError();
                    String message = apiResponse.getMessage();
                    Log.d("InterestsActivity", "onResponse: " + message);
                    tinyDB.putString("isUser", "yes");
                    Toast.makeText(InterestsActivity.this, "Save successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(InterestsActivity.this, HomePageActivity.class)); // Update to the next activity
                } else {
                    Log.d("InterestsActivity", "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.d("InterestsActivity", "onFailure: " + t.getMessage());
            }
        });
    }
}
