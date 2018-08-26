package me.firdaus1453.firebaseapp.databasefirebase;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import me.firdaus1453.firebaseapp.R;

class PicassoClient {

    public static void downloading(Context c, String urlgambar, ImageView img){
        if (urlgambar!=null&&urlgambar.length()>0) {
            Picasso.with(c).load(urlgambar).placeholder(R.drawable.noimage).error(R.drawable.noimage).into(img);
        }else {
            Picasso.with(c).load(R.drawable.logofirebase).into(img);
        }
    }
}
