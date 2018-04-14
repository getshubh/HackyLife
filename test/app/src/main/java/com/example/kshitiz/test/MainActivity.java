package com.example.kshitiz.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
ListView categories;
    List<RecycleAdapter> lra;
    ListView lv;
    listAdapter ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.categories);
        String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus", "Neptune"};
        String[] constellations=new String[]{
                "Andromeda",
                "Antlia",
                "Apus",
                "Aquarius",
                "Aquila",
                "Ara",
                "Aries",
                "Auriga",
                "Boötes",
                "Caelum",
                "Camelopardalis",
                "Cancer",
                "Canes Venatici",
                "Canis Major",
                "Canis Minor",
                "Capricornus",
                "Carina",
                "Cassiopeia",
                "Centaurus",
                "Cepheus",
                "Cetus",
                "Chamaeleon",
                "Circinus",
                "Columba",
                "Coma Berenices",
                "Corona Austrina",
                "Corona Borealis",
                "Corvus",
                "Crater",
                "Crux",
                "Cygnus",
                "Delphinus",
                "Dorado",
                "Draco",
                "Equuleus",
                "Eridanus",
                "Fornax",
                "Gemini",
                "Grus",
                "Hercules",
                "Horologium",
                "Hydra",
                "Hydrus",
                "Indus",
                "Lacerta",
                "Leo",
                "Leo Minor",
                "Lepus",
                "Libra",
                "Lupus",
                "Lynx",
                "Lyra",
                "Mensa",
                "Microscopium",
                "Monoceros",
                "Musca",
                "Norma",
                "Octans",
                "Ophiuchus",
                "Orion",
                "Pavo",
                "Pegasus",
                "Perseus",
                "Phoenix",
                "Pictor",
                "Pisces",
                "Piscis Austrinus",
                "Puppis",
                "Pyxis",
                "Reticulum",
                "Sagitta",
                "Sagittarius",
                "Scorpius",
                "Sculptor",
                "Scutum",
                "Serpens",
                "Sextans",
                "Taurus",
                "Telescopium",
                "Triangulum",
                "Triangulum Australe",
                "Tucana",
                "Ursa Major",
                "Ursa Minor",
                "Vela",
                "Virgo",
                "Volans",
                "Vulpecula" };
        lra=new ArrayList<>();
        lra.add(new RecycleAdapter(planets));
        lra.add(new RecycleAdapter(constellations));
        ll=new listAdapter(getApplicationContext(),R.layout.listitems,lra);
        lv.setAdapter(ll);
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
            case R.id.action_favs:startActivity(new Intent(MainActivity.this,Main2Activity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
