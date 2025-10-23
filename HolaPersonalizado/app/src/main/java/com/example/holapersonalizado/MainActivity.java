package com.example.holapersonalizado;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button boton;
    TextView textView;

    EditText editText;

    TextView textView2;







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

textView = findViewById(R.id.nombre);
editText = findViewById(R.id.editTextText3);
textView2 = findViewById(R.id.resultado);
boton = findViewById(R.id.button3);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView2.setText(editText.getText().toString());
                textView2.setVisibility(View.VISIBLE);
                String text = textView2.getText().toString();
                if (text.contains("a")){
                    Toast.makeText(getApplicationContext(), "El texto tiene una a",Toast.LENGTH_LONG).show();
                }





            }
        });
    }
}