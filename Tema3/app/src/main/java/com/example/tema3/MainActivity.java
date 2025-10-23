package com.example.tema3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button bt;
    int num;

    Intent intent;

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

        ActivityResultLauncher<Intent> launcher=registerForActivityResult(new
                ActivityResultContracts.StartActivityForResult(),new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
if (result.getResultCode() == RESULT_OK){

    Intent intent = result.getData();
    int numEstrellas = (int) intent.getFloatExtra("estrellas",0);
    switch (numEstrellas){
        case 0:Log.i("PRUEBA","ME ENCUENTRO HORRIBLE");
        case 1:Log.i("PRUEBA","ME ENCUENTRO MUY MAL");
        case 2:Log.i("PRUEBA","ME ENCUENTRO MAL");
        case 3:Log.i("PRUEBA","ME ENCUENTRO REGULAR");
        case 4:Log.i("PRUEBA","ME ENCUENTRO BIEN");
        case 5:Log.i("PRUEBA","ME ENCUENTRO GENIAL");


    }

}
            }
        });


        bt = findViewById(R.id.button);
        num = 5;


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,MainActivity2.class);
intent.putExtra("numero",num);

              //  startActivity(intent);
                    launcher.launch(intent);
            }
        });
    }
}