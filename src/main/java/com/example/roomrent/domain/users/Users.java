package com.example.roomrent.domain.users;

import lombok.*;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private Long id;

    private String nome;
    private String user_name;
    private String password;

    public static class UsersRowMapper implements RowMapper<Users>{
        @Override
        public Users mapRow(ResultSet rs, int rowNum) throws SQLException{
            Users users = new Users();
            users.setId(rs.getLong("id"));
            users.setNome(rs.getString("nome"));
            users.setUser_name(rs.getString("user_name"));
            users.setPassword(rs.getString("password"));
            return users;
        }
    }
}
