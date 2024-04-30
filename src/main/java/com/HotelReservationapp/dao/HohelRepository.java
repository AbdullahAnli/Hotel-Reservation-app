package com.HotelReservationapp.dao;

import com.HotelReservationapp.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HohelRepository extends JpaRepository<Hotel,Long> {
}
