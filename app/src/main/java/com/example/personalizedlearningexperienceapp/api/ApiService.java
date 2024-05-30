package com.example.personalizedlearningexperienceapp.api;

import com.example.personalizedlearningexperienceapp.model.response.LoginResponse;
import com.example.personalizedlearningexperienceapp.model.response.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

//    public interface ApiService {
//        @POST("creat_user.php") // Include the .php extension
//        Call<UserResponse> createUser(@Body User user);
//    }

public interface ApiService {
    @FormUrlEncoded

    @POST("creat_user.php")
    Call<RegisterResponse> createUser(
            @Field("username") String username,
            @Field("email") String email,
            @Field("phone_no") String phoneNo,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("interest.php")
    Call<RegisterResponse> saveInterest(
            @Field("username") String username,
            @Field("one") String one,
            @Field("two") String two,
            @Field("three") String three,
            @Field("four") String four
    );


    @FormUrlEncoded
    @POST("login.php") // Update the endpoint URL
    Call<LoginResponse> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );
}