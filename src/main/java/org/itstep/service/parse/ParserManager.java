package org.itstep.service.parse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParserManager {
    private final String storeName = "foxtrot";

    @Autowired
    private final FoxtrotStoreService foxtrotStoreService;

    public ParserManager(FoxtrotStoreService foxtrotStoreService) {
        this.foxtrotStoreService = foxtrotStoreService;
    }

    public void parse() {
        new Thread(() -> foxtrotStoreService.parse(storeName)).start();
//        foxtrotStoreService.parse(storeName);
    }
}
