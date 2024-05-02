package com.HotelReservationapp.service;

import com.HotelReservationapp.dao.RoomRepository;
import com.HotelReservationapp.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;


    public List<Room>getAllRooms(){
        return roomRepository.findAll();
    }
    public Optional<Room> getRoomById(Long id){
        return roomRepository.findById(id);
    }
    public Optional<Room> getRoomByRoomNumber(String roomNumber){
        return roomRepository.getRoomByRoomNumber(roomNumber);

    }
    @Override
    public void saveOrUpdateRoom(Room room){
        roomRepository.save(room);
    }
    public  void deleteRoom(Long id){
        //16 40

    }

}
