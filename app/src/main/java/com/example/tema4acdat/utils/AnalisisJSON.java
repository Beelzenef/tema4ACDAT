package com.example.tema4acdat.utils;

import android.os.Environment;
import android.util.Log;

import com.example.tema4acdat.pojo.Biblioteca;
import com.example.tema4acdat.pojo.Ciudad;
import com.example.tema4acdat.pojo.Estacion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Análisis de JSON
 */

public class AnalisisJSON {

    public static ArrayList<Biblioteca> leerBibliotecas(JSONObject texto) throws JSONException {
        ArrayList<Biblioteca> listaBibliotecas = new ArrayList<>();

        Biblioteca bibliotecaLeída;

        JSONArray listaBibliosJSON = texto.getJSONArray("elements");
        JSONObject bibliotecaJSON;

        for (int i = 0; i < 20; i++) {
            bibliotecaLeída = new Biblioteca();

            // Leyendo y cargando datos
            bibliotecaJSON = listaBibliosJSON.getJSONObject(i);

            bibliotecaLeída.setDescripcion(bibliotecaJSON.getString("descripcio"));
            bibliotecaLeída.setURL(bibliotecaJSON.getString("url_general"));
            bibliotecaLeída.setEmail(bibliotecaJSON.getJSONArray("email").getString(0));
            bibliotecaLeída.setNumeroTlf(bibliotecaJSON.getJSONArray("telefon_contacte").getString(0));

            listaBibliotecas.add(bibliotecaLeída);
        }

        return listaBibliotecas;
    }

    public static ArrayList<Estacion> leerEstaciones (JSONObject texto) throws JSONException {
        ArrayList<Estacion> listaEstaciones = new ArrayList<>();

        Estacion estacionLeida;

        JSONArray listaEstacionesJSON = texto.getJSONArray("result");
        JSONObject estacionJSON;

        for (int i = 0; i < listaEstaciones.size() ; i++) {
            estacionJSON = listaEstacionesJSON.getJSONObject(i);

            estacionLeida = new Estacion();
            estacionLeida.setDireccion(estacionJSON.getString("title"));
            estacionLeida.setEstado(estacionJSON.getString("estado"));
            estacionLeida.setBicisDisponibles(estacionJSON.getInt("bicisDisponibles"));
            estacionLeida.setAnclajes(estacionJSON.getInt("anclajesDisponibles"));

            listaEstaciones.add(estacionLeida);
        }

        return listaEstaciones;
    }

    public static double leerCambioDivisas(JSONObject texto) throws JSONException {
        double cambio = 0;

        JSONObject eurosJSON = texto.getJSONObject("eur");
        cambio = eurosJSON.getDouble("rate");

        return cambio;
    }

    public static Ciudad leerTiempo (JSONObject texto, Ciudad c, String n) throws JSONException {
        c = new Ciudad();

        JSONObject tiempoJSON = texto.getJSONObject("main");
        c.setNombre(n);
        c.setTemperatura(tiempoJSON.getDouble("temp"));
        c.setHumedad(tiempoJSON.getDouble("humidity"));
        c.setPresion(tiempoJSON.getDouble("pressure"));

        return c;
    }

    public static void escribirJSON(ArrayList<Ciudad> ciudads, String fichero) throws IOException, JSONException
    {
        OutputStreamWriter out;
        File miFichero;
        JSONObject objeto;
        JSONObject previsionesciudades;
        JSONArray lista;
        miFichero = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), fichero);
        out = new FileWriter(miFichero);
        //crear objeto JSON
        objeto = new JSONObject();
        objeto.put("info", "https://about.me/Beelzenef");

        previsionesciudades = new JSONObject();

        lista = new JSONArray();

        for (int i = 0; i < ciudads.size(); i++) {
            JSONObject ciudadObservable = new JSONObject();
            ciudadObservable.put("ciudad", ciudads.get(i).getNombre());
            ciudadObservable.put("temperatura", ciudads.get(i).getTemperatura());
            ciudadObservable.put("presion", ciudads.get(i).getPresion());
            ciudadObservable.put("humedad", ciudads.get(i).getHumedad());

            lista.put(ciudadObservable);
        }

        objeto.put("ciudades", lista);
        previsionesciudades.put("previsiones", objeto);
        out.write(previsionesciudades.toString(4)); //tabulación de 4 caracteres
        out.flush();
        out.close();
        Log.i("info", objeto.toString());
    }
}
