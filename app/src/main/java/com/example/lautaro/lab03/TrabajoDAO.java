package com.example.lautaro.lab03;

/**
 * Created by Lautaro on 31/10/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;


public class TrabajoDAO {
    ArrayList<Trabajo> trabajos = new ArrayList<Trabajo>();
    private String file = "ofertas.json";
    public TrabajoDAO(Context context){
        leerArchivo(context);


    }
    public ArrayList<Trabajo> getTrabajos(){
     return trabajos;
    }

    public void  guardarArchivo(String textoIngresado , Context context){

        try {
            FileOutputStream fOut = context.openFileOutput(file, MODE_PRIVATE);

            fOut.write(textoIngresado.trim().getBytes());
            fOut.close();
        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }


    }
    public void leerArchivo(Context context){
        try {
            FileInputStream fin = context.openFileInput(file);
            int c;
            String temp;
            temp = "";

            while( (c = fin.read()) != -1){
                temp = temp + Character.toString((char)c);
            }

            JsonParser parser = new JsonParser();
            Gson json = new Gson();
            JsonArray datos = new JsonArray();
            datos = (JsonArray) parser.parse(temp);
            for (int i=0; i<datos.size();i++) {
                JsonElement object = datos.get(i);
                JsonObject jTrabajo = (JsonObject) object;
                Trabajo trabajo = json.fromJson(object , Trabajo.class);
                trabajos.add(trabajo);
                System.out.println(trabajo.getDescripcion());
            }








             //dumpJSONElement(datos);
        }
        catch(Exception e){


        }



    }


    public void guardarTrabajo(Trabajo trabajo, Context context){
        Gson gson = new Gson();
        trabajos.add(trabajo);
        String json = gson.toJson(trabajos);
        guardarArchivo(json,context);
        }
}