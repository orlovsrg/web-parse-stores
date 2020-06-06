package org.itstep.service.parse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ParserManager {
    private final String storeComfy = "comfy";
    private final String storeFoxtrot = "foxtrot";


    @Autowired
    private final FoxtrotStoreService foxtrotStoreService;
    @Autowired
    private final ComfyStoreService comfyStoreService;

    public ParserManager(FoxtrotStoreService foxtrotStoreService, ComfyStoreService comfyStoreService) {
        this.foxtrotStoreService = foxtrotStoreService;
        this.comfyStoreService = comfyStoreService;
    }


    @PostConstruct
    public void parse() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(() -> {
            new Thread(() -> foxtrotStoreService.startProcess(storeFoxtrot)).start();
            new Thread(() -> comfyStoreService.startProcess(storeComfy)).start();
        }, 10, 60 * 24, TimeUnit.SECONDS);
    }

}
