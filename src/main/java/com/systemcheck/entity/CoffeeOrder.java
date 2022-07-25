package com.systemcheck.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CoffeeOrder {

    private String customerId;
    private String menuId;
    private int amount;

    private List<CoffeeOrder> orders;

    public CoffeeOrder(String customerId, String menuId, int amount) {
        this.customerId = customerId;
        this.menuId = menuId;
        this.amount = amount;
    }
}
