package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.AppUserRole;
import co.id.ogya.lokakarya.repositories.AppUserRoleRepo;
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
public class AppUserRoleRepoImpl implements AppUserRoleRepo {

    private final RowMapper<AppUserRole> rowMapper = new BeanPropertyRowMapper<>(AppUserRole.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AppUserRole> getAppUserRoles() {
        String sql = "SELECT * FROM TBL_APP_USER_ROLE";
        try {
            log.info("Fetching all AppUserRoles");
            List<AppUserRole> appUserRoles = jdbcTemplate.query(sql, rowMapper);
            log.info("Successfully fetched {} AppUserRoles", appUserRoles.size());
            return appUserRoles;
        } catch (Exception e) {
            log.error("Error fetching AppUserRoles: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public AppUserRole getAppUserRoleById(String id) {
        String sql = "SELECT * FROM TBL_APP_USER_ROLE WHERE ID = ?";
        try {
            log.info("Fetching AppUserRole by ID: {}", id);
            AppUserRole appUserRole = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Successfully fetched AppUserRole: {}", appUserRole);
            return appUserRole;
        } catch (Exception e) {
            log.error("Error fetching AppUserRole by ID: {}. Error: {}", id, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public AppUserRole saveAppUserRole(AppUserRole appUserRole) {
        appUserRole.prePersist();
        String sql = "INSERT INTO TBL_APP_USER_ROLE (ID, ROLE_ID, USER_ID) VALUES (?, ?, ?)";
        try {
            log.info("Saving AppUserRole: {}", appUserRole);
            int rowsAffected = jdbcTemplate.update(sql, appUserRole.getId(), appUserRole.getRoleId(), appUserRole.getUserId());
            if (rowsAffected > 0) {
                log.info("Successfully saved AppUserRole: {}", appUserRole);
                return appUserRole;
            } else {
                log.warn("Failed to save AppUserRole: {}", appUserRole);
                return null;
            }
        } catch (Exception e) {
            log.error("Error saving AppUserRole: {}. Error: {}", appUserRole, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public AppUserRole updateAppUserRole(AppUserRole appUserRole) {
        String sql = "UPDATE TBL_APP_USER_ROLE SET ROLE_ID = ?, USER_ID = ? WHERE ID = ?";
        try {
            log.info("Updating AppUserRole with ID: {}", appUserRole.getId());
            int rowsAffected = jdbcTemplate.update(sql, appUserRole.getRoleId(), appUserRole.getUserId(), appUserRole.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated AppUserRole: {}", appUserRole);
                return appUserRole;
            } else {
                log.warn("Failed to update AppUserRole with ID: {}", appUserRole.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error updating AppUserRole with ID: {}. Error: {}", appUserRole.getId(), e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Boolean deleteAppUserRole(String id) {
        String sql = "DELETE FROM TBL_APP_USER_ROLE WHERE ID = ?";
        try {
            log.info("Deleting AppUserRole with ID: {}", id);
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted AppUserRole with ID: {}", id);
                return true;
            } else {
                log.warn("Failed to delete AppUserRole with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error deleting AppUserRole with ID: {}. Error: {}", id, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> getAppUserRoleGets() {
        String sql = "SELECT AUR.ID, AU.FULL_NAME, AR.ROLENAME FROM TBL_APP_USER_ROLE AUR " +
                "LEFT JOIN TBL_APP_USER AU ON AUR.USER_ID = AU.ID " +
                "LEFT JOIN TBL_APP_ROLE AR ON AUR.ROLE_ID = AR.ID";
        try {
            log.info("Fetching AppUserRoles");
            List<Map<String, Object>> userRoles = jdbcTemplate.queryForList(sql);
            log.info("Successfully fetched {} AppUserRoles", userRoles.size());
            return userRoles;
        } catch (Exception e) {
            log.error("Error fetching AppUserRoles, Error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> getAppUserRoleGetById(String userId) {
        String sql = "SELECT AUR.ID, AU.FULL_NAME, AR.ROLENAME " +
                "FROM TBL_APP_USER_ROLE AUR LEFT JOIN TBL_APP_USER AU ON AUR.USER_ID = AU.ID " +
                "LEFT JOIN TBL_APP_ROLE AR ON AUR.ROLE_ID = AR.ID " +
                "WHERE AUR.USER_ID = ?";
        try {
            log.info("Fetching AppUserRoles for User ID: {}", userId);
            List<Map<String, Object>> userRoles = jdbcTemplate.queryForList(sql, userId);
            log.info("Successfully fetched {} AppUserRoles for User ID: {}", userRoles.size(), userId);
            return userRoles;
        } catch (Exception e) {
            log.error("Error fetching AppUserRoles for User ID: {}. Error: {}", userId, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<String> getAppUserRoleNamesById(String userId) {
        String sql = "SELECT AR.ROLENAME " +
                "FROM TBL_APP_USER_ROLE AUR LEFT JOIN TBL_APP_USER AU ON AUR.USER_ID = AU.ID " +
                "LEFT JOIN TBL_APP_ROLE AR ON AUR.ROLE_ID = AR.ID " +
                "WHERE AUR.USER_ID = ?";
        try {
            log.info("Fetching AppUserRoles for User ID: {}", userId);
            List<Map<String, Object>> userRoles = jdbcTemplate.queryForList(sql, userId);

            List<String> roleNames = userRoles.stream()
                    .map(role -> (String) role.get("ROLENAME"))
                    .toList();

            log.info("Successfully fetched roles: {} for User ID: {}", roleNames, userId);
            return roleNames;
        } catch (Exception e) {
            log.error("Error fetching AppUserRoles for User ID: {}. Error: {}", userId, e.getMessage(), e);
            throw e;
        }
    }

}
