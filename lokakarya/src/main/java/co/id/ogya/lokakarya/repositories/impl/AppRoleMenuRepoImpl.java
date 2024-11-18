package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.AppRoleMenu;
import co.id.ogya.lokakarya.repositories.AppRoleMenuRepo;
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
public class AppRoleMenuRepoImpl implements AppRoleMenuRepo {

    private final RowMapper<AppRoleMenu> rowMapper = new BeanPropertyRowMapper<>(AppRoleMenu.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AppRoleMenu> getAppRoleMenus() {
        String sql = "SELECT * FROM TBL_APP_ROLE_MENU";
        log.info("Executing query to fetch all AppRoleMenus: {}", sql);
        List<AppRoleMenu> appRoleMenus = jdbcTemplate.query(sql, rowMapper);
        log.info("Successfully fetched {} AppRoleMenus", appRoleMenus.size());
        return appRoleMenus;
    }

    @Override
    public AppRoleMenu getAppRoleMenuById(String id) {
        String sql = "SELECT * FROM TBL_APP_ROLE_MENU WHERE ID = ?";
        log.info("Executing query to fetch AppRoleMenu by ID: {} using query: {}", id, sql);
        try {
            AppRoleMenu appRoleMenu = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Successfully fetched AppRoleMenu: {}", appRoleMenu);
            return appRoleMenu;
        } catch (Exception e) {
            log.error("Error fetching AppRoleMenu by ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public AppRoleMenu saveAppRoleMenu(AppRoleMenu appRoleMenu) {
        appRoleMenu.prePersist();
        String sql = "INSERT INTO TBL_APP_ROLE_MENU (ID, ROLE_ID, MENU_ID) VALUES (?, ?, ?)";
        log.info("Executing query to save AppRoleMenu: {} using query: {}", appRoleMenu, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, appRoleMenu.getId(), appRoleMenu.getRoleId(), appRoleMenu.getMenuId());
            if (rowsAffected > 0) {
                log.info("Successfully saved AppRoleMenu: {}", appRoleMenu);
                return appRoleMenu;
            } else {
                log.warn("Failed to save AppRoleMenu: {}", appRoleMenu);
                return null;
            }
        } catch (Exception e) {
            log.error("Error saving AppRoleMenu: {}. Error: {}", appRoleMenu, e.getMessage());
            return null;
        }
    }

    @Override
    public AppRoleMenu updateAppRoleMenu(AppRoleMenu appRoleMenu) {
        String sql = "UPDATE TBL_APP_ROLE_MENU SET ROLE_ID = ?, MENU_ID = ? WHERE ID = ?";
        log.info("Executing query to update AppRoleMenu with ID: {} using query: {}", appRoleMenu.getId(), sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, appRoleMenu.getRoleId(), appRoleMenu.getMenuId(), appRoleMenu.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated AppRoleMenu: {}", appRoleMenu);
                return appRoleMenu;
            } else {
                log.warn("Failed to update AppRoleMenu with ID: {}", appRoleMenu.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error updating AppRoleMenu with ID: {}. Error: {}", appRoleMenu.getId(), e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteAppRoleMenu(String id) {
        String sql = "DELETE FROM TBL_APP_ROLE_MENU WHERE ID = ?";
        log.info("Executing query to delete AppRoleMenu with ID: {} using query: {}", id, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted AppRoleMenu with ID: {}", id);
                return true;
            } else {
                log.warn("Failed to delete AppRoleMenu with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error deleting AppRoleMenu with ID: {}. Error: {}", id, e.getMessage());
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> getAppRoleMenuGets() {
        String sql = "SELECT ARM.ID, AR.ROLENAME, AM.MENU_NAME FROM TBL_APP_ROLE_MENU ARM JOIN TBL_APP_ROLE AR ON ARM.ROLE_ID = AR.ID JOIN TBL_APP_MENU AM ON ARM.MENU_ID = AM.ID";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getAppRoleMenuGetByRoleId(String roleId) {
        String sql = "SELECT ARM.ID, AR.ROLENAME, AM.MENU_NAME FROM TBL_APP_ROLE_MENU ARM JOIN TBL_APP_ROLE AR ON ARM.ROLE_ID = AR.ID JOIN TBL_APP_MENU AM ON ARM.MENU_ID = AM.ID WHERE ARM.ROLE_ID = ?";
        return jdbcTemplate.queryForList(sql, roleId);
    }

    @Override
    public List<Map<String, Object>> getAppRoleMenuGetByMenuId(String menuId) {
        String sql = "SELECT ARM.ID, AR.ROLENAME, AM.MENU_NAME FROM TBL_APP_ROLE_MENU ARM JOIN TBL_APP_ROLE AR ON ARM.ROLE_ID = AR.ID JOIN TBL_APP_MENU AM ON ARM.MENU_ID = AM.ID WHERE ARM.MENU_ID = ?";
        return jdbcTemplate.queryForList(sql, menuId);
    }

}
