package com.example.swipetolearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private BanqueImageRecyclerView adapter;
    private RecyclerView recyclerView;
    public RetroBanqueImage image;
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

                Log.d("response",response.body().get(0).traduction);
                //generateDataList(response.body());
            }

             public void onFailure(Call<List<RetroBanqueImage>> call, Throwable t) {
                //progressDoalog.dismiss();

                 t.printStackTrace();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

        /*callName.enqueue(new Callback<List<RetroName>>() {
            @Override
            public void onResponse(Call<List<RetroName>> callName, Response<List<RetroName>> response) {
                //progressDoalog.dismiss();
                Log.d("response",response.body().size()+" ");

            }

            @Override
            public void onFailure(Call<List<RetroName>> call, Throwable t) {
                //progressDoalog.dismiss();
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
    /*private void generateDataList(List<RetroBanqueImage> photoList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new BanqueImageRecyclerView(this,photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }*/


}
