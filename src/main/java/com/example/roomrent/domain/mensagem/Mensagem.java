package com.example.roomrent.domain.mensagem;

import java.util.Date;

import lombok.*;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Mensagem {
    private Long id;

    private String remetente;
    private String mensagem;
    private Date data;

    private Long idAnuncio;

    public static class MensagemRowMapper implements RowMapper<Mensagem>{
        @Override
        public Mensagem mapRow(ResultSet rs, int rowNum) throws SQLException{
            Mensagem mensagem = new Mensagem();
            mensagem.setId(rs.getLong("id"));
            mensagem.setRemetente(rs.getString("remetente"));
            mensagem.setMensagem(rs.getString("mensagem"));
            mensagem.setData(rs.getDate("data"));
            mensagem.setIdAnuncio(rs.getLong("anuncio_id"));
            return mensagem;
        }
    }
}
