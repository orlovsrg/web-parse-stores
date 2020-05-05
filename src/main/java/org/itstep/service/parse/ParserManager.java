package org.itstep.service.parse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParserManager {
    private final String storeComfy = "comfy";
    private final String storeFoxtrot = "foxtrot";

    @Autowired
    private final FoxtrotStoreService foxtrotStoreService;
    @Autowired
    private final ComfyStoreService comfyStoreService;


    public ParserManager(FoxtrotStoreService foxtrotStoreService,  ComfyStoreService comfyStoreService) {
        this.foxtrotStoreService = foxtrotStoreService;
        this.comfyStoreService = comfyStoreService;
    }

    public void parse() {
        new Thread(() -> foxtrotStoreService.startProcess(storeFoxtrot)).start();
        new Thread(() -> comfyStoreService.startProcess(storeComfy)).start();
    }
}
