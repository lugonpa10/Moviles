package com.example.ejerciciopeliculas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerListado extends AppCompatActivity {
    RecyclerView rvLs;
    Toolbar tbLs;
    MiAdaptadorListado miAdaptadorListado;
    ArrayList<Pelicula>peliculas = new ArrayList<>();
    ArrayList<Pelicula> peliculasAux;
    ActionBar abLs;


    RecyclerView.LayoutManager miLayoutManagerLs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_listado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent pillarPelisMain = getIntent();
        peliculasAux = (ArrayList<Pelicula>) pillarPelisMain.getSerializableExtra("pelis_main");
        peliculas.addAll(peliculasAux);
        rvLs = findViewById(R.id.recyclerViewListado);
        tbLs = findViewById(R.id.toolbarListado);
        miAdaptadorListado = new MiAdaptadorListado(peliculas, this);
        miLayoutManagerLs =new GridLayoutManager(this, 1);
        rvLs.setLayoutManager(miLayoutManagerLs);
        rvLs.setAdapter(miAdaptadorListado);
        setSupportActionBar(tbLs);
        abLs = getSupportActionBar();
        abLs.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_atras,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id ==  android.R.id.home ) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}