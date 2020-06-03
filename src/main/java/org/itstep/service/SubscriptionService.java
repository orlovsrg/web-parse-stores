package org.itstep.service;

import org.itstep.data.parse.DataEquipment;
import org.itstep.data.parse.DataSubscription;
import org.itstep.dto.ModelEquipmentDto;
import org.itstep.model.ModelEquipment;
import org.itstep.model.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionService {
    private final Logger log = LoggerFactory.getLogger(SubscriptionService.class);

    @Autowired
    private final DataSubscription dataSubscription;
    @Autowired
    private final DataEquipment dataEquipment;
    @Autowired
    private final RestTemplate restTemplate;

    public SubscriptionService(DataSubscription dataSubscription, DataEquipment dataEquipment, RestTemplate restTemplate) {
        this.dataSubscription = dataSubscription;
        this.dataEquipment = dataEquipment;
        this.restTemplate = restTemplate;
    }

    public List<Subscription> getAllSubscriptionByUserId(int userId) {
        return dataSubscription.getUserSubscription(userId);
    }

    public void saveSubscription(Subscription subscription) {
        dataSubscription.addSubscription(subscription);
    }

    public List<ModelEquipmentDto> getAllSubscriptionProduct(int userId) {
        List<Subscription> subscriptionList = getAllSubscriptionByUserId(userId);
        List<ModelEquipmentDto> modelEquipmentList = new ArrayList<>();
        subscriptionList.forEach(s -> modelEquipmentList.add(dataEquipment.getProductById(s.getProductType(), s.getProductId())));
        modelEquipmentList.forEach(System.out::println);
        return modelEquipmentList;
    }

    public void sendMessage(String type, ModelEquipment modelEquipment) {
        log.info("into sendMessage");
        List<Integer> usersId = dataSubscription.getUserIdOfSubscription(type, modelEquipment.getId());
        log.info("userId: {}", usersId);
        if (usersId.size() > 0) {
            ModelEquipmentDto dto = dataEquipment.getProductById(type, modelEquipment.getId());
            dto.setUsersId(usersId);
            log.info("dto: {}", dto);
            restTemplate.put("http://localhost:8081/api/user", dto);
        }
    }

    public void deleteSubscription(int userId, int productId) {
        Subscription subscription = dataSubscription.getSubscription(userId, productId);
        dataSubscription.delete(subscription);
    }

}
