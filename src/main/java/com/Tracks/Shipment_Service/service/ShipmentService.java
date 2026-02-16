package com.Tracks.Shipment_Service.service;


import com.Tracks.Shipment_Service.entity.Shipment;
import com.Tracks.Shipment_Service.entity.ShipmentStatus;
import com.Tracks.Shipment_Service.entity.TrackingEvent;
import com.Tracks.Shipment_Service.entity.User;
import com.Tracks.Shipment_Service.kafka.event.ShpimentEvent;
import com.Tracks.Shipment_Service.kafka.producer.ShipmentEvenProducer;
import com.Tracks.Shipment_Service.repository.ShipmentRepository;
import com.Tracks.Shipment_Service.repository.TrackingEventRepository;
import com.Tracks.Shipment_Service.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ShipmentService {
//private final TrackingEvent trackingEvent;
private final TrackingEventRepository trackingEventRepository;
    private final ShipmentRepository shipmentRepository;
    private final ShipmentEvenProducer eventProducer;
    private final UserRepository userRepository;

//    public Shipment createShipment(Long userId,
//                                   String sender,
//                                   String receiver) {
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Shipment shipment = Shipment.builder()
//                .trackingNumber(generateTrackingNumber())
//                .senderAddress(sender)
//                .receiverAddress(receiver)
//                .status(ShipmentStatus.CREATED)
//                .user(user)
//                .build();
//return eventProducer.sendEvent(shipment);
//      //  return shipmentRepository.save(shipment);
//    }
public Shipment createShipment(Long userId, String sender, String receiver) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

    // 1. Create and Save the Entity to Database first
    Shipment shipment = Shipment.builder()
            .trackingNumber(generateTrackingNumber())
            .senderAddress(sender)
            .receiverAddress(receiver)
            .status(ShipmentStatus.CREATED)
            .user(user)
            .build();

    Shipment savedShipment = shipmentRepository.save(shipment);

    // 2. Create the EVENT object from the saved entity
    ShpimentEvent event = ShpimentEvent.builder()
            .trackingNumber(savedShipment.getTrackingNumber())
            .status(savedShipment.getStatus())
            .location("Warehouse - Origin") // Initial location
            .timestamp(LocalDateTime.now())
            .build();

    // 3. Send the EVENT
    eventProducer.sendEvent(event);

    return savedShipment;
}

    public Shipment getShipmentByTrackingNumber(String trackingNumber){
      return  shipmentRepository.findByTrackingNumber(trackingNumber)
              .orElseThrow(() -> new RuntimeException("Shipment not found"));

    }

    public Shipment updatestatus(String trackingnumber,
                                 ShipmentStatus status,
                                 String location){

        Shipment shipment = getShipmentByTrackingNumber(trackingnumber);
        shipment.setStatus(status);
        shipmentRepository.save(shipment);
        TrackingEvent event = TrackingEvent.builder()
                .shipment(shipment)
                .status(status)
                .location(location)
                .timestamp(LocalDateTime.now())
                .build();
        trackingEventRepository.save(event);
        return shipment;
    }

    public List<TrackingEvent> getTrackingHistory(String trackingNumber){
        Shipment shipment = getShipmentByTrackingNumber(trackingNumber);
        return trackingEventRepository.findByShipment_Id(shipment.getId());
    }




    private String generateTrackingNumber() {
        return "TRK-" + System.currentTimeMillis();
    }
}
