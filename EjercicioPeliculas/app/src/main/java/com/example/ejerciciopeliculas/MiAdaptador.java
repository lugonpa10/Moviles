package com.example.ejerciciopeliculas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MyViewHolder> {
    ArrayList<Pelicula> peliculas;
    TextView fija;
    public MiAdaptador(ArrayList<Pelicula> peliculas, TextView fija){
        this.peliculas = peliculas;
        this.fija = fija;
    }
    int selectedPos = RecyclerView.NO_POSITION;
    public int getSelectedPos() {
        return selectedPos;
    }
    public void setSelectedPos(int selectedPos){
        if (selectedPos == this.selectedPos) {
            this.selectedPos = RecyclerView.NO_POSITION;
            notifyItemChanged(selectedPos);
        } else {
            if (this.selectedPos != RecyclerView.NO_POSITION){
                notifyItemChanged(this.selectedPos);
            }
            this.selectedPos = selectedPos;
            notifyItemChanged(selectedPos);
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elemento = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda,parent,false);
        MyViewHolder mvh = new MyViewHolder(elemento);
        return mvh;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvDirector;
        ImageView imageView, imageView2;
        public MyViewHolder(View viewElemento){
            super(viewElemento);
            tvNombre = viewElemento.findViewById(R.id.textView);
            tvDirector = viewElemento.findViewById(R.id.textView2);
            imageView = viewElemento.findViewById(R.id.imageView);
            imageView2 = viewElemento.findViewById(R.id.imageView2);
            viewElemento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedPos = getAdapterPosition();
                    setSelectedPos(selectedPos);
                }
            });
        }
        public TextView obtenerNombre(){
            return tvNombre;
        }
        public TextView obtenerDirector(){
            return  tvDirector;
        }
        public ImageView obtenerPortada(){
            return  imageView;
        }
        public ImageView obtenerEdad(){
            return imageView2;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MiAdaptador.MyViewHolder holder, int position) {
        Pelicula p = this.peliculas.get(position);
        holder.obtenerNombre().setText(p.getTitulo());
        holder.obtenerDirector().setText(p.getDirector());
        holder.obtenerPortada().setImageResource(p.getPortada());
        holder.obtenerEdad().setImageResource(p.getClasi());
        if (selectedPos == position){
            holder.itemView.setBackgroundResource(R.color.black);
            fija.setText(p.getTitulo());
        } else {
            holder.itemView.setBackgroundResource(R.color.white);
        }
    }

    @Override
    public int getItemCount() {
        return this.peliculas.size();
    }
}