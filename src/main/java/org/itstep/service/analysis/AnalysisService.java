package org.itstep.service.analysis;

import org.itstep.data.parse.DataEquipment;
import org.itstep.model.ModelEquipment;
import org.itstep.model.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalysisService {
    private final static Logger log = LoggerFactory.getLogger(AnalysisService.class);

    @Autowired
    private final DataEquipment dataEquipment;

    public AnalysisService(DataEquipment dataEquipment) {
        this.dataEquipment = dataEquipment;
    }


    public Map<Store, List<ModelEquipment>> getProductsBy(String typeProduct){
        Map<Store, List<ModelEquipment>> map = new HashMap<>();
        List<Store> storeList = dataEquipment.getAllStores();
        log.info("Stores: {}", storeList);

        storeList.forEach(store ->
                map.put(store, dataEquipment.getProductsByType(typeProduct, store.getId()))
        );

        map.forEach((k, v) -> System.out.println(k + ": " + v));

        return map;
    }

}
