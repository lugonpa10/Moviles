package com.example.ejercicio;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Pelicula> peliculas;
    boolean dosColumnas = false;


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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.listadoCompleto){
            Intent intent=new Intent(MainActivity.this, listadocompleto.class);
            startActivity(intent);
            return true;
        }else if (id==R.id.AñadirPeli){
            Intent intent = new Intent(MainActivity.this,insertarPeli.class);
            startActivity(intent);

            return true;
        }else if (id==R.id.ListadoFavoritos){

            return true;
        }else if (id == R.id.SoloFav){

            ArrayList<Pelicula> filtro = new ArrayList<>();

            for (Pelicula p : peliculas){
                if (p.getFavorita())
                    filtro.add(p);
            }

            miAdaptador = new MiAdaptador(filtro, txt);
            rv.setAdapter(miAdaptador);

            getSupportActionBar().setSubtitle("Favoritas: " + filtro.size());

            return true;


        }else if(id==R.id.Columnas){


            if (dosColumnas){
                miLayoutManager = new GridLayoutManager(this,2);
                rv.setLayoutManager(miLayoutManager);
                dosColumnas = false;

            }else {
                miLayoutManager = new GridLayoutManager(this,1);
                rv.setLayoutManager(miLayoutManager);
            }
        }


        return super.onOptionsItemSelected(item);
    } 



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK){
            String titulo = data.getStringExtra("nombre");
            String director = data.getStringExtra("director");
            int duracion = data.getIntExtra("duracion",0);
            String sala = data.getStringExtra("sala");
            int clasi = data.getIntExtra("clasi",0);




        }

    }









    }