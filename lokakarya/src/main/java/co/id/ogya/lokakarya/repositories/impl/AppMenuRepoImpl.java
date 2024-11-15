package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.AppMenu;
import co.id.ogya.lokakarya.repositories.AppMenuRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class AppMenuRepoImpl implements AppMenuRepo {

    private final RowMapper<AppMenu> rowMapper = new BeanPropertyRowMapper<>(AppMenu.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AppMenu> getAppMenus() {
        String sql = "SELECT * FROM TBL_APP_MENU";
        log.info("Executing query to fetch all AppMenus: {}", sql);
        List<AppMenu> appMenus = jdbcTemplate.query(sql, rowMapper);
        log.info("Successfully fetched {} AppMenus", appMenus.size());
        return appMenus;
    }

    @Override
    public AppMenu getAppMenuById(String id) {
        String sql = "SELECT * FROM TBL_APP_MENU WHERE ID = ?";
        log.info("Executing query to fetch AppMenu by ID: {} using query: {}", id, sql);
        try {
            AppMenu appMenu = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Successfully fetched AppMenu: {}", appMenu);
            return appMenu;
        } catch (Exception e) {
            log.error("Error fetching AppMenu by ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public AppMenu saveAppMenu(AppMenu appMenu) {
        appMenu.prePersist();
        String sql = "INSERT INTO TBL_APP_MENU (ID, MENU_NAME, CREATED_BY) VALUES (?, ?, ?)";
        log.info("Executing query to save AppMenu: {} using query: {}", appMenu, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, appMenu.getId(), appMenu.getMenuName(), appMenu.getCreatedBy());
            if (rowsAffected > 0) {
                log.info("Successfully saved AppMenu: {}", appMenu);
                return appMenu;
            } else {
                log.warn("Failed to save AppMenu: {}", appMenu);
                return null;
            }
        } catch (Exception e) {
            log.error("Error saving AppMenu: {}. Error: {}", appMenu, e.getMessage());
            return null;
        }
    }

    @Override
    public AppMenu updateAppMenu(AppMenu appMenu) {
        String sql = "UPDATE TBL_APP_MENU SET MENU_NAME = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        log.info("Executing query to update AppMenu with ID: {} using query: {}", appMenu.getId(), sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, appMenu.getMenuName(), appMenu.getUpdatedBy(), appMenu.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated AppMenu: {}", appMenu);
                return appMenu;
            } else {
                log.warn("Failed to update AppMenu with ID: {}", appMenu.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error updating AppMenu with ID: {}. Error: {}", appMenu.getId(), e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteAppMenu(String id) {
        String sql = "DELETE FROM TBL_APP_MENU WHERE ID = ?";
        log.info("Executing query to delete AppMenu with ID: {} using query: {}", id, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted AppMenu with ID: {}", id);
                return true;
            } else {
                log.warn("Failed to delete AppMenu with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error deleting AppMenu with ID: {}. Error: {}", id, e.getMessage());
            return false;
        }
    }
}
