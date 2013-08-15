package ru.fuxx;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*;

public class TitleListHandler extends DefaultHandler {
    private StringBuilder currentBuffer;
    private String title;
    private String name;
    private Writer w = null;

    public TitleListHandler(final String outName) {
        this.name = outName;
    }

    private void open(final String name) {
        try {
            w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {
            //if(w!=null)try {w.close();} catch(Exception e){e.printStackTrace();}
        }
    }

    private void write(String title) {
        try {
            w.write(title + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void close() {
        try {
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startElement(String uri,
                             String localName,
                             String qName,
                             Attributes attributes)
            throws SAXException {
        if (qName.equalsIgnoreCase("mediawiki")) {
            open(name);
        } else if (qName.equalsIgnoreCase("page")) {
            ;
        } else if (qName.equalsIgnoreCase("title")) {
            currentBuffer = new StringBuilder();
        } else if (qName.equalsIgnoreCase("text")) {
        }
    }

    public void endElement(String uri,
                           String localName,
                           String qName)
            throws SAXException {
        if (qName.equalsIgnoreCase("page")) {
            if (title.startsWith("Обсуждение портала:")
                    || title.startsWith("Портал:")
                    || title.startsWith("Обсуждение категории:")
                    || title.startsWith("Категория:")
                    || title.startsWith("Обсуждение справки:")
                    || title.startsWith("Справка:")
                    || title.startsWith("Обсуждение шаблона")
                    || title.startsWith("Шаблон:")
                    || title.startsWith("Обсуждение MediaWiki:")
                    || title.startsWith("MediaWiki:")
                    || title.startsWith("Обсуждение файла:")
                    || title.startsWith("Файл:")
                    || title.startsWith("Обсуждение Википедии:")
                    || title.startsWith("Википедия:")
                    || title.startsWith("Обсуждение участника:")
                    || title.startsWith("Участник:")
                    || title.startsWith("Обсуждение:")
                    || title.startsWith("Служебная:")
                    || title.startsWith("Медиа:"))
                return;
            write(title);
            title = null;
        } else if (qName.equalsIgnoreCase("title")) {
            title = currentBuffer.toString();
            currentBuffer = null;
        } else if (qName.equalsIgnoreCase("text")) {
            currentBuffer = null;
        } else if (qName.equalsIgnoreCase("mediawiki")) {
            close();
        }
    }

    public void characters(char ch[],
                           int start,
                           int length)
            throws SAXException {
        if (currentBuffer == null) return;
        currentBuffer.append(new String(ch, start, length));
    }
}
