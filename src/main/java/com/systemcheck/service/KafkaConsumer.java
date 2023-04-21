package com.systemcheck.service;

import com.systemcheck.entity.CoffeeOrder;
import com.systemcheck.repository.CoffeeOrderRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class KafkaConsumer {

    @Autowired
    CoffeeOrderRepository coffeeOrderRepository;

/*    @KafkaListener(topics = "new-topic", groupId = "test")
    public void consume(String message) throws IOException {
        System.out.println(String.format("Consumed message : %s", message));
        CoffeeOrder coffeeOrder = new CoffeeOrder();
        coffeeOrder.setCustomerId(message);
        coffeeOrderRepository.save(coffeeOrder);
    }*/
}
