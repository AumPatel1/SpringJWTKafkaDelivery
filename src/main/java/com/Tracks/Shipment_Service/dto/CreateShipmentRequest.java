package com.Tracks.Shipment_Service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateShipmentRequest {

    private Long userId;
    @NotBlank
    private String senderAddress;
    @NotBlank
    private String receiverAddress;
}
