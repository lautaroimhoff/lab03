package com.example.lautaro.lab03;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Trabajo> trabajos = new ArrayList<Trabajo>();
    ListView lv;
    static int codigoAlta = 1;
    MiAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        trabajos.addAll(Arrays.asList(Trabajo.TRABAJOS_MOCK));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String action = "android.intent.action.ALTAOFERTA";
                Intent intent = new Intent(action);
                startActivityForResult(intent ,codigoAlta);



            }
        });


        lv = (ListView) findViewById(R.id.listView);
        adapter = new MiAdapter(getApplicationContext(), trabajos);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Trabajo trabajo = adapter.getItem(i);
                Toast.makeText(getApplicationContext(),trabajo.getDescripcion(),Toast.LENGTH_LONG).show();
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == codigoAlta) {
            if (resultCode == RESULT_OK) {

                Trabajo trabajo = (Trabajo) data.getParcelableExtra("trabajo");
                trabajos.add(trabajo);
                lv.setAdapter(null);
                adapter = new MiAdapter(getApplicationContext(), trabajos);
                lv.setAdapter(adapter);



                Toast.makeText(MainActivity.this, "Trabajo agregado"
                        , Toast.LENGTH_LONG).show();


            }
            if(resultCode == RESULT_CANCELED) {

                Toast.makeText(MainActivity.this, "EL PAGO HA SIDO CANCELADO", Toast.LENGTH_LONG).show();


            }

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
