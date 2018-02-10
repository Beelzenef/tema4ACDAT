package com.example.tema4acdat.network;

import com.example.tema4acdat.pojo.DatosGSON;
import com.example.tema4acdat.pojo.Estacion;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Retrofit
 */

public class ApiService {
    public interface GitHubClient {

        @GET("servicio/urbanismo-infraestructuras/estacion-bicicleta.json")
        Call<DatosGSON> getEstaciones();
    }

}