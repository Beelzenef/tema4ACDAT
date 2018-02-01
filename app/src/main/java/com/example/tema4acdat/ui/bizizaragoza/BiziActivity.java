package com.example.tema4acdat.ui.bizizaragoza;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tema4acdat.R;
import com.example.tema4acdat.network.RestClient;
import com.example.tema4acdat.pojo.Estacion;
import com.example.tema4acdat.utils.AnalisisJSON;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class BiziActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static String DATA = "https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/estacion-bicicleta.json";

    private ListView lista;
    private ArrayList<Estacion> listaEstaciones;
    private ArrayAdapter<Estacion> adapter;
    private FloatingActionButton fab_updatePosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizi);

        lista = (ListView) findViewById(R.id.listView);
        lista.setOnItemClickListener(this);
        fab_updatePosts = (FloatingActionButton) findViewById(R.id.fab_getEstaciones);
        fab_updatePosts.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == fab_updatePosts)
            descarga(DATA);
    }

    private void descarga(String web) {
        final ProgressDialog progreso = new ProgressDialog(this);
        RestClient.get(web, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progreso.setMessage("Conectando...");
                progreso.setCancelable(true);
                progreso.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    progreso.dismiss();
                    // Cargar estaciones
                    listaEstaciones = AnalisisJSON.leerEstaciones(response);
                    mostrar();
                } catch (Exception e) {
                    Toast.makeText(BiziActivity.this, "¡Error al mostrar estaciones! :(",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                progreso.dismiss();
                Toast.makeText(BiziActivity.this, "¡Ha fallado la descarga! :(",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mostrar() {
        if (listaEstaciones != null)
            if (adapter == null) {
                adapter = new ArrayAdapter<Estacion>(this, android.R.layout.simple_list_item_1, listaEstaciones);
                lista.setAdapter(adapter);
            }
            else {
                adapter.clear();
                adapter.addAll(listaEstaciones);
            }
        else
            Toast.makeText(getApplicationContext(), "Error al crear la lista", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Estacion estacionElegida = listaEstaciones.get(position);

        Bundle b = new Bundle();
        b.putParcelable(Estacion.TAG, estacionElegida);
        Intent intent = new Intent(BiziActivity.this, EstacionActivity.class);
        intent.putExtra("Bundle", b);
        startActivity(intent);

    }
}
