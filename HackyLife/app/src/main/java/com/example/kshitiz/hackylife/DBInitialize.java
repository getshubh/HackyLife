package com.example.kshitiz.hackylife;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kshitiz on 24/3/18.
 */

public class DBInitialize {
    static AssetManager am;
    public static void populateAsync(@NonNull final AppDb appDb,AssetManager ami){
        PopulateDBAsync pdba=new PopulateDBAsync(appDb);
        am=ami;
        pdba.execute();
    }

    public static class PopulateDBAsync extends AsyncTask<Void,Void,Void>{
        private final AppDb appDb;
        PopulateDBAsync(AppDb appDb){
            this.appDb=appDb;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            //String arr[]={"food","genius","health","lifetips","moneysave","party","shop","survive","tech"};
            String arr[]={"food","economic","genius","health","lifetips","technology"};
            List<hack> hacks=new ArrayList<>();
            InputStream is;
            String json;
            for (String hack:arr) {
                try {
                    is = am.open(hack.concat(".json"));
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    json = new String(buffer, "UTF-8");
                    JSONObject jo=new JSONObject(json);
                    JSONArray ja=jo.getJSONArray(hack);
                    int l=ja.length();
                    for (int i = 0; i <l ; i++) {
                        JSONObject js=ja.getJSONObject(i);
                        hack hack1=new hack();
                        hack1.setId(js.getInt("id"));
                        hack1.setDescription(js.getString("description"));
                        hack1.setCategory(js.getInt("category"));
                        hack1.setStatus(js.getInt("status"));
                        hacks.add(hack1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            appDb.UDao().insertAll(hacks);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.d("DAT","Data Inserted");
        }
    }
}
