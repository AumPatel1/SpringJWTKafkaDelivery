package com.Tracks.Shipment_Service.repository;

import com.Tracks.Shipment_Service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
//public interface UserREposirotury implements JpaRepository<User,Long>{
    //Optional<User> findByEmail(String email);}
