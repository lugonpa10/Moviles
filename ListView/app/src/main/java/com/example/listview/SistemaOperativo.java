package com.example.listview;

public class SistemaOperativo {
    private String nombre; // Nombre del sistema operativo
    private String ano; // Año de salida
    private int logo; // Int que representa la imagen en el fichero R

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getAno() {
        return ano;
    }
    public void setAno(String ano) {
        this.ano = ano;
    }
    public int getLogo() {
        return logo;
    }
    public void setLogo(int logo) {
        this.logo = logo;
    }
    public SistemaOperativo(String nombre, String ano, int logo) {
        this.nombre = nombre;
        this.ano = ano;
        this.logo = logo;
    }

}
