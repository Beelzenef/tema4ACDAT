package com.example.tema4acdat.utils;

import android.os.Environment;
import android.util.Xml;

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

    public static String analizar(String texto) throws XmlPullParserException, IOException {
        StringBuilder cadena = new StringBuilder();
        XmlPullParser xpp = Xml.newPullParser();
        xpp.setInput(new StringReader(texto));
        int eventType = xpp.getEventType();
        cadena.append("Leyendo XML:\n ");
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_DOCUMENT)
                cadena.append("START DOCUMENT \n");
            if (eventType == XmlPullParser.START_TAG)
                cadena.append("START TAG: " + xpp.getName() + "\n");
            if (eventType == XmlPullParser.TEXT)
                cadena.append("CONTENT: " + xpp.getText() + "\n");
            if (eventType == XmlPullParser.END_TAG)
                cadena.append("END TAG: " + xpp.getName() + "\n");
            eventType = xpp.next();
        }

        cadena.append("END DOCUMENT");
        return cadena.toString();
    }

    public static String analizarRSS(File file) throws NullPointerException, XmlPullParserException, IOException {

        boolean dentroItem = false;

        StringBuilder builder = new StringBuilder();
        XmlPullParser xpp = Xml.newPullParser();
        xpp.setInput(new FileReader(file));

        int eventType = xpp.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (xpp.getName().equals("item"))
                        dentroItem = true;

                    if (dentroItem && xpp.getName().equals("title")) {
                        builder.append("Post: " + xpp.nextText() + "\n");
                        dentroItem = false;
                    }
                    break;
            }
            eventType = xpp.next();
        }
        return builder.toString();
    }

    public static ArrayList<Noticia> analizarNoticias(File file) throws XmlPullParserException, IOException {
        int eventType;

        ArrayList<Noticia> noticias = null;
        Noticia actual = null;
        boolean dentroItem = false;

        XmlPullParser xpp = Xml.newPullParser();
        xpp.setInput(new FileReader(file));
        eventType = xpp.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    noticias = new ArrayList<>();
                    break;
                case XmlPullParser.START_TAG:
                    if (xpp.getName().equals("item")) {
                        dentroItem = true;
                        actual = new Noticia();
                    }

                    if (dentroItem && xpp.getName().equals("title")) {
                        actual.setTitle(xpp.nextText());
                    }
                    if (dentroItem && xpp.getName().equals("link")) {
                        actual.setLink(xpp.nextText());
                    }
                    if (dentroItem && xpp.getName().equals("description")) {
                        actual.setDescription(xpp.nextText());
                    }
                    if (dentroItem && xpp.getName().equals("pubDate")) {
                        actual.setPubDate(xpp.nextText());
                    }

                    break;
                case XmlPullParser.END_TAG:
                    if (xpp.getName().equals("item")) {
                        dentroItem = false;
                        noticias.add(actual);
                    }
                    break;
            }
            eventType = xpp.next();
        }
        //devolver el array de noticias
        return noticias;
    }

    public static void crearXML(ArrayList<Noticia> listaNoticias, String fichero) throws IOException {
        FileOutputStream fout;
        fout = new FileOutputStream(new File(Environment.getExternalStorageDirectory().getAbsolutePath(), fichero));
        XmlSerializer serializer = Xml.newSerializer();
        serializer.setOutput(fout, "UTF-8");
        serializer.startDocument(null, true);

        // Settear tabulación
        serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);

        serializer.startTag(null, "titulares");
        for (Noticia noticia : listaNoticias) {

            serializer.startTag(null, "item");

            // Elemento titulo
            serializer.startTag(null, "titulo");
            serializer.attribute(null, "fecha", noticia.getPubDate());
            serializer.text(noticia.getTitle());
            serializer.endTag(null, "titulo");
            // Elemento link
            serializer.startTag(null, "enlace");
            serializer.text(noticia.getLink());
            serializer.endTag(null, "enlace");
            // Elemento descripcion
            serializer.startTag(null, "descripcion");
            serializer.text(noticia.getDescription());
            serializer.endTag(null, "descripcion");

            serializer.endTag(null, "item");
        }

        serializer.endTag(null, "titulares");
        serializer.endDocument();
        serializer.flush();
        fout.close();
    }
}
