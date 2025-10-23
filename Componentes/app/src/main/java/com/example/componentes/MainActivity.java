package com.example.componentes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


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

public class MainActivity extends AppCompatActivity {

ToggleButton tb;

ActionBar actionBar;

Button bt5;
Toolbar toolbar;

CheckBox ch1;
    CheckBox ch2;
    CheckBox ch3;

    RadioGroup rg;
    RadioButton rb1;
    RadioButton rb2;

SeekBar skb;

TextView txt;

Switch sw;
TextView txt2;
TextView txt3;
EditText txt4;

Button bt1;
Button bt2;

Button bt4;

Toast toast;
    int cont;

RatingBar rb;

ImageButton img;
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


tb = findViewById(R.id.toggleButton);
ch1 = findViewById(R.id.check1);
ch2 = findViewById(R.id.check2);
ch3 = findViewById(R.id.check3);
skb = findViewById(R.id.SeekBar);
bt5 = findViewById(R.id.button5);
txt = findViewById(R.id.resultado);
sw = findViewById(R.id.swit);
txt2 = findViewById(R.id.textView2);
bt1 = findViewById(R.id.button1);
bt2 = findViewById(R.id.button2);
rb1 = findViewById(R.id.radioButton1);
rb2 = findViewById(R.id.radioButton2);
rb = findViewById(R.id.ratingBar);
txt3 = findViewById(R.id.textView3);
rg = findViewById(R.id.RadioGroup);
txt4 = findViewById(R.id.editTextText);
img = findViewById(R.id.imgButton);
bt4 = findViewById(R.id.button4);
toolbar = findViewById(R.id.toolbar);
setSupportActionBar(toolbar);
actionBar = getSupportActionBar();
actionBar.setSubtitle(Integer.toString(skb.getProgress()));


tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
        if (tb.isChecked()) {
            ch1.setChecked(true);
        } else {
            ch1.setChecked(false);
        }
    }});


bt5.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (getSupportActionBar() !=null){
            if (getSupportActionBar().isShowing()){
                getSupportActionBar().hide();
            }
        }
    }
});

skb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        txt.setText(Integer.toString(skb.getProgress()));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
});

sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
        if (sw.isChecked()){
            txt2.setText("activo");

        }else
        {
            txt2.setText("desactivo");
        }
    }
});

bt1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        tb.setChecked(false);
        ch1.setChecked(false);
        ch2.setChecked(false);
        ch3.setChecked(false);
        rb1.setChecked(false);
        rb2.setChecked(false);
        sw.setChecked(false);
        skb.setProgress(0);
        rb.setRating(0);
        txt.setText("");
        txt4.setText("");


    }
});

bt2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {



 if (ch2.isChecked()){

     cont--;
     txt3.setText(Integer.toString(cont));
 }else {
     cont++;
     txt3.setText(Integer.toString(cont));
 }
    }
});


rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {

        if (rb1.isChecked()){
            Toast.makeText(MainActivity.this,"Has pulsado el radiobutton1",Toast.LENGTH_LONG).show();

        }else if (rb2.isChecked()){
            Toast.makeText(MainActivity.this,"Has pulsado el radiobutton2",Toast.LENGTH_LONG).show();

        }


    }
});
int num = 5;


        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new
                ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    Intent intent = result.getData();

                    float numestrellas = intent.getFloatExtra("estrellas",0);
                    rb.setRating(numestrellas);
                    txt.setText(numestrellas+"");
                    Log.i("NUMERO ESTRELLAS",numestrellas + "");

                }

            }});


img.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        intent = new Intent(MainActivity.this, MainActivity.class);
        intent.putExtra("numero",num);
        intent.putExtra("texto",txt4.getText().toString());
        launcher.launch(intent);
    }



});

bt4.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +txt4.getText()));


        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }
    }
});



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menuprincipal,menu);
        return true;
    }
}