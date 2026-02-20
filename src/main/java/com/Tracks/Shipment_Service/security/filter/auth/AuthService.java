package com.Tracks.Shipment_Service.security.filter.auth;

import com.Tracks.Shipment_Service.config.CustomerUserDetailsService;
import com.Tracks.Shipment_Service.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final CustomerUserDetailsService userService;

    //when user login , authenticate but where does it came from and what about JWT or is it
    // before that to see if user who trys to login has correct password or not?
    public String login(String email, String password) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email, password
                )
        );
//userdetail service = get the username
        UserDetails user =
                userService.loadUserByUsername(email);
        //and generte token
        return jwtService.generatetoken(user);
    }
}
