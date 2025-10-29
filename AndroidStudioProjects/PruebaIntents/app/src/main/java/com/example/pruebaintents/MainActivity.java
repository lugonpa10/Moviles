package com.example.pruebaintents;

import static android.content.ClipData.newIntent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button btnPosicion;

    Button btnmensaje;
    Button btnalarma;

    Button btnimagen;

    Button btn5;
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

        final Button btnPosicion =findViewById(R.id.button);
        btnmensaje = findViewById(R.id.button2);
        btnalarma= findViewById(R.id.button3);
        btnimagen = findViewById(R.id.button4);
        btn5= findViewById(R.id.button5);

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
                if(intent.resolveActivity(getPackageManager()) !=null) {
                    startActivity(intent);
                }
            }
        });

      //  btnimagen.setOnClickListener(new View.OnClickListener() {
         //   @Override
         //   public void onClick(View v) {
             //   Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
               // if(intent.resolveActivity(getPackageManager()) !=null) {
                 //   startActivity(intent);
            //}
            //}});

        btnalarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Se requiere el permiso com.android.alarm.permission.SET_ALARM
                Intent intent=new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_MESSAGE,"¡¡ Hora de levantarse !!")
                        .putExtra(AlarmClock.EXTRA_HOUR,7)
                        .putExtra(AlarmClock.EXTRA_MINUTES,30);
                if(intent.resolveActivity(getPackageManager()) !=null) {
                    startActivity(intent);
                }
            }
        });


        btnmensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "¡Hola Mundo!");
                if(intent.resolveActivity(getPackageManager()) !=null) {
                    startActivity(intent);
                }
            }
        });
        btnPosicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double lat = 42.237109;
                Double lon = -8.723474;
                int zoom = 22;
                String label = "MiPunto";
// try { label=URLEncoder.encode("label","UTF-8");
// } catch (UnsupportedEncodingException e){}
                String uri = String.format(Locale.US,
                        "geo:%f,%f?z=%d&q=%f,%f(%s)", lat, lon, zoom, lat, lon, label);
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(uri)); //OPCION 1
                intent.setData(Uri.parse(uri)); // OPCION 2
// Si existe una Activity que es capaz de gestionar los datos esta se lanza
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);
            }
        });
    }
}