package com.Tracks.Shipment_Service.controller;

import com.Tracks.Shipment_Service.dto.CreateUserRequest;
import com.Tracks.Shipment_Service.entity.User;
import com.Tracks.Shipment_Service.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/load")
    public User getUser(@RequestParam String email){
      return  userService.load(email);

    }

    @PostMapping
    public User createUser(@RequestBody CreateUserRequest request){
       return  userService.createUser(
               request.getEmail(),
               request.getPassword()
       );
    }
}
