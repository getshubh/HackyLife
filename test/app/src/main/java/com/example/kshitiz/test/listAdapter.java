package com.example.kshitiz.test;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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

public class listAdapter extends ArrayAdapter<RecycleAdapter> {

private int layoutResource;
    public listAdapter(@NonNull Context context, int resource, @NonNull List<RecycleAdapter> objects) {
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
            Toast.makeText(getContext(),"not working",Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(getContext(),"not here",Toast.LENGTH_SHORT).show();
        GridLayoutManager glm=new GridLayoutManager(null,3,GridLayoutManager.HORIZONTAL,false);

        RecycleAdapter ra=getItem(position);
            TextView tv=view.findViewById(R.id.itemcat);
            tv.setText("trdtdgchjb");
            RecyclerView rv=view.findViewById(R.id.hacklist);
            rv.setLayoutManager(glm);
        rv.setAdapter(ra);
        return view;
    }
}
