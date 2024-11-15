package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.AppMenu;
import co.id.ogya.lokakarya.repositories.AppMenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppMenuRepoImpl implements AppMenuRepo {

    RowMapper<AppMenu> rowMapper = new BeanPropertyRowMapper<>(AppMenu.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AppMenu> getAppMenus() {
        String sql = "SELECT * FROM TBL_APP_MENU";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public AppMenu getAppMenuById(String id) {
        String sql = "SELECT * FROM TBL_APP_MENU WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public AppMenu saveAppMenu(AppMenu appMenu) {
        String sql = "INSERT INTO TBL_APP_MENU (ID, MENU_NAME, CREATED_BY) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, appMenu.getId(), appMenu.getMenuName(), appMenu.getCreatedBy());
        if (rowsAffected > 0) {
            return appMenu;
        } else {
            return null;
        }
    }

    @Override
    public AppMenu updateAppMenu(AppMenu appMenu) {
        String sql = "UPDATE TBL_APP_MENU SET MENU_NAME = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, appMenu.getMenuName(), appMenu.getUpdatedBy(), appMenu.getId());
        if (rowsAffected > 0) {
            return appMenu;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteAppMenu(String id) {
        String sql = "DELETE FROM TBL_APP_MENU WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
