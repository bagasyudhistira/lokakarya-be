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

@Slf4j
@Repository
public class AccessDivisionRepoImpl implements AccessDivisionRepo {

    private final RowMapper<AccessDivision> rowMapper = new BeanPropertyRowMapper<>(AccessDivision.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AccessDivision> getAccessDivisions() {
        String sql = "SELECT * FROM TBL_ACCESS_DIVISION";
        try {
            log.info("Fetching all AccessDivision records");
            List<AccessDivision> accessDivisions = jdbcTemplate.query(sql, rowMapper);
            log.info("Fetched {} AccessDivision records", accessDivisions.size());
            return accessDivisions;
        } catch (Exception e) {
            log.error("Error fetching AccessDivision records: {}", e.getMessage(), e);
            throw e; // Rethrow the exception or handle accordingly.
        }
    }

    @Override
    public AccessDivision getAccessDivisionById(String id) {
        String sql = "SELECT * FROM TBL_ACCESS_DIVISION WHERE ID = ?";
        try {
            log.info("Fetching AccessDivision record with ID: {}", id);
            AccessDivision accessDivision = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Fetched AccessDivision record: {}", accessDivision);
            return accessDivision;
        } catch (Exception e) {
            log.error("Error fetching AccessDivision record with ID {}: {}", id, e.getMessage(), e);
            throw e; // Rethrow the exception or handle accordingly.
        }
    }

    @Override
    public AccessDivision saveAccessDivision(AccessDivision accessDivision) {
        accessDivision.prePersist();
String sql = "INSERT INTO TBL_ACCESS_DIVISION (ID, USER_ID, DIVISION_ID) VALUES (?,?,?)";
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
            throw e; // Rethrow the exception or handle accordingly.
        }
    }

    @Override
    public AccessDivision updateAccessDivision(AccessDivision accessDivision) {
        String sql = "UPDATE TBL_ACCESS_DIVISION SET USER_ID = ?, DIVISION_ID = ? WHERE ID = ?";
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
            throw e; // Rethrow the exception or handle accordingly.
        }
    }

    @Override
    public Boolean deleteAccessDivision(String id) {
        String sql = "DELETE FROM TBL_ACCESS_DIVISION WHERE ID = ?";
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
            throw e; // Rethrow the exception or handle accordingly.
        }
    }
}
