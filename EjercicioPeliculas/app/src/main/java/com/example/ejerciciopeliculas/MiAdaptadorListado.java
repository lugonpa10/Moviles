package com.example.ejerciciopeliculas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class MiAdaptadorListado extends RecyclerView.Adapter<MiAdaptadorListado.MyViewHolder> {
    ArrayList<Pelicula> peliculas;
    Context contexto;

    public MiAdaptadorListado(ArrayList<Pelicula> peliculas, Context contexto){
        this.peliculas =peliculas;
        this.contexto = contexto;
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
        View elemento = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda2,parent,false);
        MyViewHolder mvh = new MyViewHolder(elemento);
        return mvh;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvDir, tvDur,tvSala,tvFech;
        ImageView ivCaratula, ivEdad, ivFavs;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDir = itemView.findViewById(R.id.tvDirector);
            tvDur = itemView.findViewById(R.id.tvDuracion);
            tvSala = itemView.findViewById(R.id.tvSala);
            tvFech = itemView.findViewById(R.id.tvFecha);
            ivCaratula = itemView.findViewById(R.id.ivCaratulaLanzada);
            ivEdad = itemView.findViewById(R.id.ivEdad);
            ivFavs = itemView.findViewById(R.id.ivFav);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedPos = getAdapterPosition();
                    setSelectedPos(selectedPos);
                    Intent lanzarNuevoActivity = new Intent(contexto, ActividadListado.class);
                    lanzarNuevoActivity.putExtra("pelicula_seleccionada", peliculas.get(selectedPos));
                    contexto.startActivity(lanzarNuevoActivity);
                }
            });
        }
        public TextView getTvDir(){
            return tvDir;
        }
        public TextView getTvDur(){
            return tvDur;
        }
        public TextView getTvSala(){
            return tvSala;
        }
        public TextView getTvFech(){
            return tvFech;
        }
        public ImageView getIvCaratula(){
            return ivCaratula;
        }
        public ImageView getIvEdad(){
            return ivEdad;
        }
        public ImageView getIvFavs(){
            return ivFavs;
        }
    }


    @Override
    public void onBindViewHolder(@NonNull MiAdaptadorListado.MyViewHolder holder, int position) {
        Pelicula p = this.peliculas.get(position);
        holder.getTvDir().setText(p.getDirector());
        holder.getTvDur().setText(p.getDuracion()+"");
        holder.getTvFech().setText(p.getFecha()+"");
        holder.getTvSala().setText(p.getSala());
        holder.getIvCaratula().setImageResource(p.getPortada());
        holder.getIvEdad().setImageResource(p.getClasi());
        if (p.getFavorita()){
            holder.getIvFavs().setImageResource(R.drawable.star);
        } else {
            holder.getIvFavs().setImageResource(0);
        }

        if (selectedPos == position){
            holder.itemView.setBackgroundResource(R.color.black);
        } else {
            holder.itemView.setBackgroundResource(R.color.white);
        }
    }

    @Override
    public int getItemCount() {
        return this.peliculas.size();
    }
}