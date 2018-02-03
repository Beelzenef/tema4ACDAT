package com.example.tema4acdat.utils;

/**
 * Conversor de divisas
 */

public final class Conversor {

    // Variables
    private double cambioMoneda;

    // Constructores

    public Conversor() {
        cambioDefault();
    }

    // Métodos

    public String cambioADolares(String valor) {
        return Double.toString(Double.parseDouble(valor) / cambioMoneda);
    }

    public String cambioAEuros(String valor) {
        return Double.toString(Double.parseDouble(valor) * cambioMoneda);
    }

    public void setCambioMoneda(double c) {
        this.cambioMoneda = c;
    }

    public void cambioDefault() {
        this.cambioMoneda = 0.84;
    }

    public double getCambioMoneda() {
        return cambioMoneda;
    }
}
