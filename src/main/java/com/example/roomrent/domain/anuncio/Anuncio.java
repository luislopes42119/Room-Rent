package com.example.roomrent.domain.anuncio;

import lombok.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Anuncio {
    private Long id;

    private String local;
    private int preco;
    private char genero;
    private String anunciante;
    private int contacto;
    private String tipologia;
    private char tipo;
    private Date data;
    private char estado;


    public static class AnuncioRowMapper implements RowMapper<Anuncio>{
        @Override
        public Anuncio mapRow(ResultSet rs, int rowNum) throws SQLException{
            Anuncio anuncio = new Anuncio();
            anuncio.setId(rs.getLong("id"));
            anuncio.setLocal(rs.getString("local"));
            anuncio.setPreco(rs.getInt("preco"));
            anuncio.setGenero(rs.getString("genero").charAt(0));
            anuncio.setAnunciante(rs.getString("anunciante"));
            anuncio.setContacto(rs.getInt("contacto"));
            anuncio.setTipologia(rs.getString("tipologia"));
            anuncio.setTipo(rs.getString("tipo").charAt(0));
            anuncio.setData(rs.getDate("data"));
            anuncio.setEstado(rs.getString("estado").charAt(0));
            return anuncio;
        }
    }
}
