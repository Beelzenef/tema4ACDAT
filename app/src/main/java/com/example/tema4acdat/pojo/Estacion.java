package com.example.tema4acdat.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Beelzenef on 10/02/2018.
 */

public class Estacion implements Parcelable {

    public static final String TAG = "Estacion";

    @SerializedName("estado")
    @Expose
    private String estado;
    @SerializedName("bicisDisponibles")
    @Expose
    private Integer bicisDisponibles;
    @SerializedName("anclajesDisponibles")
    @Expose
    private Integer anclajesDisponibles;
    @SerializedName("title")
    @Expose
    private String title;

    public Integer getAnclajesDisponibles() {
        return anclajesDisponibles;
    }

    public void setAnclajesDisponibles(Integer anclajesDisponibles) {
        this.anclajesDisponibles = anclajesDisponibles;
    }

    public String getDescription() {
        return title;
    }

    public void setDescription(String description) {
        this.title = description;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.title = estado;
    }

    public Integer getBicisDisponibles() {
        return bicisDisponibles;
    }

    public void setBicisDisponibles(int bicisDisponibles) {
        this.bicisDisponibles = bicisDisponibles;
    }

    public Estacion() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(estado);
        parcel.writeInt(anclajesDisponibles);
        parcel.writeInt(bicisDisponibles);
    }

    private Estacion(Parcel source) {
        this.title = source.readString();
        this.estado = source.readString();
        this.anclajesDisponibles = source.readInt();
        this.bicisDisponibles = source.readInt();
    }

    public static Creator<Estacion> CREATOR = new Creator<Estacion>() {
        @Override
        public Estacion createFromParcel(Parcel parcel) {
            return new Estacion(parcel);
        }

        @Override
        public Estacion[] newArray(int i) {
            return new Estacion[0];
        }
    };

    @Override
    public String toString() {
        return this.title;
    }
}