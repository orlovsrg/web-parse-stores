package org.itstep.api;

import org.itstep.dto.ModelEquipmentDto;
import org.itstep.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserApi {

    @Autowired
    private final SubscriptionService subscriptionService;

    public UserApi(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ModelEquipmentDto> getAllSubUser(@PathVariable int id){
        return subscriptionService.getAllSubscriptionProduct(id);
    }

}
