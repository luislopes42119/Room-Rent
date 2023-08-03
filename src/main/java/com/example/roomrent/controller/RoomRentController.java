package com.example.roomrent.controller;

import com.example.roomrent.domain.anuncio.Anuncio;
import com.example.roomrent.domain.anuncio.Anuncio.AnuncioRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RoomRentController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String pageHome(Model model){
        List<Anuncio> anunciosOferta = jdbcTemplate.query("SELECT * FROM anuncio WHERE tipo = 'O' AND estado = 'A' order by data DESC", new AnuncioRowMapper());
        List<Anuncio> anunciosProcura = jdbcTemplate.query("SELECT * FROM anuncio WHERE tipo = 'P' AND estado = 'A' order by data DESC", new AnuncioRowMapper());

        if(anunciosOferta.size() > 3){
            anunciosOferta = anunciosOferta.subList(anunciosOferta.size()-3, anunciosOferta.size());
        }
        if(anunciosProcura.size() > 3){
            anunciosProcura = anunciosProcura.subList(anunciosProcura.size()-3, anunciosProcura.size());
        }

        model.addAttribute("anunciosOferta", anunciosOferta);
        model.addAttribute("anunciosProcura", anunciosProcura);

        return "index";
    }

    @GetMapping("/anuncio/search")
    public String pageSearch(){
        return "search";
    }
}
