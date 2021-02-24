package com.example.swipetolearn;

import com.google.gson.annotations.SerializedName;

public class RetroName {
    //Classe qui correspond au fichier JSON RetroName
    @SerializedName("categorie")
    private String categorie;
    @SerializedName("name")
    private String name;



    public RetroName(String categorie, String name) {
        this.categorie = categorie;
        this.name = name;


    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
