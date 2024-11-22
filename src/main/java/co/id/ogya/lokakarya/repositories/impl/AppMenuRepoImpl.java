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
import java.util.Map;

@Slf4j
@Repository
public class AppMenuRepoImpl implements AppMenuRepo {

    private final RowMapper<AppMenu> rowMapper = new BeanPropertyRowMapper<>(AppMenu.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AppMenu> getAppMenus() {
        String sql = "SELECT * FROM TBL_APP_MENU";
        try {
            log.info("Fetching all AppMenu records");
            List<AppMenu> appMenus = jdbcTemplate.query(sql, rowMapper);
            log.info("Fetched {} AppMenu records", appMenus.size());
            return appMenus;
        } catch (Exception e) {
            log.error("Error fetching AppMenu records: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public AppMenu getAppMenuById(String id) {
        String sql = "SELECT * FROM TBL_APP_MENU WHERE ID = ?";
        try {
            log.info("Fetching AppMenu record with ID: {}", id);
            AppMenu appMenu = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Fetched AppMenu record: {}", appMenu);
            return appMenu;
        } catch (Exception e) {
            log.error("Error fetching AppMenu record with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> getAppMenuGets() {
        String sql = "SELECT AM.ID, AM.MENU_NAME, AM.CREATED_AT, AU1.FULL_NAME AS CREATED_BY_NAME, " +
                "AM.UPDATED_AT, AU2.FULL_NAME AS UPDATED_BY_NAME " +
                "FROM TBL_APP_MENU AM " +
                "LEFT JOIN TBL_APP_USER AU1 ON AM.CREATED_BY = AU1.ID " +
                "LEFT JOIN TBL_APP_USER AU2 ON AM.UPDATED_BY = AU2.ID";
        try {
            log.info("Fetching detailed AppMenu records with LEFT JOIN");
            List<Map<String, Object>> appMenus = jdbcTemplate.queryForList(sql);
            log.info("Fetched {} detailed AppMenu records", appMenus.size());
            return appMenus;
        } catch (Exception e) {
            log.error("Error fetching detailed AppMenu records: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Map<String, Object> getAppMenuGetById(String id) {
        String sql = "SELECT AM.ID, AM.MENU_NAME, AM.CREATED_AT, AU1.FULL_NAME AS CREATED_BY_NAME, " +
                "AM.UPDATED_AT, AU2.FULL_NAME AS UPDATED_BY_NAME " +
                "FROM TBL_APP_MENU AM " +
                "LEFT JOIN TBL_APP_USER AU1 ON AM.CREATED_BY = AU1.ID " +
                "LEFT JOIN TBL_APP_USER AU2 ON AM.UPDATED_BY = AU2.ID " +
                "WHERE AM.ID = ?";
        try {
            log.info("Fetching detailed AppMenu record for ID: {} with LEFT JOIN", id);
            Map<String, Object> appMenu = jdbcTemplate.queryForMap(sql, id);
            log.info("Fetched detailed AppMenu record: {}", appMenu);
            return appMenu;
        } catch (Exception e) {
            log.error("Error fetching detailed AppMenu record for ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public AppMenu saveAppMenu(AppMenu appMenu) {
        appMenu.prePersist();
        String sql = "INSERT INTO TBL_APP_MENU (ID, MENU_NAME, CREATED_BY) VALUES (?, ?, ?)";
        try {
            log.info("Saving AppMenu record: {}", appMenu);
            int rowsAffected = jdbcTemplate.update(sql, appMenu.getId(), appMenu.getMenuName(), appMenu.getCreatedBy());
            if (rowsAffected > 0) {
                log.info("Successfully saved AppMenu record");
                return appMenu;
            } else {
                log.warn("Failed to save AppMenu record: No rows affected");
                return null;
            }
        } catch (Exception e) {
            log.error("Error saving AppMenu record: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public AppMenu updateAppMenu(AppMenu appMenu) {
        String sql = "UPDATE TBL_APP_MENU SET MENU_NAME = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        try {
            log.info("Updating AppMenu record: {}", appMenu);
            int rowsAffected = jdbcTemplate.update(sql, appMenu.getMenuName(), appMenu.getUpdatedBy(), appMenu.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated AppMenu record");
                return appMenu;
            } else {
                log.warn("Failed to update AppMenu record with ID: {}", appMenu.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error updating AppMenu record: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Boolean deleteAppMenu(String id) {
        String sql = "DELETE FROM TBL_APP_MENU WHERE ID = ?";
        try {
            log.info("Deleting AppMenu record with ID: {}", id);
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted AppMenu record with ID: {}", id);
                return true;
            } else {
                log.warn("Failed to delete AppMenu record with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error deleting AppMenu record with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }
}
