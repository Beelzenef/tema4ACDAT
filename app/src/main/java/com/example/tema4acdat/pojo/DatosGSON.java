package com.example.tema4acdat.pojo;

/**
 * Created by Beelzenef on 01/02/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Clase Estación de bicletas
 */

public class DatosGSON {

    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("start")
    @Expose
    private Integer start;
    @SerializedName("rows")
    @Expose
    private Integer rows;
    @SerializedName("result")
    @Expose
    private ArrayList<Estacion> result = null;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public ArrayList<Estacion> getResult() {
        return result;
    }

    public void setResult(ArrayList<Estacion> result) {
        this.result = result;
    }

}