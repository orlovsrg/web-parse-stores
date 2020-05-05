package org.itstep.service.parse;

import org.springframework.stereotype.Component;

@Component
public class FormattingIncomingData {

    public int formattingPrice(String data){
        if (data == null)
            return 0;
        return Integer.parseInt(data.replaceAll("\\D", ""));
    }
}
