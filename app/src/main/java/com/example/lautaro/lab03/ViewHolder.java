package com.example.lautaro.lab03;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by st on 19/09/2017.
 */
public class ViewHolder {
TextView categoria = null;
TextView descripcion = null;
TextView horasPresupuestadas = null;
TextView precioMaximoHora = null;
ImageView monedaPago = null;
TextView fechaentrega = null;
CheckBox requiereIngles = null;



ViewHolder(View base){
    this.categoria = (TextView) base.findViewById(R.id.textView);
    this.descripcion = (TextView) base.findViewById(R.id.textView2);
    this.horasPresupuestadas = (TextView) base.findViewById(R.id.textView3);
    this.precioMaximoHora = (TextView) base.findViewById(R.id.textView4);
    this.fechaentrega = (TextView) base.findViewById(R.id.textView5);

    this.monedaPago = (ImageView) base.findViewById(R.id.ImageView);

    this.requiereIngles = (CheckBox) base.findViewById(R.id.checkbox);
    requiereIngles.setEnabled(false);








}

}
