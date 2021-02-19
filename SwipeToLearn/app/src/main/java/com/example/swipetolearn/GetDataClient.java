package com.example.swipetolearn;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataClient {
    @GET("/BanqueImage.json")
    Call<List<RetroBanqueImage>> getAllImages();

    @GET("/name.json")
    Call<List<RetroName>> getAllWords();


}
