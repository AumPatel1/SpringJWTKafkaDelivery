package com.Tracks.Shipment_Service.repository;

import com.Tracks.Shipment_Service.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import javax.swing.text.html.Option;
import java.util.Optional;
@Repository
public interface ShipmentRepository extends JpaRepository<Shipment,Long> {
    Optional<Shipment> findByTrackingNumber(String trackingNumber);
}
