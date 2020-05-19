package org.itstep.service.analysis;

import org.itstep.data.parse.DataEquipment;
import org.itstep.dto.ModelEquipmentDto;
import org.itstep.model.ModelEquipment;
import org.itstep.model.Store;
import org.itstep.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.ui.Model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalysisService {
    private final static Logger log = LoggerFactory.getLogger(AnalysisService.class);

    @Autowired
    private final DataEquipment dataEquipment;
    @Autowired
    private final SubscriptionService subscriptionService;
    @Autowired
    private final DataSourceTransactionManager transactionManager;

    public AnalysisService(DataEquipment dataEquipment, SubscriptionService subscriptionService, DataSourceTransactionManager transactionManager) {
        this.dataEquipment = dataEquipment;
        this.subscriptionService = subscriptionService;
        this.transactionManager = transactionManager;
    }

    public List<Store> getStores() {
        return dataEquipment.getAllStores();
    }

    public int getCountPage(String productType, double itemsAtPage) {
        double items = dataEquipment.getAllTitle(productType).size() - 1;
        double countPage = Math.ceil(items / itemsAtPage);
        return (int) countPage;
    }


    public Map<String, List<ModelEquipmentDto>> getProductsByType(String typeProduct, int start, int end) {
        Map<String, List<ModelEquipmentDto>> map = new LinkedHashMap<>();
        log.info("Stores: {}", "");
        List<String> listTitleProduct = dataEquipment.getAllTitle(typeProduct);
//        List<ModelEquipment> listModel = dataEquipment.getProductsByType(typeProduct);

        listTitleProduct.stream().skip(start).limit(end - start).forEach(n -> {
            map.put(n, dataEquipment.getProductDtoByName(typeProduct, n));
        });

        return map;
    }

    public void checkProduct(String productType, ModelEquipment modelEquipment) {
        log.warn("In : checkProduct() model= {}", modelEquipment);
        boolean hasProduct = dataEquipment.hasProduct(productType, modelEquipment);
        log.warn("In : checkProduct() hasProduct {}", hasProduct);
        if (hasProduct) {
            boolean checkPrice = dataEquipment.checkPriceProduct(productType, modelEquipment);
            log.warn("In : checkProduct() checkPrice {}", checkPrice);
            TransactionStatus status = null;
            if (!checkPrice) {
                try {
                    status = transactionManager.getTransaction(TransactionDefinition.withDefaults());
                    log.warn("price change notice of product: {}", modelEquipment);
                    ModelEquipment oldModel = dataEquipment.getProductByName(productType, modelEquipment.getTitle()).get(0);
                    log.warn(" old: {}", oldModel);
                    dataEquipment.saveOldProduct(productType, oldModel);
                    log.warn("price change notice of product before save old: {}", modelEquipment);
                    modelEquipment.setId(oldModel.getId());
                    dataEquipment.update(productType, modelEquipment);
                    transactionManager.commit(status);
                    subscriptionService.sendMessage();
                } catch (Exception ex) {
                    transactionManager.rollback(status);
                }

            }
        } else {
            dataEquipment.save(productType, modelEquipment);
        }
    }

}
