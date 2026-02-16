package com.Tracks.Shipment_Service.dto;

import com.Tracks.Shipment_Service.entity.ShipmentStatus;
import lombok.Data;

@Data
public class UpdateShipmentStatusRequest {
    private ShipmentStatus status;
    private String location;
}
