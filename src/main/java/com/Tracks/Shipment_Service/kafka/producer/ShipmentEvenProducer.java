package com.Tracks.Shipment_Service.kafka.producer;

import com.Tracks.Shipment_Service.entity.Shipment;
import com.Tracks.Shipment_Service.kafka.event.ShpimentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service@RequiredArgsConstructor
public class ShipmentEvenProducer {
    private final KafkaTemplate<String, ShpimentEvent> kafkaTemplate;
    private static final String TOPIC = "shipment-events";
    public void sendEvent(ShpimentEvent event){
        kafkaTemplate.send(TOPIC,event);
    }
}
//for kafka we need producer
//we have to initialize a kafkaTemplate with values<String, Event> i.e. <INt or string something,Class>
//a topic = "shipment-events"
// and a method of sendEvent , it is the class  , to that topic and that event class full of values
//