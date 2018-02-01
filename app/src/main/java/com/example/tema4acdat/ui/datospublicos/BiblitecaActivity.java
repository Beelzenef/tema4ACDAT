package com.example.tema4acdat.ui.datospublicos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tema4acdat.R;
import com.example.tema4acdat.pojo.Biblioteca;

public class BiblitecaActivity extends AppCompatActivity {

    private TextView txtV_BiblioDescripcion;
    private TextView txtV_BiblioURL;
    private TextView txtV_BiblioEmail;
    private TextView txtV_BiblioTelefono;

    private Biblioteca bibliotecaObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bibliteca);

        getViews();
        getBiblioteca();
    }

    private void getViews() {
        txtV_BiblioDescripcion = (TextView) findViewById(R.id.txtV_BiblioDescripcion);
        txtV_BiblioURL = (TextView) findViewById(R.id.txtV_BiblioURL);
        txtV_BiblioEmail = (TextView) findViewById(R.id.txtV_BiblioEmail);
        txtV_BiblioTelefono = (TextView) findViewById(R.id.txtV_BiblioTelefono);
    }

    private void getBiblioteca() {
        bibliotecaObservable = new Biblioteca();
        bibliotecaObservable =  getIntent().getExtras().getBundle("Bundle").getParcelable("Biblio");

        txtV_BiblioDescripcion.setText(bibliotecaObservable.getDescripcion());
        txtV_BiblioEmail.setText(bibliotecaObservable.getEmail());
        txtV_BiblioTelefono.setText(bibliotecaObservable.getNumeroTlf());
        txtV_BiblioURL.setText(bibliotecaObservable.getURL());
    }
}
