package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.AppRoleMenu;
import co.id.ogya.lokakarya.repositories.AppRoleMenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppRoleMenuRepoImpl implements AppRoleMenuRepo {

    RowMapper<AppRoleMenu> rowMapper = new BeanPropertyRowMapper<>(AppRoleMenu.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AppRoleMenu> getAppRoleMenus() {
        String sql = "SELECT * FROM TBL_APP_ROLE_MENU";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public AppRoleMenu getAppRoleMenuById(String id) {
        String sql = "SELECT * FROM TBL_APP_ROLE_MENU WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public AppRoleMenu saveAppRoleMenu(AppRoleMenu appRoleMenu) {
        String sql = "INSERT INTO TBL_APP_ROLE_MENU (ID, ROLE_ID, MENU_ID) VALUES (?,?,?)";
        int rowsAffected = jdbcTemplate.update(sql, appRoleMenu.getId(), appRoleMenu.getRoleId(), appRoleMenu.getMenuId());
        if (rowsAffected > 0) {
            return appRoleMenu;
        } else {
            return null;
        }
    }

    @Override
    public AppRoleMenu updateAppRoleMenu(AppRoleMenu appRoleMenu) {
        String sql = "UPDATE TBL_APP_ROLE_MENU SET ROLE_ID = ?, MENU_ID = ? WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, appRoleMenu.getRoleId(), appRoleMenu.getMenuId(), appRoleMenu.getId());
        if (rowsAffected > 0) {
            return appRoleMenu;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteAppRoleMenu(String id) {
        String sql = "DELETE FROM TBL_APP_ROLE_MENU WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
