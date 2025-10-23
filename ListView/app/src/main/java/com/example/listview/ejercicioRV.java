package com.example.listview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
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
}