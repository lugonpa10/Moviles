package com.example.ejercicio;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MyViewHolder> {

    ArrayList<Pelicula> peliculas;
    TextView txt;


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elemento = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda, parent, false);
        MyViewHolder mvh = new MyViewHolder(elemento);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pelicula pel = this.peliculas.get(position);
        holder.getTitulo().setText(pel.getTitulo());
        holder.getDirector().setText(pel.getDirector());
        holder.getPortadas().setImageResource(pel.getPortada());
        holder.getClasi().setImageResource(pel.getClasi());


    }

    @Override
    public int getItemCount() {
        return this.peliculas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView titulo;
        private TextView director;
        private ImageView portadas;

        private ImageView clasi;

        public MyViewHolder(View viewElemento) {
            super(viewElemento);

            this.titulo = viewElemento.findViewById(R.id.txtTit);
            this.director = viewElemento.findViewById(R.id.txtDir);
            this.portadas = viewElemento.findViewById(R.id.imgPor);
            this.clasi = viewElemento.findViewById(R.id.imgClasi);

            viewElemento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txt.setText(peliculas.get(getAbsoluteAdapterPosition()).getTitulo());

                    viewElemento.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            txt.setText(peliculas.get(getAbsoluteAdapterPosition()).getTitulo());



                        }
                    });



                }
            });
        }

        public TextView getTitulo() {
            return titulo;
        }

        public TextView getDirector() {
            return director;
        }

        public ImageView getPortadas() {
            return portadas;
        }

        public ImageView getClasi() {
            return clasi;
        }
    }


    public MiAdaptador(ArrayList<Pelicula> peliculas, TextView txt) {
        this.peliculas = peliculas;
        this.txt = txt;

    }
}
