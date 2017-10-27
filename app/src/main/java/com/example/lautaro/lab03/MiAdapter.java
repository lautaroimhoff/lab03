package com.example.lautaro.lab03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by st on 19/09/2017.
 */
public class MiAdapter extends ArrayAdapter<Trabajo> {

    LayoutInflater inflater;

    MiAdapter(Context context, List<Trabajo> items) {
        super(context, R.layout.content_ofertalaboral, items);
        inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        DecimalFormat df = new DecimalFormat("#.##");
        View row=convertView;
        if(row==null) row=inflater.inflate(R.layout.content_ofertalaboral, parent, false);

        ViewHolder holder=(ViewHolder)row.getTag();
        if(holder==null){
            holder= new ViewHolder(row);
            row.setTag(holder);
        }

        holder.categoria.setText(this.getItem(position).getCategoria().getDescripcion());
        holder.descripcion.setText(this.getItem(position).getDescripcion());

        switch (this.getItem(position).getMonedaPago()){
            case 1:
                holder.monedaPago.setImageResource(R.drawable.us);
                break;
            case 2:
                holder.monedaPago.setImageResource(R.drawable.eu);
                break;
            case 3:
                holder.monedaPago.setImageResource(R.drawable.ar);
                break;
            case 4:
                holder.monedaPago.setImageResource(R.drawable.uk);
                break;
            case 5:
                holder.monedaPago.setImageResource(R.drawable.br);
                break;
            default:
                break;
        }
        boolean ingles = this.getItem(position).getRequiereIngles();
        holder.requiereIngles.setChecked(ingles);
        holder.fechaentrega.setText(this.getItem(position).getFechaEntrega().toString());
        holder.horasPresupuestadas.setText(Integer.toString(this.getItem(position).getHorasPresupuestadas()));
        holder.precioMaximoHora.setText(df.format(this.getItem(position).getPrecioMaximoHora()).toString());




return row;
    }


}