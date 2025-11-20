package com.example.ejercicio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

public class adaptadorListado extends RecyclerView.Adapter<adaptadorListado.MyViewHolder> {

    ArrayList<Pelicula> peliculas;



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
        holder.getSalaCine().setText(pel.getSala());
        holder.getEstreno().setText(pel.getFecha()+"");
        holder.getDuracion().setText(pel.getDuracion());
        holder.getFavorito().setImageResource(R.drawable.estrella);

        holder.getDirector().setText(pel.getDirector());
        holder.getPortadas().setImageResource(pel.getPortada());
        holder.getClasi().setImageResource(pel.getClasi());


    }

    @Override
    public int getItemCount() {
        return this.peliculas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView director;
        private ImageView portadas;
        private ImageView clasi;
        private TextView duracion;
        private TextView salaCine;

        private ImageView favorito;

        private TextView estreno;


        public MyViewHolder(View viewElemento) {
            super(viewElemento);

            this.duracion = viewElemento.findViewById(R.id.txtDuracion);
            this.estreno = viewElemento.findViewById(R.id.txtEstreno);
            this.salaCine = viewElemento.findViewById(R.id.txtSalaCine);
            this.director = viewElemento.findViewById(R.id.txtDir);
            this.portadas = viewElemento.findViewById(R.id.imgPor);
            this.clasi = viewElemento.findViewById(R.id.imgClasi);
            this.favorito = viewElemento.findViewById(R.id.imgFavorito);

            viewElemento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });
        }

        public TextView getDuracion() {
            return duracion;
        }

        public TextView getSalaCine(){
            return salaCine;
        }
        public TextView getEstreno(){
            return estreno;
        }

        public ImageView getFavorito(){
            return favorito;
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


    public adaptadorListado(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;


    }
}

