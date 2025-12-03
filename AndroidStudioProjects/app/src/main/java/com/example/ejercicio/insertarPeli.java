package com.example.ejercicio;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

public class insertarPeli extends AppCompatActivity {

    boolean muestra = false;
    Toolbar toolbarInsertar;
    CalendarView calendarView;
    long fechaMili;
    Date fechaFinal;

int imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_peli);

        final String[] salas = getResources().getStringArray(R.array.Salas);
        Spinner spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                salas
        );

        spinner.setAdapter(adapter);
        calendarView = findViewById(R.id.calendarView);
        calendarView.getDate();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                fechaMili = calendarView.getDate();
                fechaFinal = new Date(fechaMili);

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int i, long l) {

                // Se usa la variable booleana para impedir que muestre el resultado
                // seleccionado por defecto al acceder al activity
                if (!muestra) {
                    muestra = true;
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "Has seleccionado: " + adapterView.getItemAtPosition(i).toString(),
                            Toast.LENGTH_SHORT
                    ).show();

                    setTitle(adapterView.getItemAtPosition(i).toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Se ejecuta cuando se pulsa fuera del Spinner (no se selecciona nada)
            }
        });
        toolbarInsertar = findViewById(R.id.toolbarInsertar);
        ActionBar actionBar = getSupportActionBar();
        setSupportActionBar(toolbarInsertar);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menuinsertar,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.volver) {
            onBackPressed();
            return true;
        }else if (id== R.id.guardar){
            EditText editNombre = findViewById(R.id.editNombre);
            EditText editDir = findViewById(R.id.editDir);
            EditText editDuracion = findViewById(R.id.editDuracion);
            Spinner spinner = findViewById(R.id.spinner);
            RadioGroup radioGroup = findViewById(R.id.Edades);

            String nombre = editNombre.getText().toString();
            String director = editDir.getText().toString();
            int duracion = Integer.parseInt(editDuracion.getText().toString());
            String sala = spinner.getSelectedItem().toString();



            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                    RadioButton rb1 = (RadioButton) findViewById(R.id.rb1);
                    RadioButton rb2 = (RadioButton) findViewById(R.id.rb2);
                    RadioButton rb3 = (RadioButton) findViewById(R.id.rb3);
                    RadioButton rb4 = (RadioButton) findViewById(R.id.rb4);
                    if (rb1.isChecked()){
                         imagen = R.drawable.r;

                    }else if (rb2.isChecked()){
                        imagen = R.drawable.g;

                    }else if (rb3.isChecked()){
                        imagen = R.drawable.pg;
                    }else if (rb4.isChecked()){
                        imagen = R.drawable.pg13;

                    }

                }
            });

            Intent intentSec = new Intent(insertarPeli.this,MainActivity.class);
            intentSec.putExtra("nombre",nombre);
            intentSec.putExtra("director",director);
            intentSec.putExtra("duracion",duracion);
            intentSec.putExtra("sala",sala);
            intentSec.putExtra("clasi",imagen);
            intentSec.putExtra("fecha",fechaFinal);




            return true;
    }
        return super.onOptionsItemSelected(item);
}

}
