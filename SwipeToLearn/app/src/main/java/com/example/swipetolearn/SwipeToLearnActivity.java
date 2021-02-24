package com.example.swipetolearn;

import android.content.Intent;

import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wenchao.cardstack.CardStack;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SwipeToLearnActivity extends AppCompatActivity implements CardStack.CardEventListener {

    private Button seeScoresButton;
    private CardStack card_stack;
    private CardAdapter card_adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        //Appel aux fichiers Json
        GetDataClient service = RetroClientInstance.getRetrofitInstance().create(GetDataClient.class);

        Call<List<RetroBanqueImage>> call = service.getAllImages();
        Call<List<RetroName>> callName = service.getAllWords();
        call.enqueue(new Callback<List<RetroBanqueImage>>() {
            @Override
            public void onResponse(Call<List<RetroBanqueImage>> call, Response<List<RetroBanqueImage>> response) {

                Log.d("response",response.body().get(0).image);

            }

            public void onFailure(Call<List<RetroBanqueImage>> call, Throwable t) {


                t.printStackTrace();
                Toast.makeText(SwipeToLearnActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

        callName.enqueue(new Callback<List<RetroName>>() {
            @Override
            public void onResponse(Call<List<RetroName>> callName, Response<List<RetroName>> response) {

                Log.d("responseName",response.body().get(0).getName());

            }

            @Override
            public void onFailure(Call<List<RetroName>> call, Throwable t) {

                t.printStackTrace();
                Toast.makeText(SwipeToLearnActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
        
        initImages();
        card_stack=(CardStack)findViewById(R.id.card_stack);
        card_stack.setContentResource(R.layout.card_layout);
        card_stack.setStackMargin(20);
        card_stack.setAdapter(card_adapter);

        card_stack.setListener(this);


        seeScoresButton = (Button) findViewById(R.id.seeScoresButton);
        seeScoresButton.setOnClickListener(v -> {
            Intent intent = new Intent(SwipeToLearnActivity.this, SwipeToLearnScoresActivity.class);
            startActivity(intent);
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.category_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.animaux:
                Toast.makeText(this,"catégorie animaux sélectionnée",Toast.LENGTH_LONG).show();
                return true;
            case R.id.habitation:
                Toast.makeText(this,"catégorie habitation sélectionnée",Toast.LENGTH_LONG).show();
                return true;
            case R.id.imaginaire:
                Toast.makeText(this,"catégorie imaginaire sélectionnée",Toast.LENGTH_LONG).show();
                return true;
            case R.id.objet:
                Toast.makeText(this,"catégorie objet sélectionnée",Toast.LENGTH_LONG).show();
                return true;
            case R.id.nourriture:
                Toast.makeText(this,"catégorie nourriture sélectionnée",Toast.LENGTH_LONG).show();
                return true;
            case R.id.metier:
                Toast.makeText(this,"catégorie métier sélectionnée",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public boolean gameFunction(int i){
        //A FAIRE
        return false;
    }

    private void gameResult(boolean result){
        String message;
        if (result==true){
            message="Résultat correct, bien joué !";
            Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
        }
        else{
            message="Resultat incorrect, dommage !";
            Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
        }
        //Affichge trueName, la correction
    }

    private void initImages() {
        card_adapter=new CardAdapter(getApplicationContext(),0);
        card_adapter.add(R.drawable.batman);
        card_adapter.add(R.drawable.logo2);
        card_adapter.add(R.drawable.superman);
    }

    @Override
    public boolean swipeEnd(int i, float v) {
        if(i==0 || i==2){
            //gameFunction
            Toast.makeText(this,"a gauche",Toast.LENGTH_LONG).show();
        }
        if(i==1 || i==3){
            //gameFunction
            Toast.makeText(this,"a droite",Toast.LENGTH_LONG).show();
        }

        return (v>300)?true:false;
    }


    @Override
    public boolean swipeStart(int i, float v) {

        return false;
    }

    @Override
    public boolean swipeContinue(int i, float v, float v1) {
        return false;
    }

    @Override
    public void discarded(int i, int i1) {

    }

    @Override
    public void topCardTapped() {

    }

}
