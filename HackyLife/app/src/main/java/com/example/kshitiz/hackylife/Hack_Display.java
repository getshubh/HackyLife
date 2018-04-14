package com.example.kshitiz.hackylife;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * Created by kshitiz on 9/3/18.
 */

public class Hack_Display extends Fragment {
String hack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(
                R.layout.fragment_hack_display, container, false);
        TextView txt=(TextView)rootView.findViewById(R.id.hackText);
        Bundle bundle=getArguments();
        hack=bundle.getString("cat");
        txt.setText(hack);
        return rootView;
    }
}
