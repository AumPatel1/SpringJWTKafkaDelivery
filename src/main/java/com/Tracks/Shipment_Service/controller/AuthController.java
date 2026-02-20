package com.Tracks.Shipment_Service.controller;

import com.Tracks.Shipment_Service.dto.LoginRequest;
import com.Tracks.Shipment_Service.security.filter.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //so when one login ,goes from here
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {
        return authService.login(
                req.getEmail(),
                req.getPassword()
        );
    }
}

