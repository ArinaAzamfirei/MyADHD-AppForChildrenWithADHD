package com.app.myadhdgame.Retrofit;


import com.app.myadhdgame.DTOS.Child;
import com.app.myadhdgame.DTOS.GameResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiClient {


    @GET("/api/game/{childCode}")
    Call<Child> getChildByCode(@Path("childCode") String childCode);

    @POST("/api/game/save/{childId}")
    Call<GameResult> putGameResult(@Body GameResult gameResult, @Path("childId")Integer childId);
}
