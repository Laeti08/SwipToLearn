package com.example.swipetolearn;

import com.google.gson.annotations.SerializedName;

public class RetroBanqueImage {

        @SerializedName("Categorie")
        private String categorie;
        @SerializedName("Image")
        private String image;
        @SerializedName("MotAnglais")
        private String motAnglais;
        @SerializedName("Traduction")
        private String traduction;


        public RetroBanqueImage(String categorie, String image, String motAnglais, String traduction) {
            this.categorie = categorie;
            this.image = image;
            this.motAnglais = motAnglais;
            this.traduction = traduction;

        }

        public String getCategorie() {
            return categorie;
        }

        public void setCategorie(String categorie) {
            this.categorie = categorie;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getMotAnglais() {
            return motAnglais;
        }

        public void setMotAnglais(String motAnglais) {
            this.motAnglais = motAnglais;
        }

        public String getTraduction() {
            return traduction;
        }

        public void setTraduction(String traduction) {
            this.traduction = traduction;
        }



}
