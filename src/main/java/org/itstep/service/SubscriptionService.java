package org.itstep.service;

import org.itstep.data.parse.DataEquipment;
import org.itstep.data.parse.DataSubscription;
import org.itstep.model.ModelEquipment;
import org.itstep.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionService {
    @Autowired
    private final DataSubscription dataSubscription;
    @Autowired
    private final DataEquipment dataEquipment;

    public SubscriptionService(DataSubscription dataSubscription, DataEquipment dataEquipment) {
        this.dataSubscription = dataSubscription;
        this.dataEquipment = dataEquipment;
    }

    public List<Subscription> getAllSubscriptionByUserId(int userId) {
        return dataSubscription.getUserSubscription(userId);
    }

    public void saveSubscription(Subscription subscription){
        dataSubscription.addSubscription(subscription);
    }

    public List<ModelEquipment> getAllSubscriptionProduct(int userId){
        List<Subscription> subscriptionList = getAllSubscriptionByUserId(userId);
        List<ModelEquipment> modelEquipmentList = new ArrayList<>();
        subscriptionList.forEach(s -> modelEquipmentList.add(dataEquipment.getProductById(s.getProductType(), s.getProductId())));
        return modelEquipmentList;
    }
}
