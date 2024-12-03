package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.EmpAttitudeSkill;
import co.id.ogya.lokakarya.repositories.EmpAttitudeSkillRepo;
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
public class EmpAttitudeSkillRepoImpl implements EmpAttitudeSkillRepo {

    private final RowMapper<EmpAttitudeSkill> rowMapper = new BeanPropertyRowMapper<>(EmpAttitudeSkill.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EmpAttitudeSkill> getEmpAttitudeSkills() {
        String sql = "SELECT * FROM TBL_EMP_ATTITUDE_SKILL";
        log.info("Fetching all EmpAttitudeSkills with query: {}", sql);
        try {
            List<EmpAttitudeSkill> result = jdbcTemplate.query(sql, rowMapper);
            log.info("Successfully fetched {} EmpAttitudeSkills", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching EmpAttitudeSkills. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public EmpAttitudeSkill getEmpAttitudeSkillById(String id) {
        String sql = "SELECT * FROM TBL_EMP_ATTITUDE_SKILL WHERE ID = ?";
        log.info("Fetching EmpAttitudeSkill by ID: {} with query: {}", id, sql);
        try {
            EmpAttitudeSkill result = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Successfully fetched EmpAttitudeSkill: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error fetching EmpAttitudeSkill by ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getEmpAttitudeSkillGets() {
        String sql = "SELECT eas.ID, eas.USER_ID, au.FULL_NAME, ats.ATTITUDE_SKILL, eas.SCORE, eas.ASSESSMENT_YEAR " +
                "FROM TBL_EMP_ATTITUDE_SKILL eas " +
                "JOIN TBL_ATTITUDE_SKILL ats ON eas.ATTITUDE_SKILL_ID = ats.ID " +
                "JOIN TBL_APP_USER au ON eas.USER_ID = au.ID";
        log.info("Fetching all EmpAttitudeSkills with JOIN query: {}", sql);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
            log.info("Successfully fetched {} EmpAttitudeSkills", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching EmpAttitudeSkills. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Map<String, Object> getEmpAttitudeSkillGetById(String id) {
        String sql = "SELECT eas.ID, eas.USER_ID, FULL_NAME, ATTITUDE_SKILL, SCORE, ASSESSMENT_YEAR " +
                "FROM TBL_EMP_ATTITUDE_SKILL eas " +
                "JOIN TBL_ATTITUDE_SKILL ats ON eas.ATTITUDE_SKILL_ID = ats.ID " +
                "JOIN TBL_APP_USER au ON eas.USER_ID = au.ID " +
                "WHERE eas.ID = ?";
        log.info("Fetching EmpAttitudeSkill by ID: {} with JOIN query: {}", id, sql);
        try {
            Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
            log.info("Successfully fetched EmpAttitudeSkill: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error fetching EmpAttitudeSkill by ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getEmpAttitudeSkillGetsByUserId(String userId) {
        String sql = "SELECT eas.ID, FULL_NAME, ATTITUDE_SKILL, SCORE, eas.ASSESSMENT_YEAR " +
                "FROM TBL_EMP_ATTITUDE_SKILL eas " +
                "LEFT JOIN TBL_ATTITUDE_SKILL ats ON eas.ATTITUDE_SKILL_ID = ats.ID " +
                "LEFT JOIN TBL_APP_USER au ON eas.USER_ID = au.ID " +
                "WHERE eas.ID = ?";
        log.info("Fetching EmpAttitudeSkill by User ID: {} with JOIN query: {}", userId, sql);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, userId);
            log.info("Successfully fetched EmpAttitudeSkill: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error fetching EmpAttitudeSkill by User ID: {}. Error: {}", userId, e.getMessage());
            return null;
        }
    }

    @Override
    public EmpAttitudeSkill saveEmpAttitudeSkill(EmpAttitudeSkill empAttitudeSkill) {
        empAttitudeSkill.prePersist();
        String sql = "INSERT INTO TBL_EMP_ATTITUDE_SKILL (ID, USER_ID, ATTITUDE_SKILL_ID, SCORE, ASSESSMENT_YEAR, CREATED_BY) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        log.info("Saving EmpAttitudeSkill: {} with query: {}", empAttitudeSkill, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, empAttitudeSkill.getId(), empAttitudeSkill.getUserId(),
                    empAttitudeSkill.getAttitudeSkillId(), empAttitudeSkill.getScore(),
                    empAttitudeSkill.getAssessmentYear(), empAttitudeSkill.getCreatedBy());
            if (rowsAffected > 0) {
                log.info("Successfully saved EmpAttitudeSkill: {}", empAttitudeSkill);
                return empAttitudeSkill;
            } else {
                log.warn("No rows affected while saving EmpAttitudeSkill: {}", empAttitudeSkill);
                return null;
            }
        } catch (Exception e) {
            log.error("Error saving EmpAttitudeSkill: {}. Error: {}", empAttitudeSkill, e.getMessage());
            return null;
        }
    }

    @Override
    public EmpAttitudeSkill updateEmpAttitudeSkill(EmpAttitudeSkill empAttitudeSkill) {
        String sql = "UPDATE TBL_EMP_ATTITUDE_SKILL SET USER_ID = ?, ATTITUDE_SKILL_ID = ?, SCORE = ?, ASSESSMENT_YEAR = ?, " +
                "UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        log.info("Updating EmpAttitudeSkill with ID: {} using query: {}", empAttitudeSkill.getId(), sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, empAttitudeSkill.getUserId(), empAttitudeSkill.getAttitudeSkillId(),
                    empAttitudeSkill.getScore(), empAttitudeSkill.getAssessmentYear(), empAttitudeSkill.getUpdatedBy(),
                    empAttitudeSkill.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated EmpAttitudeSkill: {}", empAttitudeSkill);
                return empAttitudeSkill;
            } else {
                log.warn("No rows affected while updating EmpAttitudeSkill with ID: {}", empAttitudeSkill.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error updating EmpAttitudeSkill with ID: {}. Error: {}", empAttitudeSkill.getId(), e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteEmpAttitudeSkill(String id) {
        String sql = "DELETE FROM TBL_EMP_ATTITUDE_SKILL WHERE ID = ?";
        log.info("Deleting EmpAttitudeSkill with ID: {} using query: {}", id, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted EmpAttitudeSkill with ID: {}", id);
                return true;
            } else {
                log.warn("No rows affected while deleting EmpAttitudeSkill with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error deleting EmpAttitudeSkill with ID: {}. Error: {}", id, e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean ifAnyEmpAttitudeSkillExist(String userId, String attitudeSkillId, int assessmentYear) {
        String sql = "SELECT ID FROM TBL_EMP_ATTITUDE_SKILL WHERE USER_ID = ? AND ATTITUDE_SKILL_ID = ? AND ASSESSMENT_YEAR = ?";
        log.info("Looking for EmpAttitudeSkill with User ID: {}, Attitude Skill ID: {}, and Assessment Year: {} with query: {}", userId, attitudeSkillId, assessmentYear, sql);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, userId, attitudeSkillId, assessmentYear);
            if (result.isEmpty()) {
                log.info("There is no EmpAttitudeSkill with User ID: {}, Attitude Skill ID: {}, and Assessment Year: {}", userId, attitudeSkillId, assessmentYear);
                return false;
            } else {
                log.info("There is an EmpAttitudeSkill with UserID: {}, Attitude Skill ID: {}, and Assessment Year: {}", userId, attitudeSkillId, assessmentYear);
                return true;
            }
        } catch (Exception e) {
            log.error("Error while looking EmpAttitudeSkill by User ID: {}, Attitude Skill ID: {}, and Assessment Year: {}. Error: {}", userId, attitudeSkillId, assessmentYear, e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> getEmpAttitudeSkillGetsByUserIdAssessmentYear(String userId, int assessmentYear) {
        String sql = "SELECT EAS.ID, EAS.ATTITUDE_SKILL_ID, EAS.SCORE, ASK.GROUP_ID FROM TBL_EMP_ATTITUDE_SKILL EAS JOIN TBL_ATTITUDE_SKILL ASK ON EAS.ATTITUDE_SKILL_ID = ASK.ID WHERE EAS.USER_ID = ? AND EAS.ASSESSMENT_YEAR = ?";
        log.info("Fetching all EmpAttitudeSkills for User ID: {} and Assessment Year: {} with JOIN query: {}", userId, assessmentYear,sql);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, userId, assessmentYear);
            log.info("Successfully fetched {} EmpAttitudeSkills for User ID: {} and Assessment Year: {}", result.size(), userId, assessmentYear);
            return result;
        } catch (Exception e) {
            log.error("Error fetching EmpAttitudeSkills for User ID: {} and Assessment Year: {}. Error: {}", userId, assessmentYear, e.getMessage());
            throw e;
        }
    }
}
