package com.example.swipetolearn;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import static com.example.swipetolearn.R.id.image_content;

public class CardAdapter extends ArrayAdapter<RetroBanqueImage> {


    public CardAdapter( Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {  //Chargement de la bonne image dans imageView
        ImageView imgView=(ImageView) convertView.findViewById(image_content);
        Picasso.get().load(getItem(position).getImage()).into(imgView);     //chargement de l'image dans ImageView
        return convertView;
    }

}

