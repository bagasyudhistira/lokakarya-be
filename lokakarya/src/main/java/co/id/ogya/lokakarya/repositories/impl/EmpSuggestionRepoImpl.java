package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.EmpSuggestion;
import co.id.ogya.lokakarya.repositories.EmpSuggestionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class EmpSuggestionRepoImpl implements EmpSuggestionRepo {

    private final RowMapper<EmpSuggestion> rowMapper = new BeanPropertyRowMapper<>(EmpSuggestion.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EmpSuggestion> getEmpSuggestions() {
        String sql = "SELECT * FROM TBL_EMP_SUGGESTION";
        log.info("Executing query to fetch all EmpSuggestions: {}", sql);
        try {
            List<EmpSuggestion> result = jdbcTemplate.query(sql, rowMapper);
            log.info("Successfully fetched {} EmpSuggestions", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error while fetching all EmpSuggestions. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public EmpSuggestion getEmpSuggestionById(String id) {
        String sql = "SELECT * FROM TBL_EMP_SUGGESTION WHERE ID = ?";
        log.info("Executing query to fetch EmpSuggestion by ID: {}. Query: {}", id, sql);
        try {
            EmpSuggestion result = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Successfully fetched EmpSuggestion: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error while fetching EmpSuggestion with ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public EmpSuggestion saveEmpSuggestion(EmpSuggestion empSuggestion) {
        empSuggestion.prePersist();
        String sql = "INSERT INTO TBL_EMP_SUGGESTION (ID, USER_ID, SUGGESTION, ASSESSMENT_YEAR, CREATED_BY) VALUES (?,?,?,?,?)";
        log.info("Executing query to save EmpSuggestion: {}. Query: {}", empSuggestion, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, empSuggestion.getId(), empSuggestion.getUserId(), empSuggestion.getSuggestion(), empSuggestion.getAssessmentYear(), empSuggestion.getCreatedBy());
            if (rowsAffected > 0) {
                log.info("Successfully saved EmpSuggestion: {}", empSuggestion);
                return empSuggestion;
            } else {
                log.warn("No rows affected while saving EmpSuggestion: {}", empSuggestion);
                return null;
            }
        } catch (Exception e) {
            log.error("Error while saving EmpSuggestion: {}. Error: {}", empSuggestion, e.getMessage());
            return null;
        }
    }

    @Override
    public EmpSuggestion updateEmpSuggestion(EmpSuggestion empSuggestion) {
        String sql = "UPDATE TBL_EMP_SUGGESTION SET USER_ID = ?, SUGGESTION = ?, ASSESSMENT_YEAR = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        log.info("Executing query to update EmpSuggestion with ID: {}. Query: {}", empSuggestion.getId(), sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, empSuggestion.getUserId(), empSuggestion.getSuggestion(), empSuggestion.getAssessmentYear(), empSuggestion.getUpdatedBy(), empSuggestion.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated EmpSuggestion: {}", empSuggestion);
                return empSuggestion;
            } else {
                log.warn("No rows affected while updating EmpSuggestion with ID: {}", empSuggestion.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error while updating EmpSuggestion with ID: {}. Error: {}", empSuggestion.getId(), e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteEmpSuggestion(String id) {
        String sql = "DELETE FROM TBL_EMP_SUGGESTION WHERE ID = ?";
        log.info("Executing query to delete EmpSuggestion with ID: {}. Query: {}", id, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted EmpSuggestion with ID: {}", id);
                return true;
            } else {
                log.warn("No rows affected while deleting EmpSuggestion with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error while deleting EmpSuggestion with ID: {}. Error: {}", id, e.getMessage());
            return false;
        }
    }
}
