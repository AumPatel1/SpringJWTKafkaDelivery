package com.Tracks.Shipment_Service.security.filter;

import com.Tracks.Shipment_Service.config.CustomerUserDetailsService;
import com.Tracks.Shipment_Service.config.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter
        extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomerUserDetailsService userService;
//now take the previous steps into action
    //take the jwt service that creates tje token and userdetailservice that load user
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
//what is doFilterInternalMethod about ?
        //is it handle the incoming request?
        //take the request , get the header
        //if header start with bearer , pass
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
//token is jwt token after fist 7 lettters
        //see in service extract username
        //
        String token = header.substring(7);
        String email = jwtService.extractUsername(token);

        if (email != null &&
                SecurityContextHolder.getContext()
                        .getAuthentication() == null) {
//if email not authenticated , load user
            //should not it be CustomerUserDetails ?it is ig
            UserDetails user =
                    userService.loadUserByUsername(email);
//where is verifying the token is correct and authenticating ?
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            user, null,
                            user.getAuthorities()
                    );

            SecurityContextHolder.getContext()
                    .setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}