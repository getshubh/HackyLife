package com.example.kshitiz.hackylife;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by kshitiz on 27/3/18.
 */

public class listAdapter extends ArrayAdapter<listitem> {

private int layoutResource;
    public listAdapter(@NonNull Context context, int resource, @NonNull List<listitem> objects) {
        super(context, resource, objects);
        this.layoutResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource,null);
        }
        GridLayoutManager glm;
        listitem li=getItem(position);
        if(li.getRa().getItemCount()>12)
        glm=new GridLayoutManager(null,3,GridLayoutManager.HORIZONTAL,false);
        else
            glm=new GridLayoutManager(null,3-li.getRa().getItemCount()%3,GridLayoutManager.HORIZONTAL,false);
            TextView tv=view.findViewById(R.id.itemcat);
            tv.setText(li.getCategory());
            RecyclerView rv=view.findViewById(R.id.hacklist);
            rv.setLayoutManager(glm);
        rv.setAdapter(li.getRa());
        return view;
    }
}
