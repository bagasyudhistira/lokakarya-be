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
import java.util.Map;

@Slf4j
@Repository
public class AssessmentSummaryRepoImpl implements AssessmentSummaryRepo {

    private final RowMapper<AssessmentSummary> rowMapper = new BeanPropertyRowMapper<>(AssessmentSummary.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AssessmentSummary> getAssessmentSummarys() {
        String sql = "SELECT * FROM tbl_assessment_summary";
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
        String sql = "SELECT * FROM tbl_assessment_summary WHERE ID = ?";
        log.info("Executing query to fetch AssessmentSummary by ID: {}", id);
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
    public List<Map<String, Object>> getAssessmentSummaryGets() {
        String sql = "SELECT au.ID AS USER_ID, FULL_NAME, YEAR, SCORE, STATUS, " +
                "ass.CREATED_AT, ass.CREATED_BY, ass.UPDATED_AT, ass.UPDATED_BY " +
                "FROM tbl_assessment_summary ass " +
                "LEFT JOIN tbl_app_user au ON ass.USER_ID = au.ID";
        log.info("Executing query to fetch all AssessmentSummary records with user details: {}", sql);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
            log.info("Successfully fetched {} AssessmentSummary records with user details.", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching all AssessmentSummary records with user details. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Map<String, Object> getAssessmentSummaryGetById(String id) {
        String sql = "SELECT ass.ID, FULL_NAME, YEAR, SCORE, STATUS, " +
                "ass.CREATED_AT, ass.CREATED_BY, ass.UPDATED_AT, ass.UPDATED_BY " +
                "FROM tbl_assessment_summary ass " +
                "LEFT JOIN tbl_app_user au ON ass.USER_ID = au.ID " +
                "WHERE ass.ID = ?";
        log.info("Executing query to fetch AssessmentSummary by ID with user details: {}", id);
        try {
            Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
            log.info("Successfully fetched AssessmentSummary: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error fetching AssessmentSummary by ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public Map<String, Object> getAssessmentSummaryGetByUserIdAndAssessmentYear(String userId, int year) {
        String sql = "SELECT ass.ID, FULL_NAME, YEAR, SCORE, STATUS, " +
                "ass.CREATED_AT, ass.CREATED_BY, ass.UPDATED_AT, ass.UPDATED_BY " +
                "FROM tbl_assessment_summary ass " +
                "LEFT JOIN tbl_app_user au ON ass.USER_ID = au.ID " +
                "WHERE ass.USER_ID = ? AND YEAR = ?";
        log.info("Executing query to fetch AssessmentSummary by ID with user details: {} year: {}", userId, year);
        try {
            Map<String, Object> result = jdbcTemplate.queryForMap(sql, userId, year);
            log.info("Successfully fetched AssessmentSummary: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error fetching AssessmentSummary by ID: {} year: {}. Error: {}", userId, year, e.getMessage());
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getAssessmentSummaryGetByUserId(String userId) {
        String sql = "SELECT ass.ID, FULL_NAME, YEAR, SCORE, STATUS, " +
                "ass.CREATED_AT, ass.CREATED_BY, ass.UPDATED_AT, ass.UPDATED_BY " +
                "FROM tbl_assessment_summary ass " +
                "LEFT JOIN tbl_app_user au ON ass.USER_ID = au.ID " +
                "WHERE au.ID = ?";
        log.info("Executing query to fetch AssessmentSummary records by User ID: {}", userId);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, userId);
            log.info("Successfully fetched {} AssessmentSummary records for User ID: {}", result.size(), userId);
            return result;
        } catch (Exception e) {
            log.error("Error fetching AssessmentSummary records for User ID: {}. Error: {}", userId, e.getMessage());
            throw e;
        }
    }

    @Override
    public AssessmentSummary saveAssessmentSummary(AssessmentSummary assessmentSummary) {
        assessmentSummary.prePersist();
        String sql = "INSERT INTO tbl_assessment_summary (ID, USER_ID, YEAR, SCORE, STATUS, CREATED_BY) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        log.info("Executing query to save AssessmentSummary: {}", assessmentSummary);
        try {
            int rowsAffected = jdbcTemplate.update(sql, assessmentSummary.getId(), assessmentSummary.getUserId(),
                    assessmentSummary.getYear(), assessmentSummary.getScore(), assessmentSummary.getStatus(),
                    assessmentSummary.getCreatedBy());
            if (rowsAffected > 0) {
                log.info("Successfully saved AssessmentSummary: {}", assessmentSummary);
                return assessmentSummary;
            } else {
                log.warn("Failed to save AssessmentSummary: {}", assessmentSummary);
                return null;
            }
        } catch (Exception e) {
            log.error("Error saving AssessmentSummary: {}. Error: {}", assessmentSummary, e.getMessage());
            return null;
        }
    }

    @Override
    public AssessmentSummary updateAssessmentSummary(AssessmentSummary assessmentSummary) {
        String sql = "UPDATE tbl_assessment_summary SET USER_ID = ?, YEAR = ?, SCORE = ?, STATUS = ?, " +
                "UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        log.info("Executing query to update AssessmentSummary with ID: {}", assessmentSummary.getId());
        try {
            int rowsAffected = jdbcTemplate.update(sql, assessmentSummary.getUserId(), assessmentSummary.getYear(),
                    assessmentSummary.getScore(), assessmentSummary.getStatus(), assessmentSummary.getUpdatedBy(),
                    assessmentSummary.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated AssessmentSummary: {}", assessmentSummary);
                return assessmentSummary;
            } else {
                log.warn("Failed to update AssessmentSummary with ID: {}", assessmentSummary.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error updating AssessmentSummary with ID: {}. Error: {}", assessmentSummary.getId(), e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteAssessmentSummary(String id) {
        String sql = "DELETE FROM tbl_assessment_summary WHERE ID = ?";
        log.info("Executing query to delete AssessmentSummary with ID: {}", id);
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted AssessmentSummary with ID: {}", id);
                return true;
            } else {
                log.warn("Failed to delete AssessmentSummary with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error deleting AssessmentSummary with ID: {}. Error: {}", id, e.getMessage());
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> getAchievementSummaryByUserIdAssessmentYear(String userId, int assessmentYear) {
        String sql = "SELECT GAC.GROUP_NAME, SUM(EAC.SCORE)/(50 * COUNT(EAC.ACHIEVEMENT_ID)) * 100 SUM_SCORE, GAC.PERCENTAGE FROM tbl_emp_achievement_skill EAC JOIN tbl_achievement ACH ON EAC.ACHIEVEMENT_ID = ACH.ID JOIN tbl_group_achievement GAC ON ACH.GROUP_ID = GAC.ID WHERE EAC.USER_ID = ? AND EAC.ASSESSMENT_YEAR = ? GROUP BY GAC.GROUP_NAME";
        log.info("Executing query to fetch AchievementSummary by User ID: {} and Assessment Year: {}", userId, assessmentYear);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, userId, assessmentYear);
            log.info("Successfully fetched {} AchievementSummary records for User ID: {} and Assessment Year: {}", result.size(), userId, assessmentYear);
            return result;
        } catch (Exception e) {
            log.error("Error fetching AchievementSummary records for User ID: {} and Assessment Year: {}. Error: {}", userId, assessmentYear, e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> getAttitudeSkillSummaryByUserIdAssessmentYear(String userId, int assessmentYear) {
        String sql = "SELECT GAT.GROUP_NAME, SUM(EAT.SCORE)/(50 * COUNT(EAT.ATTITUDE_SKILL_ID)) * 100 SUM_SCORE, GAT.PERCENTAGE FROM tbl_emp_attitude_skill EAT JOIN tbl_attitude_skill ATS ON EAT.ATTITUDE_SKILL_ID = ATS.ID JOIN tbl_group_attitude_skill GAT ON ATS.GROUP_ID = GAT.ID WHERE EAT.USER_ID = ? AND EAT.ASSESSMENT_YEAR = ? GROUP BY GAT.GROUP_NAME";
        log.info("Executing query to fetch AttitudeSkillSummary by User ID: {} and Assessment Year: {}", userId, assessmentYear);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, userId, assessmentYear);
            log.info("Successfully fetched {} AttitudeSkillSummary records for User ID: {} and Assessment Year: {}", result.size(), userId, assessmentYear);
            return result;
        } catch (Exception e) {
            log.error("Error fetching AttitudeSkillSummary records for User ID: {} and Assessment Year: {}. Error: {}", userId, assessmentYear, e.getMessage());
            throw e;
        }
    }
}
