package com.Tracks.Shipment_Service.kafka.event;

import com.Tracks.Shipment_Service.entity.ShipmentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder@NoArgsConstructor@AllArgsConstructor
public class ShpimentEvent {
    private String trackingNumber;
    private ShipmentStatus status;
    private String location;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime timestamp;
}


// starting kafka by creating an event -
//we have all the properties listed here ,some of them are classes and some are the enums
//