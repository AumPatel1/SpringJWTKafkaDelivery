package com.Tracks.Shipment_Service.config;

import com.Tracks.Shipment_Service.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


//This is the way spring loads the user so we override that method and make it load and deal our way
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final User user;
//UserDetails

    //OVerride the GrantedAuthorities - get user.getRole()
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_" + user.getRole())
        );
    }

//get user password
    @Override
    public String getPassword() {
        return user.getPassword();
    }
//get username
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}