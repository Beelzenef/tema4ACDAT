package com.example.tema4acdat.utils;

import android.os.Environment;
import android.util.Xml;

import com.example.tema4acdat.pojo.Ciudad;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Analizador de XML
 */

public class AnalisisXML {

    public static void crearXML(ArrayList<Ciudad> listaCiudades, String fichero) throws IOException {
        FileOutputStream fout;
        fout = new FileOutputStream(new File(Environment.getExternalStorageDirectory().getAbsolutePath(), fichero));
        XmlSerializer serializer = Xml.newSerializer();
        serializer.setOutput(fout, "UTF-8");
        serializer.startDocument(null, true);

        // Settear tabulación
        serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);

        serializer.startTag(null, "previsiones");
        for (Ciudad noticia : listaCiudades) {

            serializer.startTag(null, "ciudad");

            serializer.startTag(null, "nombre");
            serializer.text(noticia.getNombre());
            serializer.endTag(null, "nombre");

            serializer.startTag(null, "humedad");
            serializer.text(Double.toString(noticia.getHumedad()));
            serializer.endTag(null, "humedad");

            serializer.startTag(null, "temperatura");
            serializer.text(Double.toString(noticia.getTemperatura()));
            serializer.endTag(null, "temperatura");

            serializer.startTag(null, "presion");
            serializer.text(Double.toString(noticia.getPresion()));
            serializer.endTag(null, "presion");

            serializer.endTag(null, "ciudad");
        }

        serializer.endTag(null, "previsiones");
        serializer.endDocument();
        serializer.flush();
        fout.close();
    }
}
