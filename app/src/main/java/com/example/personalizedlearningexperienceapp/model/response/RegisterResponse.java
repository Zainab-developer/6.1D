package com.example.personalizedlearningexperienceapp.model.response;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {
    @SerializedName("error")
    private int error;

    @SerializedName("message")
    private String message;

    // Getter methods
    public int getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}