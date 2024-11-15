package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.AppRoleMenu;
import co.id.ogya.lokakarya.entities.AppUserRole;
import co.id.ogya.lokakarya.repositories.AppUserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppUserRoleRepoImpl implements AppUserRoleRepo {

    RowMapper<AppUserRole> rowMapper = new BeanPropertyRowMapper<>(AppUserRole.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AppUserRole> getAppUserRoles() {
        String sql = "SELECT * FROM TBL_APP_USER_ROLE";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public AppUserRole getAppUserRoleById(String id) {
        String sql = "SELECT * FROM TBL_APP_USER_ROLE WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public AppUserRole saveAppUserRole(AppUserRole appUserRole) {
        String sql = "INSERT INTO TBL_APP_USER_ROLE (ID, ROLE_ID, USER_ID) VALUES (?,?,?)";
        int rowsAffected = jdbcTemplate.update(sql, appUserRole.getId(), appUserRole.getRoleId(), appUserRole.getUserId());
        if (rowsAffected > 0) {
            return appUserRole;
        } else {
            return null;
        }
    }

    @Override
    public AppUserRole updateAppUserRole(AppUserRole appUserRole) {
        String sql = "UPDATE TBL_APP_USER_ROLE SET ROLE_ID = ?, USER_ID = ? WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, appUserRole.getRoleId(), appUserRole.getUserId(), appUserRole.getId());
        if (rowsAffected > 0) {
            return appUserRole;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteAppUserRole(String id) {
        String sql = "DELETE FROM TBL_APP_USER_ROLE WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
