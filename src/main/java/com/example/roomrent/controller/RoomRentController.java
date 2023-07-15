package com.example.roomrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoomRentController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String pageHome(Model model){

        return "index";
    }
}
