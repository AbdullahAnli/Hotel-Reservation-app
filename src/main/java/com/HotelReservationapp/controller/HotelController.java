package com.HotelReservationapp.controller;

import com.HotelReservationapp.dao.HotelRepository;
import com.HotelReservationapp.entity.Hotel;
import com.HotelReservationapp.service.HotelService;
import com.HotelReservationapp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomServiceService;

    @GetMapping
    public String getAllHotels(Model model){
        model.addAttribute("hotels",hotelService.getAllHotels());
        return "hotel-list";
    }
    @GetMapping("/{id}")
    public String getHotelById(@PathVariable Long id,Model model){
        Hotel hotel=hotelService.getHotelById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid hotel id :"+id));
        model.addAttribute("hotel",hotel);
        return "hotel-details";
    }
    @GetMapping("/city/{city}")
    public String getHotelsByCity(@PathVariable String city , Model model) {
        List<Hotel> hotels =hotelService.getHotelsByCity(city);
        model.addAttribute("hotels",hotels);
        return "hotel-list";
    }
    @GetMapping("/new")
    public String showHotelForm(Model model){
        model.addAttribute("hotel",new Hotel());
        return "hotel-form";
    }
    @GetMapping("/new/rooms")
    public String showHotelRoomForm(Model model){
        model.addAttribute("hotel",new Hotel());
        model.addAttribute("rooms",roomServiceService.getAllRooms());

        return "hotel-room-form";
    }
    @PostMapping
    public String addHotel(Hotel hotel , BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "hotel-form";
        }
        hotelService.SaveOrUpdateHotel(hotel);
        redirectAttributes.addFlashAttribute("successMessage","Hotel added successfully");
        return "redirect:/hotels";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id,Model model){
        Hotel  hotel=hotelService.getHotelById(id)
                .orElseThrow(()-> new IllegalArgumentException("invalid hotel id :"+id));
        model.addAttribute("hotel",hotel);
        return "hotel-form";
    }
    @PostMapping("/edit/{id}")
    public String updateHotel(@PathVariable Long id,Hotel hotel,BindingResult result,
                              RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "hotel-form";
        }
        hotel.setId(id);
        hotelService.SaveOrUpdateHotel(hotel);
        redirectAttributes.addFlashAttribute("successMessage","Hotel update successfully");
        return "redirect:/hotels";
    }
    @GetMapping("/delete/{id}")
    public String deleteHotel(@PathVariable Long id,RedirectAttributes redirectAttributes){
        hotelService.DeleteHotel(id);
        redirectAttributes.addFlashAttribute("successMessage ","Hotel deleted successfully");
        return "redirect:/hotels";
    }





}
