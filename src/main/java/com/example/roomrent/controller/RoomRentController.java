package com.example.roomrent.controller;

import com.example.roomrent.domain.anuncio.Anuncio;
import com.example.roomrent.domain.anuncio.Anuncio.AnuncioRowMapper;
import com.example.roomrent.domain.mensagem.Mensagem;
import com.example.roomrent.domain.mensagem.Mensagem.MensagemRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
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

    // Error
    @GetMapping("/error")
    public String pageError(){
        return "error";
    }

    /* Anunicos */ 
    // Search Anuncio
    @GetMapping("/anuncio/search")
    public String pageSearch(){
        return "search";
    }

    @GetMapping("/anuncio/roomsearch")
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

    // Show Anuncio
    @GetMapping("/anuncio/{id}")
    public String pageAnuncio(@PathVariable Long id, Model model){
        String query = "SELECT * FROM anuncio WHERE id = "+ id;
        Anuncio anuncio = jdbcTemplate.queryForObject(query, new AnuncioRowMapper());
        model.addAttribute("anuncio", anuncio);

        return "anuncio";
    }

    // Publish Anuncio
    @GetMapping("/anuncio/publish")
    public String pagePublish(){
        return "publish";
    }

    @PostMapping("/anuncio/create")
    public String createAnuncio(@RequestParam(name = "titulo", required = true) String titulo,
                                @RequestParam(name = "local", required = true) String local,
                                @RequestParam(name = "preco", required = true) int preco,
                                @RequestParam(name = "genero", required = true) char genero,
                                @RequestParam(name = "anunciante", required = true) String anunciante,
                                @RequestParam(name = "contacto", required = true) int contacto,
                                @RequestParam(name = "tipologia", required = true) String tipologia,
                                @RequestParam(name = "tipo", required = true) char tipo,
                                @RequestParam(name = "descricao", required = true) String descricao,
                                Model model) throws Exception{
        Date data = new Date();
        java.sql.Date sqlDate = new java.sql.Date(data.getTime());

        String query = "INSERT INTO anuncio(titulo, local, preco, descricao, genero, anunciante, contacto, tipologia, data, tipo, estado) VALUES('"+ titulo +"', '"+ local +"', "+ preco +", '"+ descricao +"', '"+ genero +"', '"+ anunciante +"', "+ preco +", '"+ tipologia +"', '"+ sqlDate +"', '"+ tipo +"', 'I');";
        jdbcTemplate.update(query);

        return "redirect:/";
    }

    // Mensagem
    // send message
    @PostMapping("/message/send")
    public String sendMessage(@RequestParam(name = "idAnuncio", required = true) Long idAnuncio,
                              @RequestParam(name = "remetente", required = true) String remetente,
                              @RequestParam(name = "mensagem", required = true) String mensagem,
                              Model model) throws Exception{
        Date data = new Date();
        java.sql.Date sqlDate = new java.sql.Date(data.getTime());

        String query = "INSERT INTO mensagem(remetente, mensagem, anuncio_id, data) VALUES('"+ remetente +"', '"+ mensagem +"', '"+ idAnuncio +"', '"+ sqlDate +"');";
        jdbcTemplate.update(query);

        return "redirect:/anuncio/"+ idAnuncio;
    }

    // show message
    @GetMapping("/message")
    public String pageMessage(){
        return "message";
    }

    // Read message
    @GetMapping("/message/search")
    public String messageSearch(@RequestParam(name = "idAnuncio", required = true) String idAnuncio,
                                Model model){
        String query = "SELECT * FROM mensagem WHERE anuncio_id = '"+ idAnuncio +"' order by data DESC";
        List<Mensagem> mensagens = jdbcTemplate.query(query, new MensagemRowMapper());
        model.addAttribute("mensagens", mensagens);

        return "message";
    }
}
