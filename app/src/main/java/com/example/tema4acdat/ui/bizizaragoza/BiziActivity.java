package com.example.tema4acdat.ui.bizizaragoza;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tema4acdat.R;
import com.example.tema4acdat.network.RestClient;
import com.example.tema4acdat.pojo.Estacion;
import com.example.tema4acdat.pojo.adapter.AdapterBizis;
import com.example.tema4acdat.pojo.adapter.ClickListener;
import com.example.tema4acdat.pojo.adapter.RecyclerTouchListener;
import com.example.tema4acdat.utils.AnalisisJSON;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class BiziActivity extends AppCompatActivity implements View.OnClickListener {

    private static String DATA = "https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/estacion-bicicleta.json";

    private RecyclerView recyclerView;
    private ArrayList<Estacion> listaEstaciones;
    private AdapterBizis adapter;
    private Button btn_getEstaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizi);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new AdapterBizis();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Estacion estacionElegida = listaEstaciones.get(position);

                Bundle b = new Bundle();
                b.putParcelable(Estacion.TAG, estacionElegida);
                Intent intent = new Intent(BiziActivity.this, EstacionActivity.class);
                intent.putExtra("Bundle", b);
                startActivity(intent);
            }
        }));

        btn_getEstaciones = (Button) findViewById(R.id.fab_getEstaciones);
        btn_getEstaciones.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_getEstaciones)
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
        if (listaEstaciones != null) {
            if (adapter == null) {
               adapter = new AdapterBizis();
            }
            adapter.addAll(listaEstaciones);
            recyclerView.setAdapter(adapter);
            Toast.makeText(this, "Datos cargados", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(getApplicationContext(), "Error al crear la lista", Toast.LENGTH_SHORT).show();
    }
}