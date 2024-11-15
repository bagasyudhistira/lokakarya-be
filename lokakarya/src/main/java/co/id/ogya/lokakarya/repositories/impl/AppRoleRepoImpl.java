package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.AppRole;
import co.id.ogya.lokakarya.repositories.AppRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppRoleRepoImpl implements AppRoleRepo {

    RowMapper<AppRole> rowMapper = new BeanPropertyRowMapper<>(AppRole.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AppRole> getAppRoles() {
        String sql = "SELECT * FROM TBL_APP_ROLE";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public AppRole getAppRoleById(String id) {
        String sql = "SELECT * FROM TBL_APP_ROLE WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public AppRole saveAppRole(AppRole appRole) {
        String sql = "INSERT INTO TBL_APP_ROLE (ID, ROLENAME, CREATED_BY) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, appRole.getId(), appRole.getRolename(), appRole.getCreatedBy());
        if (rowsAffected > 0) {
            return appRole;
        } else {
            return null;
        }
    }

    @Override
    public AppRole updateAppRole(AppRole appRole) {
        String sql = "UPDATE TBL_APP_MENU SET ROLENAME = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, appRole.getRolename(), appRole.getUpdatedBy(), appRole.getId());
        if (rowsAffected > 0) {
            return appRole;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteAppRole(String id) {
        String sql = "DELETE FROM TBL_APP_ROLE WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
