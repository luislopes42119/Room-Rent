package com.example.roomrent.domain.userRole;

import lombok.*;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    private Long idUser;
    private Long idRole;

    public static class UserRoleRowMapper implements RowMapper<UserRole>{
        @Override
        public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException{
            UserRole userRole = new UserRole();
            userRole.setIdUser(rs.getLong("user_id"));
            userRole.setIdRole(rs.getLong("role_id"));
            return userRole;
        }
    }    
}
