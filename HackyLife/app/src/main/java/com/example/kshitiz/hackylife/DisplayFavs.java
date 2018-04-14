package com.example.kshitiz.hackylife;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DisplayFavs extends AppCompatActivity {
ContentLoadingProgressBar clpb;
listAdapter la;
AppDb appDb;
ListView lv;
List<String> FavHacksList;
    String cats[] = {"FOODS & DRINKS", "GENIUS", "HEALTH & FITNESS", "PARTY", "SHOPPING", "SURVIVAL", "TECHNOLOGY", "MONEY SAVING", "LIFE TIPS"};
    int color[]={Color.rgb(14,166,203),Color.rgb(255,110,64),Color.rgb(61,90,254),Color.rgb(183,28,28),Color.rgb(30,136,229),Color.rgb(211,47,47),Color.rgb(76,219,196),Color.rgb(76,197,173),Color.rgb(245,127,23)};
    int categories[]={14,11,13,12,12,12,15,17,16};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_favs);
        clpb=findViewById(R.id.loading);
        FavHacksList=new ArrayList<>();
        appDb=AppDb.getAppDb(getApplicationContext());
        GetFavs gf=new GetFavs(appDb);
        gf.execute();
        lv=findViewById(R.id.FavHacks);

    }
    public class GetFavs extends AsyncTask<Void,Void,Void>{
    AppDb appDb;
    List<listitem> lra;
    public GetFavs(AppDb appDb){
        this.appDb=appDb;
        lra=new ArrayList<>();
    }

        @Override
        protected void onPreExecute() {
            clpb.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
        for(int i=0;i<categories.length;i++) {
            FavHacksList = appDb.UDao().getAllFavsbyCat(categories[i]);
            if(FavHacksList.size()!=0){
            listitem li=new listitem();
            li.setCategory(cats[i]);
            li.setRa(new RecycleAdapter(FavHacksList.toArray(new String[0]),color[i],getFragmentManager()));
            lra.add(li);
            }
        }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            clpb.setVisibility(View.GONE);
            if(lra.size()!=0) {
                la = new listAdapter(getApplicationContext(), R.layout.listitems, lra);
                lv.setAdapter(la);
            }
            else
                Toast.makeText(getApplicationContext(),"No Favourites Found",Toast.LENGTH_LONG).show();
        }
    }
}
