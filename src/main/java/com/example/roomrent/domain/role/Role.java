package com.example.roomrent.domain.role;

import lombok.*;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Long id;

    private String nome;

    public static class RoleRowMapper implements RowMapper<Role>{
        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException{
            Role role = new Role();
            role.setId(rs.getLong("id"));
            role.setNome(rs.getString("name"));
            return role;
        }
    }
}
