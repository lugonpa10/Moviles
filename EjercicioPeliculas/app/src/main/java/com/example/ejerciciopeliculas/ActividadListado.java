package com.example.ejerciciopeliculas;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActividadListado extends AppCompatActivity {
    ImageView ivCaratulaGrande;
    ScrollView scrollSinopsis;
    TextView textoSinopsis;
    Pelicula peliculaRecogida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actividad_listado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent recibePelicula = getIntent();
        peliculaRecogida = (Pelicula) recibePelicula.getSerializableExtra("pelicula_seleccionada");
        ivCaratulaGrande = findViewById(R.id.caratulaGrande);
        scrollSinopsis = findViewById(R.id.scrollSinopsis);
        textoSinopsis = findViewById(R.id.tvSinopsis);
        this.setTitle(peliculaRecogida.getTitulo());
        ivCaratulaGrande.setImageResource(peliculaRecogida.getPortada());
        textoSinopsis.setText(peliculaRecogida.getSinopsis());
        ivCaratulaGrande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchYoutubeVideo(peliculaRecogida.getIdYoutube());
            }
        });
    }
    public void watchYoutubeVideo(String id) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }
}