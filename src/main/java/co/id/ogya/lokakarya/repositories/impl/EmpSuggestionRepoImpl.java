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
import java.util.Map;

@Slf4j
@Repository
public class EmpSuggestionRepoImpl implements EmpSuggestionRepo {

    private final RowMapper<EmpSuggestion> rowMapper = new BeanPropertyRowMapper<>(EmpSuggestion.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EmpSuggestion> getEmpSuggestions() {
        String sql = "SELECT * FROM TBL_EMP_SUGGESTION";
        log.info("Fetching all EmpSuggestions with query: {}", sql);
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
        log.info("Fetching EmpSuggestion by ID: {}. Query: {}", id, sql);
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
        String sql = "INSERT INTO TBL_EMP_SUGGESTION (ID, USER_ID, SUGGESTION, ASSESSMENT_YEAR, CREATED_BY) VALUES (?, ?, ?, ?, ?)";
        log.info("Saving EmpSuggestion: {}. Query: {}", empSuggestion, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, empSuggestion.getId(), empSuggestion.getUserId(),
                    empSuggestion.getSuggestion(), empSuggestion.getAssessmentYear(), empSuggestion.getCreatedBy());
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
        String sql = "UPDATE TBL_EMP_SUGGESTION SET USER_ID = ?, SUGGESTION = ?, ASSESSMENT_YEAR = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? " +
                "WHERE ID = ?";
        log.info("Updating EmpSuggestion with ID: {}. Query: {}", empSuggestion.getId(), sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, empSuggestion.getUserId(), empSuggestion.getSuggestion(),
                    empSuggestion.getAssessmentYear(), empSuggestion.getUpdatedBy(), empSuggestion.getId());
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
        log.info("Deleting EmpSuggestion with ID: {}. Query: {}", id, sql);
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

    @Override
    public List<Map<String, Object>> getEmpSuggestionGets() {
        String sql = "SELECT ES.ID, ES.USER_ID, AU.FULL_NAME, ES.SUGGESTION, ES.ASSESSMENT_YEAR " +
                "FROM TBL_EMP_SUGGESTION ES " +
                "LEFT JOIN TBL_APP_USER AU ON ES.USER_ID = AU.ID";
        log.info("Fetching all EmpSuggestions with LEFT JOIN query: {}", sql);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
            log.info("Successfully fetched {} EmpSuggestions", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error while fetching all EmpSuggestions. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> getEmpSuggestionGetByUserId(String userId) {
        String sql = "SELECT ES.ID, ES,USER_ID, AU.FULL_NAME, ES.SUGGESTION, ES.ASSESSMENT_YEAR " +
                "FROM TBL_EMP_SUGGESTION ES " +
                "LEFT JOIN TBL_APP_USER AU ON ES.USER_ID = AU.ID " +
                "WHERE ES.USER_ID = ?";
        log.info("Fetching EmpSuggestions by UserID: {} with LEFT JOIN query: {}", userId, sql);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, userId);
            log.info("Successfully fetched {} EmpSuggestions for UserID: {}", result.size(), userId);
            return result;
        } catch (Exception e) {
            log.error("Error while fetching EmpSuggestions for UserID: {}. Error: {}", userId, e.getMessage());
            throw e;
        }
    }
    @Override
    public List<Map<String, Object>> getEmpSuggestionGetByCreatedBy(String userId) {
        String sql = "SELECT ES.ID, ES.USER_ID, AU.FULL_NAME, ES.SUGGESTION, ES.ASSESSMENT_YEAR " +
                "FROM TBL_EMP_SUGGESTION ES " +
                "LEFT JOIN TBL_APP_USER AU ON ES.USER_ID = AU.ID " +
                "WHERE ES.CREATED_BY = ?";
        log.info("Fetching EmpSuggestions made by UserID: {} with LEFT JOIN query: {}", userId, sql);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, userId);
            log.info("Successfully fetched {} EmpSuggestions by UserID: {}", result.size(), userId);
            return result;
        } catch (Exception e) {
            log.error("Error while fetching EmpSuggestions by UserID: {}. Error: {}", userId, e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean ifAnyEmpSuggestionExist(String userId, int assessmentYear) {
        String sql = "SELECT ID FROM TBL_EMP_SUGGESTION WHERE USER_ID = ? AND ASSESSMENT_YEAR = ?";
        log.info("Looking for EmpSuggestion with User ID: {} and Assessment Year: {} with query: {}", userId, assessmentYear, sql);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, userId, assessmentYear);
            if (result.isEmpty()) {
                log.info("There is no EmpSuggestion with UserID: {} and Assessment Year: {}", userId, assessmentYear);
                return false;
            } else {
                log.info("There is an EmpSuggestion with UserID: {} and Assessment Year: {}", userId, assessmentYear);
                return true;
            }
        } catch (Exception e) {
            log.error("Error while looking EmpSuggestion by UserID: {} and Assessment Year: {}. Error: {}", userId, assessmentYear, e.getMessage());
            throw e;
        }
    }
}

