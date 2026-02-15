package com.Tracks.Shipment_Service.repository;

import com.Tracks.Shipment_Service.entity.TrackingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
//@Repository
public interface TrackingEventRepository extends JpaRepository<TrackingEvent,Long> {
    List<TrackingEvent> findByShipment_Id(Long shipmentId);
}
