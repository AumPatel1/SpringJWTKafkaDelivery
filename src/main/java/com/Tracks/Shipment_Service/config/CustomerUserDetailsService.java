package com.Tracks.Shipment_Service.config;

import com.Tracks.Shipment_Service.entity.User;
import com.Tracks.Shipment_Service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {
    //Override USerDetailService
    //load user by username and then get the  password and stuff
    private final UserRepository userRepository;
    //override
    //public UserDetails loadUserByUsername(email){
    //userRepo.findBYEMail(email)
    //and pass that thing to the previous created CustomerUSerDetails(user)
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(user);
    }
}