package com.weball.benoit.weball.service;

import com.weball.benoit.weball.requestClass.MatchInfo;
import com.weball.benoit.weball.requestClass.SimpleAnswer;
import com.weball.benoit.weball.requestClass.createMatchRequest;
import com.weball.benoit.weball.requestClass.FieldProfile;
import com.weball.benoit.weball.requestClass.FiveInfo;
import com.weball.benoit.weball.requestClass.MatchAnswer;
import com.weball.benoit.weball.requestClass.Matchs;
import com.weball.benoit.weball.requestClass.UserInfo;
import com.weball.benoit.weball.requestClass.UserMe;
import com.weball.benoit.weball.requestClass.notifications;
import com.weball.benoit.weball.requestClass.update_password;
import com.weball.benoit.weball.requestClass.user;
import com.weball.benoit.weball.requestClass.UpdateAnswer;
import com.weball.benoit.weball.requestClass.update_body;
import com.weball.benoit.weball.requestClass.UserFriend;
import com.weball.benoit.weball.requestClass.NotificationAnswer;
import com.weball.benoit.weball.requestClass.Relationship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by benoi on 07/12/2015.
 */


public interface WeballService {

    public static final String ENDPOINT = "https://api.weball.fr";

    @FormUrlEncoded
    @POST("/login/users")
    Observable<UserInfo> getToken(@Field("login") String login, @Field("password") String password);

    @GET("/users/me")
    Observable<UserMe> getUserInfo(@Query("token") String token);

    @POST("/users")
    Observable<UserInfo>  toSubscribe(@Body HashMap<String, user> body);

    @GET("/five")
    Observable<List<FiveInfo>> getAllFives(@Header("x-access-token") String token, @Query("lgt") double lgt, @Query("lat") double lat);

    @GET("/five/{field_id}")
    Observable<FieldProfile> getFiveInfo(@Path("field_id") String id, @Header("x-access-token") String token);

    @GET("/matches/five/{field_id}")
    Observable<List<Matchs>>    getMatches(@Path("field_id") String id, @Header("x-access-token") String token, @Query("sort") String order, @Query("startDate") String startdate, @Query("endDate") String enddate);

    @POST("/matches")
    Observable<MatchAnswer>     createMatch(@Header("x-access-token") String token, @Header("Content-Type") String content,@Body createMatchRequest body);

    @PATCH("/users/me")
    Observable<UpdateAnswer>    updateUserInfo(@Header("x-access-token") String token, @Body HashMap<String, update_body> body);

    @PATCH("/users/me")
    Observable<UpdateAnswer>    updateUserPassword(@Header("x-access-token") String token, @Body HashMap<String, update_password> body);

    @GET("/relationship")
    Observable<List<UserFriend>> getUserFriends(@Header("x-access-token") String token);

    @GET("/notifications")
    Observable<NotificationAnswer>  getNotifications(@Header("x-access-token") String token);

    @GET("/matches/{match_id}")
    Observable<MatchInfo>           getMatchInfo(@Path("match_id") String id, @Query("token") String token);

    @GET("/relationship/{user_id}")
    Observable<List<Relationship>>        getRelashionship(@Path("user_id") String id, @Header("x-access-token") String token);

    @GET("/users/{user_id}")
    Observable<UserMe>              getMateInfo(@Path("user_id") String id, @Header("x-access-token") String token);

    @POST("/relationship/request/{user_id}")
    Observable<SimpleAnswer>        sendRequest(@Path("user_id") String id, @Header("x-access-token") String token);

    @DELETE("/relationship/{user_id}")
    Observable<SimpleAnswer>        deleteFriend(@Path("user_id") String id, @Header("x-access-token") String token);

    @PATCH("/relationship/request/{user_id}")
    Observable<SimpleAnswer>        acceptRequest(@Path("user_id") String id, @Header("x-access-token") String token);

    @DELETE("/relationship/request/{user_id}")
    Observable<SimpleAnswer>        denyRequest(@Path("user_id") String id, @Header("x-access-token") String token);


}
