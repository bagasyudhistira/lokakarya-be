package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.EmpSuggestion;
import co.id.ogya.lokakarya.repositories.EmpSuggestionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        String sql = "SELECT * FROM tbl_emp_suggestion";
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
        String sql = "SELECT * FROM tbl_emp_suggestion WHERE ID = ?";
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
        String sql = "INSERT INTO tbl_emp_suggestion (ID, USER_ID, SUGGESTION, ASSESSMENT_YEAR, CREATED_BY) VALUES (?, ?, ?, ?, ?)";
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
        String sql = "UPDATE tbl_emp_suggestion SET USER_ID = ?, SUGGESTION = ?, ASSESSMENT_YEAR = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? " +
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
        String sql = "DELETE FROM tbl_emp_suggestion WHERE ID = ?";
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
                "FROM tbl_emp_suggestion ES " +
                "LEFT JOIN tbl_app_user AU ON ES.USER_ID = AU.ID";
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
    public List<Map<String, Object>> getEmpSuggestionGetsPerPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT ES.ID, ES.USER_ID, AU.FULL_NAME, ES.SUGGESTION, ES.ASSESSMENT_YEAR " +
                "FROM tbl_emp_suggestion ES " +
                "LEFT JOIN tbl_app_user AU ON ES.USER_ID = AU.ID ORDER BY FULL_NAME ASC LIMIT ? OFFSET ?";
        try {
            log.info("Fetching all EmpSuggestions for page {} with maximum {} entries", page, pageSize);
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, pageSize, offset);
            log.info("Successfully fetched Achievements for Page {} ({} entries)", page, result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching EmpSuggestions: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> getEmpSuggestionGetByUserId(String userId) {
        String sql = "SELECT ES.ID, ES,USER_ID, AU.FULL_NAME, ES.SUGGESTION, ES.ASSESSMENT_YEAR " +
                "FROM tbl_emp_suggestion ES " +
                "LEFT JOIN tbl_app_user AU ON ES.USER_ID = AU.ID " +
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
                "FROM tbl_emp_suggestion ES " +
                "LEFT JOIN tbl_app_user AU ON ES.USER_ID = AU.ID " +
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
    public Map<String, Object> ifAnyEmpSuggestionExist(String userId, int assessmentYear) {
        String sql = "SELECT SUGGESTION FROM tbl_emp_suggestion WHERE USER_ID = ? AND ASSESSMENT_YEAR = ?";
        log.info("Looking for EmpSuggestion with User ID: {} and Assessment Year: {} with query: {}", userId, assessmentYear, sql);
        try {
            Map<String, Object> result = jdbcTemplate.queryForMap(sql, userId, assessmentYear);

            log.info("There is an EmpSuggestion with UserID: {} and Assessment Year: {}", userId, assessmentYear);
            log.info(result.toString());
            return result;
        } catch (EmptyResultDataAccessException e){
            log.info("There is no EmpSuggestion with UserID: {} and Assessment Year: {}", userId, assessmentYear);
            return null;
        }
        catch (Exception e) {
            log.error("Error while looking EmpSuggestion by UserID: {} and Assessment Year: {}. Error: {}", userId, assessmentYear, e.getMessage());
            throw e;
        }
    }

    @Override
    public Long countEmpSuggestions() {
        String sql = "SELECT COUNT(ID) FROM tbl_assessment_summary;";
        try {
            log.info("Fetching total number of EmpSuggestions");
            Long total = jdbcTemplate.queryForObject(sql, Long.class);
            log.info("Total EmpSuggestions: {}", total);
            return total;
        } catch (Exception e) {
            log.error("Error fetching EmpSuggestions count: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> sortEmpSuggestionGetsOrderBy(String column, String order, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT ES.ID, ES.USER_ID, AU.FULL_NAME, ES.SUGGESTION, ES.ASSESSMENT_YEAR " +
                "FROM tbl_emp_suggestion ES " +
                "LEFT JOIN tbl_app_user AU ON ES.USER_ID = AU.ID ORDER BY " + column + " " + order + " LIMIT ? OFFSET ?";
        log.info("Executing query to sort EmpSuggestions order by {} {} for page {} with maximum {} entries : {}", column, order, page, pageSize, sql);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, pageSize, offset);
            log.info("Successfully sorted EmpSuggestions order by {} {} for Page {} ({} entries)", column, order, page, result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching EmpSuggestions: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> searchEmpSuggestionGets(String keyword, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT ES.ID, ES.USER_ID, AU.FULL_NAME, ES.SUGGESTION, ES.ASSESSMENT_YEAR " +
                "FROM tbl_emp_suggestion ES " +
                "LEFT JOIN tbl_app_user AU ON ES.USER_ID = AU.ID WHERE LOWER(FULL_NAME) LIKE LOWER('%' || COALESCE(?, '') || '%') OR LOWER(ASSESSMENT_YEAR) LIKE LOWER('%' || COALESCE(?, '') || '%') ORDER BY FULL_NAME LIMIT ? OFFSET ?";
        log.info("Executing query to search EmpSuggestions using keyword: {} for page {} with maximum {} entries : {}", keyword, page, pageSize, sql);
        try {
            List<Map<String, Object>> appUsers = jdbcTemplate.queryForList(sql, keyword, keyword, keyword, keyword, keyword, pageSize, offset);
            log.info("Successfully searched EmpSuggestions using keyword: {} for Page {} ({} entries)", keyword, page, appUsers.size());
            return appUsers;
        } catch (Exception e) {
            log.error("Error searching EmpSuggestions: {}", e.getMessage(), e);
            throw e;
        }
    }
}

