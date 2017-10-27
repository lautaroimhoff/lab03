package com.example.lautaro.lab03;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by Lautaro on 11/10/2017.
 */

public class AltaOferta extends AppCompatActivity {
    EditText descipcion;
    EditText horas;
    Spinner categoria;
    EditText precioxhora;
    EditText fechaentrega;
    Spinner monedas;
    CheckBox ingles;
    Button guardar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_oferta);
        descipcion = (EditText) findViewById(R.id.etDescripcion);
        horas = (EditText) findViewById(R.id.etHoras);
        categoria = (Spinner) findViewById(R.id.sCategoria);
        precioxhora = (EditText) findViewById(R.id.etPrecio);
        fechaentrega = (EditText) findViewById(R.id.etFecha);
        monedas = (Spinner) findViewById(R.id.Smoneda);
        ingles = (CheckBox) findViewById(R.id.CBingles);
        guardar = (Button) findViewById(R.id.bguardar);
        //1 US$ 2Euro 3 AR$- 4 Libra 5 R$
        String[] datos = {"US$","Euro","AR$","Libra","R$"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        monedas.setAdapter(adaptador);
        ArrayAdapter<Categoria> adaptador2 = new ArrayAdapter<Categoria>(this,android.R.layout.simple_spinner_item,Categoria.CATEGORIAS_MOCK);
        categoria.setAdapter(adaptador2);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Trabajo.TRABAJOS_MOCK.length;
                Trabajo trabajo = new Trabajo(id,"");
                trabajo.setDescripcion(descipcion.getText().toString());
                Categoria cat = new Categoria();
                cat = (Categoria) categoria.getSelectedItem();
                trabajo.setCategoria(cat);

                java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd/MM/yy");
                Date fecha = new Date();
                try {
                fecha = df.parse(fechaentrega.getText().toString());
                trabajo.setPrecioMaximoHora(Double.parseDouble(precioxhora.getText().toString().trim()));
                 trabajo.setHorasPresupuestadas(Integer.parseInt(horas.getText().toString().trim()));
                } catch (ParseException e) {e.printStackTrace();
                }
                trabajo.setFechaEntrega(fecha);
                int moneda =(int) (long)monedas.getSelectedItemId() + 1 ;
                trabajo.setMonedaPago(1);
                trabajo.setRequiereIngles(ingles.isChecked());
                Intent resultado = getIntent();


                resultado.putExtra("trabajo",  trabajo);
                setResult(Activity.RESULT_OK,resultado);
                finish();


            }
        });

    }


}
