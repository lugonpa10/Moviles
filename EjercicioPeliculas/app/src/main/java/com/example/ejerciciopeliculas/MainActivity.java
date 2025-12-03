package com.example.ejerciciopeliculas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    Toolbar tb;
    ImageButton boton;
    RecyclerView.LayoutManager miLayoutManager;
    RecyclerView.LayoutManager miLayoutManager2;
    MiAdaptador miAdaptador;
    ArrayList<Pelicula>peliculas;
    ArrayList<Pelicula>peliculasAux;
    ActionBar ab;
    TextView seccionFija;
    ActivityResultLauncher<Intent> launcher;
    ActivityResultLauncher<Intent> launcher2;
    boolean cambiaCols = true;
    boolean mostrarFavs = false;
    Calendar cal = Calendar.getInstance();
    Pelicula peliAux;
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

        peliculasAux = new ArrayList<>();
        peliAux = new Pelicula("", "", 0, cal.getTime(), "", R.drawable.g, R.drawable.sincara);
        peliculas = Datos.rellenaPeliculas();
        seccionFija = findViewById(R.id.tvSeccionFija);
        miAdaptador = new MiAdaptador(peliculas, seccionFija);
        boton = findViewById(R.id.btnActionBar);
        rv = findViewById(R.id.recyclerView);
        miLayoutManager =new GridLayoutManager(this, 1);
        miLayoutManager2 =new GridLayoutManager(this, 2);
        rv.setLayoutManager(miLayoutManager);
        rv.setAdapter(miAdaptador);
        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        ab = getSupportActionBar();
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ab.isShowing()){
                    ab.hide();
                    boton.setImageResource(R.drawable.ticklinear_106227);
                } else {
                    ab.show();
                    boton.setImageResource(R.drawable.letter_x_close_cross_remove_delete_cancel_icon_267835);
                }
            }
        });
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if (o.getResultCode() == RESULT_OK){
                    Intent i = o.getData();
                    peliculasAux = (ArrayList<Pelicula>) i.getSerializableExtra("pelis_nuevas_favs");
                    peliculas.clear();
                    peliculas.addAll(peliculasAux);
                    miAdaptador.notifyDataSetChanged();
                }

            }
        });
        launcher2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if (o.getResultCode() == RESULT_OK){
                    Intent i = o.getData();
                    peliAux = (Pelicula) i.getSerializableExtra("peliNueva");
                    peliculas.add(peliAux);
                    miAdaptador.notifyDataSetChanged();
                }
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.listado){
            Intent lanzarListado = new Intent(MainActivity.this, RecyclerListado.class);
            lanzarListado.putExtra("pelis_main", peliculas);
            startActivity(lanzarListado);

        } else if(item.getItemId() == R.id.favoritos){
            Intent devolverPelis = new Intent(MainActivity.this, ListadoFavoritos.class);
            devolverPelis.putExtra("pelis",peliculas);
            launcher.launch(devolverPelis);

        } else if(item.getItemId() == R.id.añadir){
            Intent lanzarInsertar = new Intent(MainActivity.this, InsertarPeliculas.class);
            launcher2.launch(lanzarInsertar);

        } else if(item.getItemId() == R.id.mostrarCol){
            if (cambiaCols){
                cambiaCols = false;
                rv.setLayoutManager(miLayoutManager2);
            } else {
                cambiaCols = true;
                rv.setLayoutManager(miLayoutManager);
            }
        } else if(item.getItemId() == R.id.mostrarFav){
            if (!mostrarFavs){
                peliculasAux.clear();
                peliculasAux.addAll(peliculas);
                peliculas.clear();
                for (int i = 0; i < peliculasAux.size(); i++){
                    if (peliculasAux.get(i).getFavorita()){
                        peliculas.add(peliculasAux.get(i));
                        miAdaptador.notifyDataSetChanged();
                    }
                }
                mostrarFavs = true;
            } else {
                peliculas.clear();
                peliculas.addAll(peliculasAux);
                miAdaptador.notifyDataSetChanged();
                mostrarFavs = false;
            }
        }


        return super.onOptionsItemSelected(item);
    }


}