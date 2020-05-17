package org.itstep.data.parse;

import java.io.*;

import java.net.URL;
import java.net.URLConnection;

public class DownloadFile {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.foxtrot.com.ua/dist/images/loader.svg");
        URLConnection connection = url.openConnection();
        connection.connect();
        InputStream in = connection.getInputStream();
        OutputStream out = new FileOutputStream(new File("img_test.svg"));
        int ch;
        while ((ch = in.read()) != -1){
            out.write((char)ch);
        }
        in.close();
        out.flush();
        out.close();


        System.out.println(connection.getContentType());
        System.out.println(connection.getContent());


    }
}
