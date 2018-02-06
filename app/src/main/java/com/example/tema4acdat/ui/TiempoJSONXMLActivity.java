package com.example.tema4acdat.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tema4acdat.R;
import com.example.tema4acdat.network.RestClient;
import com.example.tema4acdat.pojo.Ciudad;
import com.example.tema4acdat.ui.tiempojson.CiudadTiempoJSON;
import com.example.tema4acdat.ui.tiempojson.TiempoJSONActivity;
import com.example.tema4acdat.utils.AnalisisJSON;
import com.example.tema4acdat.utils.AnalisisXML;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TiempoJSONXMLActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> listaCiudades;
    private ArrayAdapter<String> adapter;

    final ArrayList<Ciudad> listaAGuardar = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiempo_jsonxml);

        listView = (ListView) findViewById(R.id.listView);

        cargarListaCiudades();
    }

    public void onClick_guardarPrevisiones (View v) {
        switch (v.getId()) {
            case R.id.btn_GuardarPrevisiones:
                for (int i = 0; i < listaCiudades.size(); i++)
                {
                    descarga("http://api.openweathermap.org/data/2.5/weather?q=" + listaCiudades.get(i) + ",ES&appid=34ed8abe822a0f7b2f3565c48cc88e42", listaCiudades.get(i));
                }
                guardarXML();
                guardarJSON();
                break;
        }
    }

    private void descarga(String web, final String nombreCiudad) {

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
                    listaAGuardar.add((AnalisisJSON.leerTiempo(response, new Ciudad(), nombreCiudad)));
                } catch (Exception e) {
                    Toast.makeText(TiempoJSONXMLActivity.this, "¡Error al mostrar tiempo! :(",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                progreso.dismiss();
                Toast.makeText(TiempoJSONXMLActivity.this, "¡Ha fallado la descarga! :(",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cargarListaCiudades() {
        listaCiudades = new ArrayList<>();
        listaCiudades.add("Malaga");
        listaCiudades.add("Barcelona");
        listaCiudades.add("Zaragoza");
        listaCiudades.add("Bilbao");

        if (adapter == null) {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaCiudades);
            listView.setAdapter(adapter);
        }
        else {
            adapter.clear();
            adapter.addAll(listaCiudades);
        }
    }

    private void guardarXML() {
        try {
            AnalisisXML.crearXML(listaAGuardar, "previsiones.xml");
            Toast.makeText(getApplicationContext(), "XML creado :)", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e) {
            Toast.makeText(getApplicationContext(), "¡Error de creación! :(", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardarJSON() {
        try {
            AnalisisJSON.escribirJSON(listaAGuardar, "previsiones.json");
            Toast.makeText(getApplicationContext(), "JSON creado :)", Toast.LENGTH_SHORT).show();
        }
        catch (IOException | JSONException e) {
            Toast.makeText(getApplicationContext(), "¡Error de creación! :(", Toast.LENGTH_SHORT).show();
        }
    }
}
