package com.Tracks.Shipment_Service.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="users")
public class User{
@Id
@GeneratedValue
    private Long id;

@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
@JsonManagedReference
private List<Shipment> shipments;
    @Column(unique = true, nullable = false)
private String email;
    @Column(nullable = false)
private String password;

    // In main entity add the role for assigning to user

    @Column(nullable = false)
private String role = "USER";
}