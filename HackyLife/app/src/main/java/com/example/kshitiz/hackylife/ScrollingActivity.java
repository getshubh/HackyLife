package com.example.kshitiz.hackylife;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ActionMenuView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity {
GridView gd;
public LAdapter la;
AppDb appDb;
static int id,category,status;
static String description;
SharedPreferences.Editor editor;
AssetManager am;
ActionMenuView amv;
SharedPreferences sp;
    Intent intent;
    String arr[]={"food","genius","health","party","shop","survive","tech","moneysave","lifetips"};
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar appbar=findViewById(R.id.app_bar);
        setSupportActionBar(appbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        appbar.setTitle("");
        appbar.setSubtitle("");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Hacky Life";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
        gd=(GridView)findViewById(R.id.grid);
        la=new LAdapter(this,R.layout.hackitems,getData());
        gd.setAdapter(la);
        appDb=AppDb.getAppDb(getApplicationContext());
        sp=getPreferences(MODE_PRIVATE);
        editor = sp.edit();
        am=getApplicationContext().getAssets();
        if(sp.getBoolean("firstrun",true)) {
            editor.putBoolean("firstrun", false);
            editor.apply();
            DBInitialize.populateAsync(appDb,am);
        }
        intent = new Intent(ScrollingActivity.this, HackDisplay.class);
        gd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int lastid=sp.getInt(arr[i],1);
                ImageItem item = (ImageItem) adapterView.getItemAtPosition(i);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("category", arr[i]);
                intent.putExtra("cat",i);
                startActivity(intent);

            }
        });
        MobileAds.initialize(this,"ca-app-pub-2071231742335467~4832273795");
    }

    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        int images[] = {R.drawable.food, R.drawable.genius, R.drawable.health, R.drawable.party, R.drawable.shop, R.drawable.survive, R.drawable.tech, R.drawable.moneysave, R.drawable.lifetips};
        String text[] = {"FOODS & DRINKS", "GENIUS", "HEALTH & FITNESS", "PARTY", "SHOPPING", "SURVIVAL", "TECHNOLOGY", "MONEY SAVING", "LIFE TIPS"};
        for (int i = 0; i < images.length; i++) {
            imageItems.add(new ImageItem(images[i], text[i]));
        }
        return imageItems;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_favs:startActivity(new Intent(ScrollingActivity.this,DisplayFavs.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
