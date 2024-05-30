package com.example.personalizedlearningexperienceapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.personalizedlearningexperienceapp.utils.TinyDB;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomePageActivity extends AppCompatActivity {
    TextView textViewYourName, textViewNotification;
    Button viewTaskBtn;
    ImageView imageViewProfile;
    LinearLayout containerTask;
    private SharedPreferences prefs;
    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        textViewYourName = findViewById(R.id.textViewYourName);
        imageViewProfile = findViewById(R.id.imageViewProfile);
        containerTask = findViewById(R.id.containerTask);
        viewTaskBtn = findViewById(R.id.viewTask);
        tinyDB = new TinyDB(this);


        textViewYourName.setText(tinyDB.getString("username").toString());


        viewTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, QuizActivity.class));
            }
        });
    }

}
