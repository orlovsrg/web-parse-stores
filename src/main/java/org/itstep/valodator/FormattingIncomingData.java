package org.itstep.valodator;

import org.springframework.stereotype.Component;

@Component
public class FormattingIncomingData {

    public int formattingPrice(String data){
        if (data == null)
            return 0;
        return Integer.parseInt(data.replaceAll("\\D", ""));
    }

    public String formattingTitle(String title){
        if (title == null)
            return "";
        return title.replaceAll("\\(.*\\)", "");
    }
}
