package com.Tracks.Shipment_Service.dto;

import lombok.Data;


@Data
public class CreateUserRequest {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

