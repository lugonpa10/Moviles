package com.example.listview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<String> lista = new ArrayList<>();
    ArrayAdapter<String> adapter;

    Toolbar toolbar;


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
       lv = findViewById(R.id.lv);
       toolbar = findViewById(R.id.toolbar2);
       setSupportActionBar(toolbar);




       String[] contactos = new String[]{"Cachorra1","Cachorra2","Cachorra3","Cachorra4","AAPapa","AAmama","Nico","Curro","Denis","Iago","Pablo"};
       for(int i = 0;i< contactos.length;i++){
           lista.add(contactos[i]);
       }

        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_checked,lista);
        lv.setAdapter(adapter);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id =item.getItemId();
        if (id == R.id.menu){
            for (int i = lv.getCount(); i >= 0 ; i--) {
                 if (lv.isItemChecked(i)) {
                     lista.remove(i);
                 }
            }
            lv.getCheckedItemPositions().clear();

            adapter.notifyDataSetChanged();

        }


        return super.onOptionsItemSelected(item);
    }

}