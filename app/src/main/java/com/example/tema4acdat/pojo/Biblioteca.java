package com.example.tema4acdat.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * POJO Biblioteca
 */

public class Biblioteca implements Parcelable {

    String descripcion;
    String numeroTlf;
    String URL;
    String email;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumeroTlf() {
        return numeroTlf;
    }

    public void setNumeroTlf(String numeroTlf) {
        this.numeroTlf = numeroTlf;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return this.descripcion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.descripcion);
        parcel.writeString(this.numeroTlf);
        parcel.writeString(this.URL);
        parcel.writeString(this.email);
    }

    public Biblioteca() {
    }

    private Biblioteca (Parcel parcel) {
        this.descripcion = parcel.readString();
        this.numeroTlf = parcel.readString();
        this.URL = parcel.readString();
        this.email = parcel.readString();
    }

    public static final Creator<Biblioteca> CREATOR = new Creator<Biblioteca>() {
        @Override
        public Biblioteca createFromParcel(Parcel parcel) {
            return new Biblioteca(parcel);
        }

        @Override
        public Biblioteca[] newArray(int i) {
            return new Biblioteca[0];
        }
    };
}
