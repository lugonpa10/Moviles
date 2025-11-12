package com.example.ejercicio;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Pelicula> peliculas;

    RecyclerView rv;

    MiAdaptador miAdaptador;

    Toolbar toolbar;
    TextView txt;

    ImageButton imgToolbar;


    RecyclerView.LayoutManager miLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        peliculas = Datos.rellenaPeliculas();
txt = findViewById(R.id.textView);
imgToolbar = findViewById(R.id.ImgButtonToolbar);
        miAdaptador = new MiAdaptador(peliculas,txt);

        rv = findViewById(R.id.recyclerView);
        miLayoutManager = new LinearLayoutManager(this);

        rv.setLayoutManager(miLayoutManager);
        rv.setAdapter(miAdaptador);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imgToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionBar ab = getSupportActionBar() ;

                if (!ab.isShowing()){
                    ab.show();
                }else {
                    ab.hide();
                }


            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }







}