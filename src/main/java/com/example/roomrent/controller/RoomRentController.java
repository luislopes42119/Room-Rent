package com.example.roomrent.controller;

import com.example.roomrent.domain.anuncio.Anuncio;
import com.example.roomrent.domain.anuncio.Anuncio.AnuncioRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RoomRentController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Home
    @GetMapping("/")
    public String pageHome(Model model){
        String queryOferta = "SELECT * FROM anuncio WHERE tipo = 'O' AND estado = 'A' order by data DESC";
        List<Anuncio> anunciosOferta = jdbcTemplate.query(queryOferta, new AnuncioRowMapper());

        String queryProcura = "SELECT * FROM anuncio WHERE tipo = 'P' AND estado = 'A' order by data DESC";
        List<Anuncio> anunciosProcura = jdbcTemplate.query(queryProcura, new AnuncioRowMapper());

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

    // Search
    @GetMapping("/anuncio/search")
    public String pageSearch(){
        return "search";
    }

    @GetMapping("/roomsearch")
    public String roomSearch(@RequestParam(name = "tipo", required = true) char tipo,
                             @RequestParam(name = "local", required = false) String local,
                             Model model){
            
        if(local != ""){
            String query = "SELECT * FROM anuncio WHERE tipo = '"+ tipo +"' AND estado = 'A' AND local ILIKE '%"+ local +"%' order by data DESC";
            List<Anuncio> anuncios = jdbcTemplate.query(query, new AnuncioRowMapper());
            model.addAttribute("anuncios", anuncios);
        }else{
            String query = "SELECT * FROM anuncio WHERE tipo = '"+ tipo +"' AND estado = 'A' order by data DESC";
            List<Anuncio> anuncios = jdbcTemplate.query(query, new AnuncioRowMapper());
            model.addAttribute("anuncios", anuncios);
        }

        return "search";
    }
}
