package com.example.swipetolearn;

import android.content.Intent;

import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.wenchao.cardstack.CardStack;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.swipetolearn.SwipeToLearnScoresActivity.setScoreText;


public class SwipeToLearnActivity extends AppCompatActivity implements CardStack.CardEventListener {

    private Button seeScoresButton; //Bouton "voir les scores", changement d'activité
    private CardStack card_stack;
    private CardAdapter card_adapter;
    private TextView textView; //zone de texte où se trouve le mot en Anglais
    private int index=0; //index de l'image affichée (elles sont dans l'ordre)
    private int swipeNumber=0; //Nombre de swipe joué
    private int numberVictory=0; //Nombre de victoire
    private TextView zoneText; //zone de texte où on stocke les joueurs
    private String[] englishwordList={"Donkey","Pig","Dog","Koala","Horse","Turtle","Rabbit","Door","House","Kitchen","Bathroom","Bedroom","Garden","Flat","Airplane","Bicycle","Boat","Car","Subway","Train","Roller Skates","Water","Strawberry","Cherry","Pizza","Raspberry","Banana","Potatoes","Farmer","Cooker","Doctor","Teacher","Musician","Painter","Writer","World cup","Scissors","Table","Computer","Shield","Board Game","Headphones","Charmander","Magician","Dragon","Angel","Elf","Dwarf","Squirtle"};
    //Creation de la liste manuelle des mots

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout); //Instance de game_layout


        //APPEL AUX FICHIERS JSON
        GetDataClient service = RetroClientInstance.getRetrofitInstance().create(GetDataClient.class);

        Call<List<RetroBanqueImage>> call = service.getAllImages();
        Call<List<RetroName>> callName = service.getAllWords();
        call.enqueue(new Callback<List<RetroBanqueImage>>() {
            @Override
            public void onResponse(Call<List<RetroBanqueImage>> call, Response<List<RetroBanqueImage>> response) {
                Log.d("response",response.body().get(0).image);
                card_adapter.addAll(response.body());//Ajout du contenu du fichier JSON

                textView=(TextView) findViewById(R.id.gameName);    //Identification de textView
                textView.setText(englishwordList[0]);   //Initialisation de textView
            }

            public void onFailure(Call<List<RetroBanqueImage>> call, Throwable t) {


                t.printStackTrace();
                Toast.makeText(SwipeToLearnActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


        //Nous n'avons pas eu le temps d'implementer les fonctions pour utiliser ce fichier JSON
        /*callName.enqueue(new Callback<List<RetroName>>() {
            @Override
            public void onResponse(Call<List<RetroName>> callName, Response<List<RetroName>> response) {

                Log.d("responseName",response.body().get(0).getName());

            }

            @Override
            public void onFailure(Call<List<RetroName>> call, Throwable t) {

                t.printStackTrace();
                Toast.makeText(SwipeToLearnActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });*/


        //MISE EN PLACE SWIPECARD
        card_adapter = new CardAdapter(getApplicationContext(), 0);
        card_stack=(CardStack)findViewById(R.id.card_stack);
        card_stack.setContentResource(R.layout.card_layout);
        card_stack.setStackMargin(20);
        card_stack.setAdapter(card_adapter);

        card_stack.setListener(this);


        //BOUTON VOIR LES SCORES
        seeScoresButton = (Button) findViewById(R.id.seeScoresButton);
        seeScoresButton.setOnClickListener(v -> {
            Intent intent = new Intent(SwipeToLearnActivity.this, SwipeToLearnScoresActivity.class);
            startActivity(intent);
        });

// ici on esssaye d'ajouter un joueur à la BDD, l'ajout semble bien marché mais on n'arrive pas à visualiser ce qu'on ajoute
        /*Player player=new Player( 0, "login", "password", 0);
        UserDatabase db= Room.databaseBuilder(this,UserDatabase.class, "database.db").build();
        db.playerDao().insert(player);
        List<Player> playerList = db.playerDao().getAll();
            String text = "";
            for (Player player : playerList) {

                Log.i("SwipeToLearn", player.toString());
                text += player.toString() + "\n";
            }
            String finalText = text;
            runOnUiThread(() -> {

            zoneText.setText(finalText);

            });
        */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //Creation d'un menu pour changer de categorie
        getMenuInflater().inflate(R.menu.category_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //Creation des items du menu
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

    public int returnIndex(String[] listString){ //permet de retourner l'index du mot en anglais dans la liste
        for (int i=0;i<listString.length;i++){
            if(listString[i].equals(textView.getText().toString())){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean swipeEnd(int i, float v) {
        if(i==0 || i==2){
            //L'utilisateur dit que c'est faux
            if(index==returnIndex(englishwordList)){ //Si c'est réellement juste
                gameResult(false);
            }
            else{
                gameResult(true); //Si c'est réellement faux
            }
        }
        if(i==1 || i==3){
            //L'utilisateur dit que c'est juste
            if(index==returnIndex(englishwordList)){
                gameResult(true); //Si c'est réellement juste
            }
            else{
                gameResult(false); //Si c'est réellement faux
            }
        }
        int round= (int)(Math.random()*100);
        if(round<60){
            int choice = (int)(Math.random() * 48);
            textView.setText(englishwordList[choice]);  //Permet de mélanger les mots en anglais
        }
        else{
            textView.setText(englishwordList[index+1]);
        }

        index=index+1;
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


    private void gameResult(boolean result){    //Affiche un toast pour annoncer le resultat sur l'ecran
        String message;
        if (result==true){
            message="Résultat correct, bien joué !";
            Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
            numberVictory++;    //compte le nombre de victoire
        }
        else{
            message="Resultat incorrect, dommage !";
            Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
        }
        swipeNumber++;  //compte le nombre de parties jouées
        setScoreText(numberVictory,swipeNumber);   //Permet de mettre à jour le score du joueur **ne fonctionne pas**
    }



}
