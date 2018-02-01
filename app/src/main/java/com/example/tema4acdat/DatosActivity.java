package com.example.tema4acdat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tema4acdat.network.RestClient;
import com.example.tema4acdat.pojo.Biblioteca;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class DatosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String WEB = "http://do.diba.cat/api/dataset/biblioteques/format/json";

    private ListView listView;
    private ArrayList<Biblioteca> listaBibliotecas;
    private ArrayAdapter<Biblioteca> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        listaBibliotecas = new ArrayList<>();

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
    }

    public void onClick_obtenerDatos (View v) {
        switch (v.getId()) {
            case R.id.btn_obtenerDatos:
                descarga(WEB);
                break;
        }
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
                    // Cargar bibliotecas
                    mostrar();
                } catch (Exception e) {
                    Toast.makeText(DatosActivity.this, "¡Error al mostrar contactos! :(",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                progreso.dismiss();
                Toast.makeText(DatosActivity.this, "¡Ha fallado la descarga! :(",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mostrar() {
        if (listaBibliotecas != null)
            if (adapter == null) {
                adapter = new ArrayAdapter<Biblioteca>(this, android.R.layout.simple_list_item_1, listaBibliotecas);
                listView.setAdapter(adapter);
            }
            else {
                adapter.clear();
                adapter.addAll(listaBibliotecas);
            }
        else
            Toast.makeText(getApplicationContext(), "Error al crear la lista", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Bundle b = new Bundle();
        b.putParcelable("Biblio", listaBibliotecas.get(position));

        Intent intent = new Intent(DatosActivity.this, BiblitecaActivity.class);
        intent.putExtra("Bundle", b);
        startActivity(intent);

    }
}
