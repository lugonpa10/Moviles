package com.example.pruebaapi;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiRest {
    public void subirDeportista(String nombre,String deporte){
        new Thread(() -> {
            try {
                URL url = new URL("http://192.130.0.125:8080/tema5maven/rest/deportistas/android");
                HttpURLConnection con = (HttpURLConnection) url.openConnection(); //Abrir conexion
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-type","application/json");
                con.setDoOutput(true); //Escribir en el body

                JSONObject json = new JSONObject();
                json.put("nombre",nombre);
                json.put("deporte",deporte);

                System.out.println(json);//Sale en el logcat

                try(OutputStream os = con.getOutputStream()) {
                    os.write(json.toString().getBytes(StandardCharsets.UTF_8)); //Enviar body

                }catch (IOException e){

                }
               int code = con.getResponseCode(); //Forzar envio
                Log.i("CODIGO APIREST","El codigo resultante es " + code);



            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }).start();
    }
}
