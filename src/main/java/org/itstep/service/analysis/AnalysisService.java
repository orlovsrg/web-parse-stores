package org.itstep.service.analysis;

import org.itstep.data.parse.DataEquipment;
import org.itstep.model.ModelEquipment;
import org.itstep.model.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
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

    public List<Store> getStores() {
        return dataEquipment.getAllStores();
    }


    public Map<String, List<ModelEquipment>> getProductsByType(String typeProduct) {
        Map<String, List<ModelEquipment>> map = new LinkedHashMap<>();
        log.info("Stores: {}", "");
        List<String> listTitleProduct = dataEquipment.getAllTitle(typeProduct);
        List<ModelEquipment> listModel = dataEquipment.getProductsByType(typeProduct);

        listTitleProduct.stream().limit(5).forEach(n -> {
            map.put(n, dataEquipment.getProductByName(typeProduct, n));
        });

        return map;
    }

}
