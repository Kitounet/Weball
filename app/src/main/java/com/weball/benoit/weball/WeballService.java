package com.weball.benoit.weball;

import java.util.HashMap;
import java.util.List;

import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.mime.TypedInput;
import rx.Observable;

/**
 * Created by benoi on 07/12/2015.
 */


public interface WeballService {

    public static final String ENDPOINT = "http://ec2-54-93-86-36.eu-central-1.compute.amazonaws.com";

    @FormUrlEncoded
    @POST("/login/users")
    Observable<UserInfo> getToken(@Field("login") String login, @Field("password") String password);

    @GET("/users/me")
    Observable<UserInfo> getUserInfo(@Query("token") String token);


//    @POST("/users")
//    Observable<UserInfo>  toSubscribe(@Field("username") String username, @Field("password") String password,
//    @Field("email") String email,@Field("firstName") String firstName, @Field("lastName") String lastName,
//    @Field("birthday") String birthday);


    @POST("/users")
    Observable<UserInfo>  toSubscribe(@Body HashMap<String, user> body);

    @GET("/five")
    Observable<List<FiveInfo>> getAllFives(@Header("x-access-token") String token, @Query("lgt") double lgt, @Query("lat") double lat);
}
