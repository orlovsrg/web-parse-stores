package org.itstep.api;

import org.itstep.data.parse.DataEquipment;
import org.itstep.dto.ModelEquipmentDto;
import org.itstep.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserApi {
    private final Logger log = LoggerFactory.getLogger(UserApi.class);

    @Autowired
    private final SubscriptionService subscriptionService;
    @Autowired
    private final DataEquipment dataEquipment;

    public UserApi(SubscriptionService subscriptionService, DataEquipment dataEquipment) {
        this.subscriptionService = subscriptionService;
        this.dataEquipment = dataEquipment;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelEquipmentDto getRandomModelEquipmentDto(){
        log.info("Getting DTO for AJAX from server");
        ModelEquipmentDto dto = dataEquipment.getRandomEquipment();
        log.info("DTO in GET Ajax: {}", dto);
        return dto;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ModelEquipmentDto> getAllSubUser(@PathVariable(value = "id") int userId){
        return subscriptionService.getAllSubscriptionProduct(userId);
    }

}
