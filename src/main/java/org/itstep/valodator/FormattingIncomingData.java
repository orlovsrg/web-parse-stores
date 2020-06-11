package org.itstep.valodator;

import org.springframework.stereotype.Component;

@Component
public class FormattingIncomingData {

    public synchronized int formattingPrice(String data) {
        if (data == null)
            return 0;
        return Integer.parseInt(data.replaceAll("\\D", ""));
    }

    public synchronized String formattingTitle(String title) {

        if (title.toLowerCase().startsWith("cмартфон") || title.toLowerCase().startsWith("телевизор")) {
            title = title.replaceAll("\\([^()]*\\)", "");
            title = title.replaceAll("[а-яА-Я]", "");
            title = title.replaceAll("(International)", "");
            return title.replaceAll(" {2,}", " ").trim();
        } else if (title.toLowerCase().startsWith("ноутбук")) {
            if (title.lastIndexOf(")") > 0 && title.lastIndexOf(")") != title.length() - 1) {
                title = title.replaceAll("[а-яА-Я]", "");
                String part = title.substring(title.lastIndexOf("("), title.lastIndexOf(")") + 1);
                title = title.replace(part, "");
                title = title + " " + part;
                title = title.replaceAll(" {2,}", " ").trim();
                return title;
            }
        }

        return title;
    }
}
