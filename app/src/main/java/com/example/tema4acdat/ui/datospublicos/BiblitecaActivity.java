package com.example.tema4acdat.ui.datospublicos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tema4acdat.R;
import com.example.tema4acdat.pojo.Biblioteca;

import java.util.ArrayList;
import java.util.List;

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

        txtV_BiblioDescripcion = (TextView) findViewById(R.id.txtV_BiblioDescripcion);
        txtV_BiblioURL = (TextView) findViewById(R.id.txtV_BiblioURL);
        txtV_BiblioEmail = (TextView) findViewById(R.id.txtV_BiblioEmail);
        txtV_BiblioTelefono = (TextView) findViewById(R.id.txtV_BiblioTelefono);

        bibliotecaObservable = new Biblioteca();
        bibliotecaObservable = (Biblioteca) getIntent().getExtras().getBundle("Bundle").getParcelable("Biblio");

        txtV_BiblioDescripcion.setText(bibliotecaObservable.getDescripcion());
        txtV_BiblioEmail.setText(bibliotecaObservable.getEmail());
        txtV_BiblioTelefono.setText(bibliotecaObservable.getNumeroTlf());
        txtV_BiblioURL.setText(bibliotecaObservable.getURL());
    }

    public void onClick_URLBiblio(View view) {

        switch (view.getId()) {
            case R.id.txtV_BiblioURL:
                abrirURL();
                break;
            case R.id.txtV_BiblioEmail:
                enviarEmail();
                break;
        }
    }

    private void abrirURL() {
        Uri uri = Uri.parse(txtV_BiblioURL.getText().toString());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else
            Toast.makeText(getApplicationContext(), "No hay un navegador", Toast.LENGTH_SHORT).show();
    }

    private void enviarEmail() {

        List<Intent> emailAppLauncherIntents = new ArrayList<>();

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, txtV_BiblioEmail.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Asunto");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Contenido");

        PackageManager packageManager = getPackageManager();

        //All installed apps that can handle email intent:
        List<ResolveInfo> emailApps = packageManager.queryIntentActivities(emailIntent, PackageManager.MATCH_ALL);

        for (ResolveInfo resolveInfo : emailApps) {
            String packageName = resolveInfo.activityInfo.packageName;
            Intent launchIntent = packageManager.getLaunchIntentForPackage(packageName);
            emailAppLauncherIntents.add(launchIntent);
        }

        //Create chooser
        Intent chooserIntent = Intent.createChooser(new Intent(), "Select email app:");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, emailAppLauncherIntents.toArray(new Parcelable[emailAppLauncherIntents.size()]));
        startActivity(chooserIntent);
    }

}
