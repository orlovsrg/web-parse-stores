package org.itstep.valodator;

import org.springframework.stereotype.Component;

@Component
public class FormattingIncomingData {

    public int formattingPrice(String data) {
        if (data == null)
            return 0;
        return Integer.parseInt(data.replaceAll("\\D", ""));
    }

    public String formattingTitle(String title) {
        if (title == null)
            return "";
        title = title.replaceAll("[а-яА-Я]", "");
        title = title.trim();

        if (')' == title.charAt(title.length() - 1)) {
            title = title.substring(0, title.lastIndexOf("("));
        }

        title = title.trim();
        title = title.replaceAll(" {2,}", " ");

        return title;
    }
}
