package com.example.tema4acdat.ui.tiempojson;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tema4acdat.R;
import com.example.tema4acdat.network.RestClient;
import com.example.tema4acdat.pojo.Ciudad;
import com.example.tema4acdat.utils.AnalisisJSON;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class CiudadTiempoJSON extends AppCompatActivity {

    private String ciudad;

    private TextView txtV_NombreCiudad;
    private TextView txtV_TemperaturasCiudad;
    private TextView txtV_PresionCiudad;
    private TextView txtV_HumedadCiudad;

    private Ciudad ciudadAObservar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciudad_tiempo_json);

        ciudad = getIntent().getExtras().getBundle("Bundle").getString("ciudad");

        String URL = "http://api.openweathermap.org/data/2.5/weather?q=" + ciudad + ",sp&appid=34ed8abe822a0f7b2f3565c48cc88e42";

        findViews();

        descarga(URL);
    }

    private void findViews() {
        txtV_NombreCiudad = (TextView) findViewById(R.id.txtV_NombreCiudad);
        txtV_TemperaturasCiudad = (TextView) findViewById(R.id.txtV_TemperaturasCiudad);
        txtV_PresionCiudad = (TextView) findViewById(R.id.txtV_PresionCiudad);
        txtV_HumedadCiudad = (TextView) findViewById(R.id.txtV_HumedadCiudad);
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
                    ciudadAObservar = AnalisisJSON.leerTiempo(response);
                    ciudadAObservar.setNombre(ciudad);
                    mostrar();
                } catch (Exception e) {
                    Toast.makeText(CiudadTiempoJSON.this, "¡Error al mostrar tiempo! :(",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                progreso.dismiss();
                Toast.makeText(CiudadTiempoJSON.this, "¡Ha fallado la descarga! :(",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mostrar() {
        txtV_NombreCiudad.setText(ciudadAObservar.getNombre());
        txtV_HumedadCiudad.setText(Double.toString(ciudadAObservar.getHumedad()));
        txtV_PresionCiudad.setText(Double.toString(ciudadAObservar.getPresion()));
        txtV_TemperaturasCiudad.setText(Double.toString(ciudadAObservar.getTemperatura()));
    }
}
