package com.HotelReservationapp.service;

import com.HotelReservationapp.dao.HotelRepository;
import com.HotelReservationapp.entity.Hotel;
import jakarta.servlet.http.PushBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel>getAllHotels(){
        return hotelRepository.findAll();
    }
    public Optional<Hotel>getHotelById(Long id){
        return hotelRepository.findById(id);
    }
    public List<Hotel>getHotelsByCity(String city) {
       return hotelRepository.getHotelsByCity(city);
    }
    public void SaveOrUpdateHotel(Hotel hotel){
        hotelRepository.save(hotel);
    }
    public void DeleteHotel(Long id){
        hotelRepository.deleteById(id);
    }

}
