package org.itstep.data.parse;

import java.io.*;

import java.net.URL;
import java.net.URLConnection;

public class DownloadFile {
    public static void main(String[] args) throws IOException {
//        URL url = new URL("https://www.foxtrot.com.ua/dist/images/loader.svg");
//        URLConnection connection = url.openConnection();
//        connection.connect();
//        InputStream in = connection.getInputStream();
//        OutputStream out = new FileOutputStream(new File("img_test.svg"));
//        int ch;
//        while ((ch = in.read()) != -1){
//            out.write((char)ch);
//        }
//        in.close();
//        out.flush();
//        out.close();
//
//
//        System.out.println(connection.getContentType());
//        System.out.println(connection.getContent());

//        String s = "Ноутбук игровой Acer Nitro 5    AN515-54 (NH.Q5AEU.05G) Black (NH.Q5AEU.05G)    ";
//        int idx = s.lastIndexOf("(");
//        s = s.trim();
//
//        System.out.println(s);
//        System.out.println(s.charAt(s.length() - 1));
//        System.out.println( ')' == s.charAt(s.length() - 1));
//        System.out.println( s.lastIndexOf("("));
//        System.out.println(s.charAt(s.indexOf(s.length()-1)));

//        s = s.replaceAll(" {2,}", " ");
//        System.out.println(s);




        double x = 9;
        double y = 4;
        double result = Math.ceil(x / y);
        int count = (int) result;
        System.out.println(count);

    }
}
