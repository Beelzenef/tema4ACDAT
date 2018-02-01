package com.example.tema4acdat.utils;


import com.example.tema4acdat.pojo.Biblioteca;
import com.example.tema4acdat.pojo.Estacion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
}
