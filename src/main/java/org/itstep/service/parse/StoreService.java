package org.itstep.service.parse;

public interface StoreService {
    void startProcess(String nameStore);
    void pars(String urlParsingTypePage, String productType) throws InterruptedException;
}
