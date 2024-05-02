package com.HotelReservationapp.controller;

import com.HotelReservationapp.entity.Room;
import com.HotelReservationapp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import javax.sql.rowset.serial.SerialStruct;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping
    public String getAllRooms(Model  model){
        model.addAttribute("rooms",roomService.getAllRooms());
        return "room-list";
    }
    @GetMapping("/{id}")
    public String  getRoomById(@PathVariable Long id,Model model){
        Room room =roomService.getRoomById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid room id: "+id));
        model.addAttribute("room",room);
        return "room-details";
    }
    @GetMapping("/room-number/{roomNumber}")
    public  String  getRoomByRoomNumber(@PathVariable String roomNumber,Model model){
        Room room= roomService.getRoomByRoomNumber(roomNumber)
                .orElseThrow(()-> new IllegalArgumentException("Invalid room number: "+roomNumber));
        model.addAttribute("room",room);
        return "room-details";
    }
    @PostMapping
    public String addRooms(Room room , BindingResult result , RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "room-form";
        }
        roomService.saveOrUpdateRoom(room);
        redirectAttributes.addFlashAttribute("successMessage","Room added successfully");
        return "redirect:/rooms";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id ,Model model){
        Room room = roomService.getRoomById(id)
                .orElseThrow(()->new IllegalArgumentException("Invalid room id : "+id));
        model.addAttribute("room" ,room);
        return "room-form";
    }
    @PostMapping("/edit/{id}")
    public String updateRoom(@PathVariable Long id, Room room,BindingResult result,
                               RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "room-form";
        }
        room.setId(id);
        roomService.saveOrUpdateRoom(room);
        redirectAttributes.addFlashAttribute("successMessage","Room update successfully");
        return "redirect:/rooms";
    }


}
