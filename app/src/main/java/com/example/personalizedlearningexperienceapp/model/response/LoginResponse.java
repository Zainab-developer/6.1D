package com.example.personalizedlearningexperienceapp.model.response;
import com.example.personalizedlearningexperienceapp.model.User;
import com.google.gson.annotations.SerializedName;
public class LoginResponse {
    @SerializedName("user")
    private User user;

    @SerializedName("error")
    private int error;

    @SerializedName("message")
    private String message;

    // Getter methods
    public User getUser() {
        return user;
    }

    public int getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
