package com.myadhd.myadhd.Retrofit;

import com.myadhd.myadhd.DTOS.Authenticate;
import com.myadhd.myadhd.DTOS.AuthenticateResponse;
import com.myadhd.myadhd.DTOS.Child;
import com.myadhd.myadhd.DTOS.Question;
import com.myadhd.myadhd.DTOS.Register;
import com.myadhd.myadhd.DTOS.Test;
import com.myadhd.myadhd.DTOS.User;


import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiClient {

    @POST("/api/auth/register")
    Call<Register> register(@Body Register dataRegister);

    @POST("/api/auth/authenticate")
    Call<AuthenticateResponse> authenticate(@Body Authenticate dataAuthenticate);

    @POST("/api/children/{userId}")
    Call<Child> addChild(@Body Child childData, @Header("Authorization") String authToken, @Path("userId")Integer userId);


    @DELETE("/api/children/{childId}")
    Call<ResponseBody> deleteChildById(@Header("Authorization") String authToken, @Path("childId") Integer childId);

    @GET("/api/questions")
    Call<ArrayList<Question>> getQuestions(@Header("Authorization") String authToken,
                                           @Query("first")String first, @Query("last") String last);

    @GET("api/children/{userId}")
    Call<ArrayList<Child>> getChildren(@Header("Authorization") String authToken, @Path("userId")Integer userId);

    @POST("api/tests/{childId}/{childCode}")
    Call<Test> sendTestResponses(@Header("Authorization") String authToken,
                                               @Body ArrayList<Integer> answerList, @Path("childId") Integer childId, @Path("childCode") String childCode);

    @PUT("/api/users/{userId}")
    Call<User> updateUserById(@Header("Authorization") String authToken,  @Path("userId") Integer userId, @Body User updatedUser);

    @GET("/api/users/{userId}")
    Call<User> getUserById(@Header("Authorization") String authToken,  @Path("userId") Integer userId);

    @GET("/api/tests/{childId}")
    Call<ArrayList<Test>> getTestsByChildId(@Header("Authorization") String authToken,  @Path("childId") Integer childId);
}
