package com.Tracks.Shipment_Service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrackingEvent {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@ManyToOne
    private Shipment shipment;
    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;
    private String location;

    private LocalDateTime timestamp;

}

