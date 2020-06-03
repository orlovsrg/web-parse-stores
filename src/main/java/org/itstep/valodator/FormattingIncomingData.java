package org.itstep.valodator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FormattingIncomingData {

    private static final Logger log = LoggerFactory.getLogger(FormattingIncomingData.class);

    public int formattingPrice(String data) {
        if (data == null)
            return 0;
        return Integer.parseInt(data.replaceAll("\\D", ""));
    }

    public String formattingTitle(String title) {
        log.info("title in formatter: {}", title);
        if (title.startsWith("Смартфон") || title.startsWith("Телевизор")) {
            title = title.replaceAll("\\([^()]*\\)", "");
            title = title.replaceAll("[а-яА-Я]", "");
            title = title.replaceAll("(International)", "");
            return title.replaceAll(" {2,}", " ").trim();
        } else if (title.startsWith("Ноутбок")) {
            if (title.lastIndexOf(")") > 0 && title.lastIndexOf(")") != title.length() - 1) {
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
