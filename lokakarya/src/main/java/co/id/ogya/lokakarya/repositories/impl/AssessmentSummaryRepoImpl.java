package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.AssessmentSummary;
import co.id.ogya.lokakarya.repositories.AssessmentSummaryRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class AssessmentSummaryRepoImpl implements AssessmentSummaryRepo {

    private final RowMapper<AssessmentSummary> rowMapper = new BeanPropertyRowMapper<>(AssessmentSummary.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AssessmentSummary> getAssessmentSummarys() {
        String sql = "SELECT * FROM TBL_ASSESSMENT_SUMMARY";
        log.info("Executing query to fetch all AssessmentSummary records: {}", sql);
        try {
            List<AssessmentSummary> result = jdbcTemplate.query(sql, rowMapper);
            log.info("Successfully fetched {} AssessmentSummary records.", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching all AssessmentSummary records. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public AssessmentSummary getAssessmentSummaryById(String id) {
        String sql = "SELECT * FROM TBL_ASSESSMENT_SUMMARY WHERE ID = ?";
        log.info("Executing query to fetch AssessmentSummary by ID: {} using query: {}", id, sql);
        try {
            AssessmentSummary result = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Successfully fetched AssessmentSummary: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error fetching AssessmentSummary by ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public AssessmentSummary saveAssessmentSummary(AssessmentSummary assessmentSummary) {
        assessmentSummary.prePersist();
        String sql = "INSERT INTO TBL_ASSESSMENT_SUMMARY (ID, USER_ID, YEAR, SCORE, STATUS, CREATED_BY) VALUES (?, ?, ?, ?, ?, ?)";
        log.info("Executing query to save AssessmentSummary: {} using query: {}", assessmentSummary, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, assessmentSummary.getId(), assessmentSummary.getUserId(), assessmentSummary.getYear(), assessmentSummary.getScore(), assessmentSummary.getStatus(), assessmentSummary.getCreatedBy());
            if (rowsAffected > 0) {
                log.info("Successfully saved AssessmentSummary: {}", assessmentSummary);
                return assessmentSummary;
            } else {
                log.warn("No rows affected while saving AssessmentSummary: {}", assessmentSummary);
                return null;
            }
        } catch (Exception e) {
            log.error("Error saving AssessmentSummary: {}. Error: {}", assessmentSummary, e.getMessage());
            return null;
        }
    }

    @Override
    public AssessmentSummary updateAssessmentSummary(AssessmentSummary assessmentSummary) {
        String sql = "UPDATE TBL_ASSESSMENT_SUMMARY SET USER_ID = ?, YEAR = ?, SCORE = ?, STATUS = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        log.info("Executing query to update AssessmentSummary with ID: {} using query: {}", assessmentSummary.getId(), sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, assessmentSummary.getUserId(), assessmentSummary.getYear(), assessmentSummary.getScore(), assessmentSummary.getStatus(), assessmentSummary.getUpdatedBy(), assessmentSummary.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated AssessmentSummary: {}", assessmentSummary);
                return assessmentSummary;
            } else {
                log.warn("No rows affected while updating AssessmentSummary with ID: {}", assessmentSummary.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error updating AssessmentSummary with ID: {}. Error: {}", assessmentSummary.getId(), e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteAssessmentSummary(String id) {
        String sql = "DELETE FROM TBL_ASSESSMENT_SUMMARY WHERE ID = ?";
        log.info("Executing query to delete AssessmentSummary with ID: {} using query: {}", id, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted AssessmentSummary with ID: {}", id);
                return true;
            } else {
                log.warn("No rows affected while deleting AssessmentSummary with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error deleting AssessmentSummary with ID: {}. Error: {}", id, e.getMessage());
            return false;
        }
    }
}
