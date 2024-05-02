package com.HotelReservationapp.dao;

import com.HotelReservationapp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room,Long> {

    Optional<Room> getRoomByRoomNumber(String roomNumber);
}
