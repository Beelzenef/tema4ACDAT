package com.example.tema4acdat.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tema4acdat.R;
import com.example.tema4acdat.ui.bizizaragoza.BiziActivity;
import com.example.tema4acdat.ui.datospublicos.DatosActivity;
import com.example.tema4acdat.ui.tiempojson.TiempoJSONActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick_lanzarExs(View v) {
        Intent unIntent = null;

        switch (v.getId()) {
            case R.id.btn_bizizaragoza:
                unIntent = new Intent(MainActivity.this, BiziActivity.class);
                break;
            case R.id.btn_cambioDivisas:
                unIntent = new Intent(MainActivity.this, DivisasActivity.class);
                break;
            case R.id.btn_datos:
                unIntent = new Intent(MainActivity.this, DatosActivity.class);
                break;
            case R.id.btn_tiempoJSON:
                unIntent = new Intent(MainActivity.this, TiempoJSONActivity.class);
                break;
            case R.id.btn_tiempoJSONXML:
                unIntent = new Intent(MainActivity.this, TiempoJSONXMLActivity.class);
                break;
        }

        startActivity(unIntent);
    }
}
