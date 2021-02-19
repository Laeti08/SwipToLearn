package com.example.swipetolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetDataClient service = RetroClientInstance.getRetrofitInstance().create(GetDataClient.class);
        Call<List<RetroBanqueImage>> call = service.getAllImages();
        Call<List<RetroName>> callName = service.getAllWords();
        call.enqueue(new Callback<List<RetroBanqueImage>>() {
            @Override
            public void onResponse(Call<List<RetroBanqueImage>> call, Response<List<RetroBanqueImage>> response) {
                progressDoalog.dismiss();

            }

            @Override
            public void onFailure(Call<List<RetroBanqueImage>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

        callName.enqueue(new Callback<List<RetroName>>() {
            @Override
            public void onResponse(Call<List<RetroName>> call, Response<List<RetroName>> response) {
                progressDoalog.dismiss();

            }

            @Override
            public void onFailure(Call<List<RetroName>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
