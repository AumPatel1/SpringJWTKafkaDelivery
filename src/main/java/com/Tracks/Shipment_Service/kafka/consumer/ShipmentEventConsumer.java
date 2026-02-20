package com.Tracks.Shipment_Service.kafka.consumer;
import com.Tracks.Shipment_Service.entity.Shipment;
import com.Tracks.Shipment_Service.entity.TrackingEvent;
import com.Tracks.Shipment_Service.kafka.event.ShpimentEvent;
import com.Tracks.Shipment_Service.kafka.producer.ShipmentEvenProducer;
import com.Tracks.Shipment_Service.repository.ShipmentRepository;
import com.Tracks.Shipment_Service.repository.TrackingEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class ShipmentEventConsumer {

    private final ShipmentRepository shipmentRepository;
    private final TrackingEventRepository trackingRepo;

    @KafkaListener(
            topics = "shipment-events",
            groupId = "shipment-group"
    )
    public void consume(ShpimentEvent event) {

        Shipment shipment =
                shipmentRepository
                        .findByTrackingNumber(
                                event.getTrackingNumber()
                        )
                        .orElseThrow();


        TrackingEvent tracking =
                TrackingEvent.builder()
                        .shipment(shipment)
                        .status(event.getStatus())
                        .location(event.getLocation())
                        .timestamp(event.getTimestamp())
                        .build();

        trackingRepo.save(tracking);

        System.out.println(
                "Consumed event: " + event
        );
    }
}
//shipment repo
//trackingevent repo
//@listeners are topic and group id
// consume(shipment event event){
/*
Shipment shipment = shipmentRepository.findByTrackinNumber(event.getTrackingNumber)
 */
