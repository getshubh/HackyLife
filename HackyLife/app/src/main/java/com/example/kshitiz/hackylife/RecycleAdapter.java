package com.example.kshitiz.hackylife;

import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kshitiz on 27/3/18.
 */



public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private String[] mDataset;
    private int c;
    private FragmentManager fm;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tv;
        public MyViewHolder(View view) {
            super(view);
            tv=(TextView)view.findViewById(R.id.hacks);
        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public RecycleAdapter(String[] myDataset, int c, FragmentManager fm) {
        mDataset = myDataset;
        this.c=c;
        this.fm=fm;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleritems, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tv.setText(mDataset[position]);
        holder.tv.setBackgroundColor(c);
        holder.tv.setTextColor(Color.rgb(255,255,255));
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogfragment df=new dialogfragment();
                Bundle bundle=new Bundle();
                bundle.putString("msg",mDataset[position]);
                df.setArguments(bundle);
                df.show(fm,"hackylife");
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}

