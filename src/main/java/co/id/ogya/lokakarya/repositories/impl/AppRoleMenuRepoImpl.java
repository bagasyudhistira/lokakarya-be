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
        String sql = "SELECT * FROM tbl_app_role_menu";
        try {
            log.info("Fetching all AppRoleMenu records");
            List<AppRoleMenu> appRoleMenus = jdbcTemplate.query(sql, rowMapper);
            log.info("Fetched {} AppRoleMenu records", appRoleMenus.size());
            return appRoleMenus;
        } catch (Exception e) {
            log.error("Error fetching AppRoleMenu records: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public AppRoleMenu getAppRoleMenuById(String id) {
        String sql = "SELECT * FROM tbl_app_role_menu WHERE ID = ?";
        try {
            log.info("Fetching AppRoleMenu record with ID: {}", id);
            AppRoleMenu appRoleMenu = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Fetched AppRoleMenu record: {}", appRoleMenu);
            return appRoleMenu;
        } catch (Exception e) {
            log.error("Error fetching AppRoleMenu with ID {}: {}", id, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getAppRoleMenuByRolename(String rolename) {
        String sql = "SELECT ARM.ID, ROLENAME, MENU_NAME  FROM tbl_app_role_menu ARM " +
                "LEFT JOIN tbl_app_role AR on ARM.ROLE_ID = AR.ID " +
                "LEFT JOIN tbl_app_menu AM on ARM.MENU_ID = AM.ID " +
                "WHERE ROLENAME = ?";
        try {
            log.info("Fetching AppRoleMenu record with role: {}", rolename);
            List<Map<String,Object>> appRoleMenu = jdbcTemplate.queryForList(sql, rolename);
            log.info("Fetched {} AppRoleMenu records", appRoleMenu.size());
            return appRoleMenu;
        } catch (Exception e) {
            log.error("Error fetching AppRoleMenu with role {}: {}", rolename, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public AppRoleMenu saveAppRoleMenu(AppRoleMenu appRoleMenu) {
        appRoleMenu.prePersist();
        String sql = "INSERT INTO tbl_app_role_menu (ID, ROLE_ID, MENU_ID) VALUES (?, ?, ?)";
        try {
            log.info("Saving AppRoleMenu record: {}", appRoleMenu);
            int rowsAffected = jdbcTemplate.update(sql, appRoleMenu.getId(), appRoleMenu.getRoleId(), appRoleMenu.getMenuId());
            if (rowsAffected > 0) {
                log.info("Successfully saved AppRoleMenu record");
                return appRoleMenu;
            } else {
                log.warn("Failed to save AppRoleMenu record: No rows affected");
                return null;
            }
        } catch (Exception e) {
            log.error("Error saving AppRoleMenu record: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public AppRoleMenu updateAppRoleMenu(AppRoleMenu appRoleMenu) {
        String sql = "UPDATE tbl_app_role_menu SET ROLE_ID = ?, MENU_ID = ? WHERE ID = ?";
        try {
            log.info("Updating AppRoleMenu record with ID: {}", appRoleMenu.getId());
            int rowsAffected = jdbcTemplate.update(sql, appRoleMenu.getRoleId(), appRoleMenu.getMenuId(), appRoleMenu.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated AppRoleMenu record");
                return appRoleMenu;
            } else {
                log.warn("Failed to update AppRoleMenu record with ID: {}", appRoleMenu.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error updating AppRoleMenu record with ID {}: {}", appRoleMenu.getId(), e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Boolean deleteAppRoleMenu(String id) {
        String sql = "DELETE FROM tbl_app_role_menu WHERE ID = ?";
        try {
            log.info("Deleting AppRoleMenu record with ID: {}", id);
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted AppRoleMenu record with ID: {}", id);
                return true;
            } else {
                log.warn("Failed to delete AppRoleMenu record with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error deleting AppRoleMenu record with ID {}: {}", id, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> getAppRoleMenuGets() {
        String sql = "SELECT ARM.ID, AR.ROLENAME, AM.MENU_NAME " +
                "FROM tbl_app_role_menu ARM " +
                "LEFT JOIN tbl_app_role AR ON ARM.ROLE_ID = AR.ID " +
                "LEFT JOIN tbl_app_menu AM ON ARM.MENU_ID = AM.ID";
        try {
            log.info("Fetching all detailed AppRoleMenu records with LEFT JOIN");
            List<Map<String, Object>> appRoleMenus = jdbcTemplate.queryForList(sql);
            log.info("Fetched {} detailed AppRoleMenu records", appRoleMenus.size());
            return appRoleMenus;
        } catch (Exception e) {
            log.error("Error fetching detailed AppRoleMenu records: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> getAppRoleMenuGetByRoleId(String roleId) {
        String sql = "SELECT ARM.ID, AR.ROLENAME, AM.MENU_NAME " +
                "FROM tbl_app_role_menu ARM " +
                "LEFT JOIN tbl_app_role AR ON ARM.ROLE_ID = AR.ID " +
                "LEFT JOIN tbl_app_menu AM ON ARM.MENU_ID = AM.ID " +
                "WHERE ARM.ROLE_ID = ?";
        try {
            log.info("Fetching AppRoleMenu records for Role ID: {}", roleId);
            List<Map<String, Object>> appRoleMenus = jdbcTemplate.queryForList(sql, roleId);
            log.info("Fetched {} AppRoleMenu records for Role ID: {}", appRoleMenus.size(), roleId);
            return appRoleMenus;
        } catch (Exception e) {
            log.error("Error fetching AppRoleMenu records for Role ID {}: {}", roleId, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> getAppRoleMenuGetByMenuId(String menuId) {
        String sql = "SELECT ARM.ID, AR.ROLENAME, AM.MENU_NAME " +
                "FROM tbl_app_role_menu ARM " +
                "LEFT JOIN tbl_app_role AR ON ARM.ROLE_ID = AR.ID " +
                "LEFT JOIN tbl_app_menu AM ON ARM.MENU_ID = AM.ID " +
                "WHERE ARM.MENU_ID = ?";
        try {
            log.info("Fetching AppRoleMenu records for Menu ID: {}", menuId);
            List<Map<String, Object>> appRoleMenus = jdbcTemplate.queryForList(sql, menuId);
            log.info("Fetched {} AppRoleMenu records for Menu ID: {}", appRoleMenus.size(), menuId);
            return appRoleMenus;
        } catch (Exception e) {
            log.error("Error fetching AppRoleMenu records for Menu ID {}: {}", menuId, e.getMessage(), e);
            throw e;
        }
    }
}
