package com.example.tema4acdat.pojo;

/**
 * When everything fails...
 */

public class FailureEvent {

    public final String msg;
    public final int status;

    public FailureEvent(String m, int s)
    {
        this.msg = m;
        this.status = s;
    }
}
