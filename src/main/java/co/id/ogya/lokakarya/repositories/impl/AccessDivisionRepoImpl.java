package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.AccessDivision;
import co.id.ogya.lokakarya.repositories.AccessDivisionRepo;
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
public class AccessDivisionRepoImpl implements AccessDivisionRepo {

    private final RowMapper<AccessDivision> rowMapper = new BeanPropertyRowMapper<>(AccessDivision.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AccessDivision> getAccessDivisions() {
        String sql = "SELECT * FROM tbl_access_division";
        try {
            log.info("Fetching all AccessDivision records");
            List<AccessDivision> accessDivisions = jdbcTemplate.query(sql, rowMapper);
            log.info("Fetched {} AccessDivision records", accessDivisions.size());
            return accessDivisions;
        } catch (Exception e) {
            log.error("Error fetching AccessDivision records: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public AccessDivision getAccessDivisionById(String id) {
        String sql = "SELECT * FROM tbl_access_division WHERE ID = ?";
        try {
            log.info("Fetching AccessDivision record with ID: {}", id);
            AccessDivision accessDivision = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Fetched AccessDivision record: {}", accessDivision);
            return accessDivision;
        } catch (Exception e) {
            log.error("Error fetching AccessDivision record with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> getAccessDivisionGets() {
        String sql = "SELECT ad.ID, au.FULL_NAME, d.DIVISION_NAME " +
                "FROM tbl_access_division ad " +
                "LEFT JOIN tbl_division d ON ad.DIVISION_ID = d.ID " +
                "LEFT JOIN tbl_app_user au ON ad.USER_ID = au.ID";
        try {
            log.info("Fetching AccessDivision details with LEFT JOIN");
            List<Map<String, Object>> accessDivisions = jdbcTemplate.queryForList(sql);
            log.info("Fetched {} AccessDivision details", accessDivisions.size());
            return accessDivisions;
        } catch (Exception e) {
            log.error("Error fetching AccessDivision details: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Map<String, Object> getAccessDivisionGetById(String id) {
        String sql = "SELECT ad.ID, au.FULL_NAME, d.DIVISION_NAME " +
                "FROM tbl_access_division ad " +
                "LEFT JOIN tbl_division d ON ad.DIVISION_ID = d.ID " +
                "LEFT JOIN tbl_app_user au ON ad.USER_ID = au.ID " +
                "WHERE ad.ID = ?";
        try {
            log.info("Fetching AccessDivision details for ID: {} with LEFT JOIN", id);
            Map<String, Object> accessDivision = jdbcTemplate.queryForMap(sql, id);
            log.info("Fetched AccessDivision details: {}", accessDivision);
            return accessDivision;
        } catch (Exception e) {
            log.error("Error fetching AccessDivision details for ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public AccessDivision saveAccessDivision(AccessDivision accessDivision) {
        accessDivision.prePersist();
        String sql = "INSERT INTO tbl_access_division (ID, USER_ID, DIVISION_ID) VALUES (?, ?, ?)";
        try {
            log.info("Saving AccessDivision record: {}", accessDivision);
            int rowsAffected = jdbcTemplate.update(sql, accessDivision.getId(), accessDivision.getUserId(), accessDivision.getDivisionId());
            if (rowsAffected > 0) {
                log.info("Successfully saved AccessDivision record");
                return accessDivision;
            } else {
                log.warn("Failed to save AccessDivision record: No rows affected");
                return null;
            }
        } catch (Exception e) {
            log.error("Error saving AccessDivision record: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public AccessDivision updateAccessDivision(AccessDivision accessDivision) {
        String sql = "UPDATE tbl_access_division SET USER_ID = ?, DIVISION_ID = ? WHERE ID = ?";
        try {
            log.info("Updating AccessDivision record: {}", accessDivision);
            int rowsAffected = jdbcTemplate.update(sql, accessDivision.getUserId(), accessDivision.getDivisionId(), accessDivision.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated AccessDivision record");
                return accessDivision;
            } else {
                log.warn("Failed to update AccessDivision record: No rows affected");
                return null;
            }
        } catch (Exception e) {
            log.error("Error updating AccessDivision record: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Boolean deleteAccessDivision(String id) {
        String sql = "DELETE FROM tbl_access_division WHERE ID = ?";
        try {
            log.info("Deleting AccessDivision record with ID: {}", id);
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted AccessDivision record with ID: {}", id);
                return true;
            } else {
                log.warn("Failed to delete AccessDivision record with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error deleting AccessDivision record with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }
}
