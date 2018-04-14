package com.example.kshitiz.hackylife;

import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kshitiz on 13/3/18.
 */

public class jsonHack {
    JSONArray jsonArray=null;
    InputStream is;
    JSONObject jsonObject=null;
    AssetManager am;
    String filename;
    public String objHack(int category){
        jsonObject=new JSONObject();
        switch(category){
            case 0:filename="food";
            break;
            case 1:filename="genius";
            break;
            case 2:filename="health";
            break;
            case 3:filename="party";
            break;
            case 4:filename="shop";
            break;
            case 5:filename="survive";
            break;
            case 6:filename="technology";
            break;
            case 7:filename="economic";
            break;
            case 8:filename="lifetips";
            break;
            default:filename="surprise";
        }
        Log.d("FILE",filename);
        String json;
        try {
            is = am.open(filename.concat(".json"));
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public JSONArray getHack(int cat,AssetManager assetManager){
        am=assetManager;
        String json=objHack(cat);
        if(json==null)
            Log.d("JSON","JSON is null");
        try {
            jsonObject = new JSONObject(json);
            jsonArray =jsonObject.getJSONArray(filename);
        }catch (Exception e){e.printStackTrace();}

        return jsonArray;
    }
}
