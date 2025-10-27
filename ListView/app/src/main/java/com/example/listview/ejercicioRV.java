package com.example.listview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ejercicioRV extends AppCompatActivity {
    ArrayList<SistemaOperativo> sistemas;
    RecyclerView rv;
    MiAdaptador miAdaptador;

    Toolbar toolbar;

    RecyclerView.LayoutManager miLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        rellenaDatos(10);
        miAdaptador=new MiAdaptador(sistemas);
        rv=findViewById(R.id.rv);
        miLayoutManager = new GridLayoutManager(this, 3);
        rv.setLayoutManager(miLayoutManager);
        rv.setAdapter(miAdaptador);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu2,menu);
        return true;
    }

    public void rellenaDatos(int vueltas){
        sistemas= new ArrayList<SistemaOperativo>();
        for (int i=1;i<=vueltas;i++) {
            sistemas.add(new SistemaOperativo("Ubuntu 14 "+i, "2014", R.drawable.ubuntu14));
            sistemas.add(new SistemaOperativo("MacOS X "+i, "2004", R.drawable.maxx));
            sistemas.add(new SistemaOperativo("Windows 95 "+i, "1995", R.drawable.w95));
            sistemas.add(new SistemaOperativo("Debian "+i, "1993", R.drawable.debian));
            sistemas.add(new SistemaOperativo("Windows 98 "+i, "1998", R.drawable.w98));
            sistemas.add(new SistemaOperativo("Linux Mint 15 "+i, "2013", R.drawable.mint));
            sistemas.add(new SistemaOperativo("Windows 10 "+i, "2016", R.drawable.w10));
            sistemas.add(new SistemaOperativo("Android "+i, "2006", R.drawable.android));
            sistemas.add(new SistemaOperativo("iOS 8 "+i, "2014", R.drawable.ios8));
            sistemas.add(new SistemaOperativo("Windows Vista "+i, "2007", R.drawable.wvista));
            sistemas.add(new SistemaOperativo("Windows XP "+i, "2001", R.drawable.wxp));
            sistemas.add(new SistemaOperativo("Elementary "+i,"2014", R.drawable.elementary));
            sistemas.add(new SistemaOperativo("Ubuntu 20 "+i, "2020", R.drawable.ubuntu20));
            sistemas.add(new SistemaOperativo("Windows 11 "+i, "2021", R.drawable.w11));
        }
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int pos = miAdaptador.getSelectedPos();
        ActionBar ab = getSupportActionBar();
// Si hay un elemento pulsado
        if (miAdaptador.getSelectedPos() >= 0) {
// Se añade un nuevo elemento con la imagen new_item
            if (item.getItemId() == R.id.madd) {
// Se añade un elemento en la posición a insertar
                sistemas.add(pos, new SistemaOperativo("prueba" + pos,
                        String.valueOf(pos), R.drawable.new_item));
// Se actualiza esa posición con los nuevos datos
                miAdaptador.notifyItemInserted(pos);
// Se desmarca la antigua posición marcada
                miAdaptador.notifyItemChanged(pos + 1);
            } else if (item.getItemId() == R.id.mdel) {
// Borramos el elemento indicado
                sistemas.remove(pos);
// Indicamos al adaptador que el elemento se ha borrado
                miAdaptador.notifyItemRemoved(pos);
// Indicamos que no hay elementos marcados
                miAdaptador.setSelectedPos(RecyclerView.NO_POSITION);
            } else if (item.getItemId() == R.id.medit) {
// Editamos el nombre del sistema operativo a modificado
                sistemas.get(pos).setNombre("Modificado");
// Se actualiza esa posición con los nuevos datos
                miAdaptador.notifyItemChanged(pos);
            } else if (item.getItemId() == R.id.mmove) {
// Se mueve el elemento seleccionado a la posición 1
                // (la primera posición es 0)
                SistemaOperativo aux = sistemas.get(pos);
                // Nueva posición a mover
                int newPos = 1;
// Eliminamos el elemento de su posición original
                sistemas.remove(pos);
// Lo movemos a su nueva posición
                sistemas.add(newPos, aux);
// Se actuliza la posiciones original y nueva para que
                // reflejen los cambios
                miAdaptador.notifyItemChanged(pos);
                miAdaptador.notifyItemMoved(pos, newPos);
// Indicamos que la posición seleccionada es newPos
                miAdaptador.setSelectedPos(newPos);
            }
        } else {
            Toast.makeText(this, "Pulsa posición", Toast.LENGTH_SHORT).show();
        }
        ab.setTitle("Tam: " + sistemas.size());
        ab.setSubtitle("Pos: " + miAdaptador.getSelectedPos());
        return true;
    }

}