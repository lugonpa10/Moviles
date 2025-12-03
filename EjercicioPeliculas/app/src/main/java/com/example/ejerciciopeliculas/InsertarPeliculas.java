package com.example.ejerciciopeliculas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Date;

public class InsertarPeliculas extends AppCompatActivity {
    TextView tvTitulo, tvDuracion, tvDirector, tvSala;
    EditText etNombre, etDirector, etDuracion;
    Spinner spinnerSala;
    ArrayAdapter<String> adaptadorSpinner;
    RadioGroup grupoRadios;
    RadioButton rb1, rb2, rb3, rb4;
    ImageView imagenClasificacion;
    CalendarView calendarioFechas;
    Pelicula nuevaPelicula;
    Button guardar;
    String[] salasSpinner = {"Sala 1", "Sala 2", "Sala 3", "Sala 4", "Sala 5"};
    String sala = "";
    int imagen = 0;
    long fechaMili;
    Date fechaFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        setContentView(R.layout.activity_actividad_insertar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        tvTitulo = findViewById(R.id.tvTituloInsertar);
        tvDirector = findViewById(R.id.tvDirectorInsertar);
        tvDuracion = findViewById(R.id.tvDuracionInsertar);
        tvSala = findViewById(R.id.tvSalaInsertar);
        etNombre = findViewById(R.id.etTituloInsertar);
        etDirector = findViewById(R.id.etDirectorInsertar);
        etDuracion = findViewById(R.id.etDuracioInsertar);
        spinnerSala = findViewById(R.id.spinnerSalaInsertar);


        guardar = findViewById(R.id.btnGuardar);




        imagenClasificacion = findViewById(R.id.imagenClasificacion);


        rb1 = findViewById(R.id.rb1Insertar);
        rb2 = findViewById(R.id.rb2Insertar);
        rb3 = findViewById(R.id.rb3Insertar);
        rb4 = findViewById(R.id.rb4Insertar);

        grupoRadios = findViewById(R.id.grupoEdades);
        calendarioFechas = findViewById(R.id.calendarioFechaInsertar);


        adaptadorSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, salasSpinner);
        spinnerSala.setAdapter(adaptadorSpinner);


        calendarioFechas.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, dayOfMonth);
                fechaFinal = cal.getTime();
            }
        });


        spinnerSala.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sala = salasSpinner[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sala = "No se ha seleccionado la sala";
            }
        });


        grupoRadios.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                if (checkedId == rb1.getId()) {
                    imagen = R.drawable.pg;
                    imagenClasificacion.setImageResource(R.drawable.pg);
                } else if (checkedId == rb2.getId()) {
                    imagen = R.drawable.pg13;
                    imagenClasificacion.setImageResource(R.drawable.pg13);
                } else if (checkedId == rb3.getId()) {
                    imagen = R.drawable.r;
                    imagenClasificacion.setImageResource(R.drawable.r);
                } else if (checkedId == rb4.getId()) {
                    imagen = R.drawable.g;
                    imagenClasificacion.setImageResource(R.drawable.g);
                }

                imagenClasificacion.setVisibility(View.VISIBLE);
            }
        });





            guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        if (etNombre.getText().toString().isEmpty() ||
                                etDirector.getText().toString().isEmpty() ||
                                etDuracion.getText().toString().isEmpty()) {
                            Log.e("ERROR", "Faltan campos por rellenar");
                            return;
                        }

                        nuevaPelicula = new Pelicula(
                                etNombre.getText().toString(),
                                etDirector.getText().toString(),
                                Integer.parseInt(etDuracion.getText().toString()),
                                fechaFinal,
                                sala,
                                imagen,
                                R.drawable.orense
                        );

                        Intent devuelvePeli = new Intent();
                        devuelvePeli.putExtra("peliNueva", nuevaPelicula);
                        setResult(RESULT_OK, devuelvePeli);
                        Log.i("PRUEBA PASAR PELI", "Película creada correctamente");
                        finish();

                }
            });

    }
}