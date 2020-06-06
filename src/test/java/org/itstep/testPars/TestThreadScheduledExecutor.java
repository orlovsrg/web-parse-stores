package org.itstep.testPars;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestThreadScheduledExecutor {
    public static void main(String[] args) {

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(() -> {
            System.out.println("ok: " + LocalTime.now());
        }, 2, 5, TimeUnit.SECONDS);


    }

}
