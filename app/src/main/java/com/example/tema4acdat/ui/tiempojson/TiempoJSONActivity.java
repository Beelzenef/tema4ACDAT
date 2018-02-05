package com.example.tema4acdat.ui.tiempojson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tema4acdat.R;

import java.util.ArrayList;

public class TiempoJSONActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener {

    private ListView listView;
    private ArrayList<String> listaCiudades;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiempo_json);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);

        cargarListaCiudades();
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Bundle b = new Bundle();
        b.putString("ciudad", listaCiudades.get(position));

        Intent intent = new Intent(TiempoJSONActivity.this, CiudadTiempoJSON.class);
        intent.putExtra("Bundle", b);
        startActivity(intent);
    }
}
