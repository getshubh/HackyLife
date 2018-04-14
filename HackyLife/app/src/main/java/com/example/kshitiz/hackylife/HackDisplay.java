package com.example.kshitiz.hackylife;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HackDisplay extends FragmentActivity {
    private static final int NUM_PAGES = 5;
    String title,cat;
    int currentPage,catno,max;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    private AdView adView;
    TextView txt;
    float dxInitial=0;
    JSONArray js=null;
    AppBarLayout abl;
    int i,flag=0,page=0;
    ConstraintLayout layout;
    ImageView iv;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    FloatingActionButton fab;
    Bundle extras;
    List<hack> hackList;
    AppDb appDb;
    List<Integer> status0;
    List<Integer> status1;
    int color[]={Color.rgb(14,166,203),Color.rgb(255,110,64),Color.rgb(61,90,254),Color.rgb(183,28,28),Color.rgb(30,136,229),Color.rgb(211,47,47),Color.rgb(76,219,196),Color.rgb(76,197,173),Color.rgb(245,127,23)};
    int images[]={R.drawable.food,R.drawable.genius,R.drawable.health,R.drawable.party,R.drawable.shop,R.drawable.survive,R.drawable.tech,R.drawable.moneysave,R.drawable.lifetips};
    hack h;
    int categories[]={14,11,13,12,12,12,15,17,16};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hack_display);
            extras = getIntent().getExtras();
            if(extras == null) {
                title= "Surprise";
                cat="surprise";
                catno=10;
            } else {
                title= extras.getString("title");
                cat=extras.getString("category");
                catno=extras.getInt("cat");
            }
            txt=(TextView)findViewById(R.id.hackCat);
            iv=(ImageView)findViewById(R.id.hackImage);
            abl=(AppBarLayout)findViewById(R.id.appbar);
            fab=(FloatingActionButton)findViewById(R.id.floatingActionButton);
            layout=(ConstraintLayout)findViewById(R.id.layout);
            layout.setBackgroundColor(color[catno]);
            abl.setBackgroundColor(color[catno]);
            txt.setText(title);
            txt.setBackgroundColor(color[catno]);
            iv.setContentDescription(title);
            iv.setImageResource(images[catno]);
            i=0;
        hackList=new ArrayList<>();
        status0=new ArrayList<>();
        status1=new ArrayList<>();
        appDb=AppDb.getAppDb(getApplicationContext());
        gethacks pdba=new gethacks(appDb);
        pdba.execute();
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new HackDisplayAdapter(getSupportFragmentManager());
        sp=getPreferences(MODE_PRIVATE);
        spe=sp.edit();
        if(sp.getBoolean("first",true)){
            spe.putBoolean("first",false);
            spe.putInt("food",427);
            spe.putInt("genius",668);
            spe.putInt("health",1);
            spe.putInt("lifetips",1288);
            spe.putInt("moneysave",529);
            spe.putInt("party",1);
            spe.putInt("shop",1);
            spe.putInt("survive",1);
            spe.putInt("tech",1088);
            spe.apply();
        }max=sp.getInt(cat,1);
        mPager.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {


                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    dxInitial=event.getX();
                    Log.d("INITIAL X", String.valueOf(dxInitial));
                }else if(event.getAction() == MotionEvent.ACTION_MOVE){;
                    if(dxInitial - event.getX() > 100 && currentPage==4){
                        mPager.setCurrentItem(0,false);
                    }else if(event.getX() - dxInitial > 50 && currentPage==0){
                        mPager.setCurrentItem(4,false);
                    }
                }
                return false;
            }

        });
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fab.setOnClickListener(new fabListener());

        adView = findViewById(R.id.adView);
        final AdRequest.Builder adReq = new AdRequest.Builder();
        final AdRequest adRequest = adReq.addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);

    }
    private class fabListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if(h.getStatus()==1){
                fab.setImageResource(R.drawable.ic_favorite_black_24dp);
                if(status1.indexOf(hackList.get(i).getId())==-1) {
                    status0.add(hackList.get(i).getId());
                }else{
                    status1.remove(status1.indexOf(hackList.get(i).getId()));
                }
                hackList.get(i).setStatus(0);
                h = hackList.get(i);
                Snackbar.make(view,"Marked Favourite",Snackbar.LENGTH_LONG).show();
            }else{
                fab.setImageResource(R.drawable.ic_favorite_white_24dp);
                if(status0.indexOf(hackList.get(i).getId())==-1){
                    status1.add(hackList.get(i).getId());
                }else{
                    status0.remove(status0.indexOf(hackList.get(i).getId()));
                }
                hackList.get(i).setStatus(1);
                h=hackList.get(i);
                Snackbar.make(view,"Favourite Removed",Snackbar.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onBackPressed() {
            spe.putInt(cat,max) ;
            spe.apply();
            updateStatus us=new updateStatus();
            us.execute();
        super.onBackPressed();
    }

    public class gethacks extends AsyncTask<Void,Void,Void> {
        private final AppDb appDb;
        List<hack> hh=new ArrayList<>();
        gethacks(AppDb appDb){
            this.appDb=appDb;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            hh=appDb.UDao().getAllbyCat(categories[catno]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            hackList.addAll(hh);
            flag=1;
            i=max-hh.get(0).getId();
            mPager.setAdapter(mPagerAdapter);
        }
    }

    public class updateStatus extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            int i,j;
            j=status0.size();
            for(i=0;i<j;i++){
                appDb.UDao().updatehack(0,status0.get(i));
            }
            j=status1.size();
            for (i=0;i<j;i++){
                appDb.UDao().updatehack(1,status1.get(i));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getApplicationContext(),"Status Updated",Toast.LENGTH_SHORT).show();
        }
    }

    private class HackDisplayAdapter extends FragmentStatePagerAdapter {
        public HackDisplayAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Hack_Display hd=new Hack_Display();
            int l=hackList.size();
            if((page==0&&position==4)||page>position) {
                page=position;
                if(i==0)
                    i=hackList.size()-1;
                else
                    i = i - 1;
            }
            else if(page!=position){
                page=position;
                if(i==hackList.size()-1)
                    i=0;
                else
                    i = i + 1;
            }
                h = hackList.get(i);
            if(h.getStatus()==0)
                fab.setImageResource(R.drawable.ic_favorite_black_24dp);
            else
                fab.setImageResource(R.drawable.ic_favorite_white_24dp);
            if(max<h.getId())
                max=h.getId();
            Bundle bundle=new Bundle();
            bundle.putString("cat",h.getDescription());
            hd.setArguments(bundle);
            return hd;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}