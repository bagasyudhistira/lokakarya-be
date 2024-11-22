package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.AppRole;
import co.id.ogya.lokakarya.repositories.AppRoleRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class AppRoleRepoImpl implements AppRoleRepo {

    private final RowMapper<AppRole> rowMapper = new BeanPropertyRowMapper<>(AppRole.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AppRole> getAppRoles() {
        String sql = "SELECT * FROM TBL_APP_ROLE";
        log.info("Executing query to fetch all AppRoles: {}", sql);
        List<AppRole> appRoles = jdbcTemplate.query(sql, rowMapper);
        log.info("Successfully fetched {} AppRoles", appRoles.size());
        return appRoles;
    }

    @Override
    public AppRole getAppRoleById(String id) {
        String sql = "SELECT * FROM TBL_APP_ROLE " +
                "WHERE ID = ?";
        log.info("Executing query to fetch AppRole by ID: {} using query: {}", id, sql);
        try {
            AppRole appRole = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Successfully fetched AppRole: {}", appRole);
            return appRole;
        } catch (Exception e) {
            log.error("Error fetching AppRole by ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public AppRole saveAppRole(AppRole appRole) {
        appRole.prePersist();
        String sql = "INSERT INTO TBL_APP_ROLE (ID, ROLENAME, CREATED_BY) VALUES (?, ?, ?)";
        log.info("Executing query to save AppRole: {} using query: {}", appRole, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, appRole.getId(), appRole.getRolename(), appRole.getCreatedBy());
            if (rowsAffected > 0) {
                log.info("Successfully saved AppRole: {}", appRole);
                return appRole;
            } else {
                log.warn("Failed to save AppRole: {}", appRole);
                return null;
            }
        } catch (Exception e) {
            log.error("Error saving AppRole: {}. Error: {}", appRole, e.getMessage());
            return null;
        }
    }

    @Override
    public AppRole updateAppRole(AppRole appRole) {
        String sql = "UPDATE TBL_APP_ROLE SET ROLENAME = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? " +
                "WHERE ID = ?";
        log.info("Executing query to update AppRole with ID: {} using query: {}", appRole.getId(), sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, appRole.getRolename(), appRole.getUpdatedBy(), appRole.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated AppRole: {}", appRole);
                return appRole;
            } else {
                log.warn("Failed to update AppRole with ID: {}", appRole.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error updating AppRole with ID: {}. Error: {}", appRole.getId(), e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteAppRole(String id) {
        String sql = "DELETE FROM TBL_APP_ROLE " +
                "WHERE ID = ?";
        log.info("Executing query to delete AppRole with ID: {} using query: {}", id, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted AppRole with ID: {}", id);
                return true;
            } else {
                log.warn("Failed to delete AppRole with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error deleting AppRole with ID: {}. Error: {}", id, e.getMessage());
            return false;
        }
    }
}
