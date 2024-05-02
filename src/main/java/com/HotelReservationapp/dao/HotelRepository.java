package com.HotelReservationapp.dao;

import com.HotelReservationapp.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
    Optional<Hotel>getHotelByCity(String city);
}
