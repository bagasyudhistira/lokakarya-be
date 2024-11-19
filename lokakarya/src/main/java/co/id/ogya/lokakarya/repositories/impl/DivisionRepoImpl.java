package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.Division;
import co.id.ogya.lokakarya.repositories.DivisionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class DivisionRepoImpl implements DivisionRepo {

    private final RowMapper<Division> rowMapper = new BeanPropertyRowMapper<>(Division.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Division> getDivisions() {
        String sql = "SELECT * FROM TBL_DIVISION";
        log.info("Executing query to fetch all divisions: {}", sql);
        try {
            List<Division> result = jdbcTemplate.query(sql, rowMapper);
            log.info("Successfully fetched {} divisions.", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching divisions. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Division getDivisionById(String id) {
        String sql = "SELECT * FROM TBL_DIVISION " +
                "WHERE ID = ?";
        log.info("Executing query to fetch division by ID: {} using query: {}", id, sql);
        try {
            Division result = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Successfully fetched division: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error fetching division by ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public Division saveDivision(Division division) {
        division.prePersist();
        String sql = "INSERT INTO TBL_DIVISION (ID, DIVISION_NAME, CREATED_BY) VALUES (?, ?, ?)";
        log.info("Executing query to save division: {} using query: {}", division, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, division.getId(), division.getDivisionName(), division.getCreatedBy());
            if (rowsAffected > 0) {
                log.info("Successfully saved division: {}", division);
                return division;
            } else {
                log.warn("No rows affected while saving division: {}", division);
                return null;
            }
        } catch (Exception e) {
            log.error("Error saving division: {}. Error: {}", division, e.getMessage());
            return null;
        }
    }

    @Override
    public Division updateDivision(Division division) {
        String sql = "UPDATE TBL_DIVISION SET DIVISION_NAME = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? " +
                "WHERE ID = ?";
        log.info("Executing query to update division with ID: {} using query: {}", division.getId(), sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, division.getDivisionName(), division.getUpdatedBy(), division.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated division: {}", division);
                return division;
            } else {
                log.warn("No rows affected while updating division with ID: {}", division.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error updating division with ID: {}. Error: {}", division.getId(), e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteDivision(String id) {
        String sql = "DELETE FROM TBL_DIVISION " +
                "WHERE ID = ?";
        log.info("Executing query to delete division with ID: {} using query: {}", id, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted division with ID: {}", id);
                return true;
            } else {
                log.warn("No rows affected while deleting division with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error deleting division with ID: {}. Error: {}", id, e.getMessage());
            return false;
        }
    }
}
