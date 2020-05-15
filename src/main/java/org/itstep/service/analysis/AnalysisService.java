package org.itstep.service.analysis;

import org.itstep.data.parse.DataEquipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AnalysisService {

    @Autowired
    private final DataEquipment dataEquipment;

    public AnalysisService(DataEquipment dataEquipment) {
        this.dataEquipment = dataEquipment;
    }


    public Map<String, List<String>> getProductsBy(String typeProduct){
        List<String> listStore = dataEquipment.getAllStores();

        return null;
    }

}
