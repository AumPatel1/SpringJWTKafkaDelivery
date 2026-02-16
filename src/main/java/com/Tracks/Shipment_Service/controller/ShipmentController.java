package com.Tracks.Shipment_Service.controller;

import com.Tracks.Shipment_Service.dto.CreateShipmentRequest;
import com.Tracks.Shipment_Service.dto.UpdateShipmentStatusRequest;
import com.Tracks.Shipment_Service.entity.Shipment;
import com.Tracks.Shipment_Service.entity.ShipmentStatus;
import com.Tracks.Shipment_Service.entity.TrackingEvent;
import com.Tracks.Shipment_Service.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipments")
@RequiredArgsConstructor
public class ShipmentController {
    private final ShipmentService shipmentService;

    @GetMapping("/{trackingNumber}")
    public Shipment getShipment(@PathVariable String trackingNumber) {
        return shipmentService.getShipmentByTrackingNumber(trackingNumber);
    }

    @PutMapping("/{trackingnumber}/status")
    public Shipment updateShipment(@PathVariable String trackingnumber,
                                   @RequestBody UpdateShipmentStatusRequest request){
        return shipmentService.updatestatus(trackingnumber,request.getStatus(),request.getLocation());
    }

    @PostMapping
    public Shipment createShipment(
            @RequestBody CreateShipmentRequest request) {

        return shipmentService.createShipment(
                request.getUserId(),
                request.getSenderAddress(),
                request.getReceiverAddress()
        );

    }

    @GetMapping("/{trackingNumber}/history")
    public List<TrackingEvent> getHistory(
            @PathVariable String trackingNumber) {
        return shipmentService.getTrackingHistory(trackingNumber);
    }
}