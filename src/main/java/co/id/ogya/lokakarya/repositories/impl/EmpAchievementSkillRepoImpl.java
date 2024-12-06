package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.EmpAchievementSkill;
import co.id.ogya.lokakarya.repositories.EmpAchievementSkillRepo;
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
public class EmpAchievementSkillRepoImpl implements EmpAchievementSkillRepo {

    private final RowMapper<EmpAchievementSkill> rowMapper = new BeanPropertyRowMapper<>(EmpAchievementSkill.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EmpAchievementSkill> getEmpAchievementSkills() {
        String sql = "SELECT * FROM tbl_emp_achievement_skill";
        log.info("Executing query to fetch all employee achievement skills: {}", sql);
        try {
            List<EmpAchievementSkill> result = jdbcTemplate.query(sql, rowMapper);
            log.info("Successfully fetched {} employee achievement skills.", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching employee achievement skills. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public EmpAchievementSkill getEmpAchievementSkillById(String id) {
        String sql = "SELECT * FROM tbl_emp_achievement_skill WHERE ID = ?";
        log.info("Executing query to fetch employee achievement skill by ID: {}", id);
        try {
            EmpAchievementSkill result = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Successfully fetched employee achievement skill: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error fetching employee achievement skill by ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getEmpAchievementSkillGets() {
        String sql = "SELECT eas.ID, FULL_NAME, NOTES, ACHIEVEMENT, SCORE, ASSESSMENT_YEAR " +
                "FROM tbl_emp_achievement_skill eas " +
                "LEFT JOIN tbl_app_user au ON eas.USER_ID = au.ID " +
                "LEFT JOIN tbl_achievement a ON eas.ACHIEVEMENT_ID = a.ID";
        log.info("Executing query to fetch all employee achievement skills with user and achievement details.");
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
            log.info("Successfully fetched {} employee achievement skills.", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching employee achievement skills. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Map<String, Object> getEmpAchievementSkillGetById(String id) {
        String sql = "SELECT eas.ID, FULL_NAME, NOTES, ACHIEVEMENT, SCORE, ASSESSMENT_YEAR " +
                "FROM tbl_emp_achievement_skill eas " +
                "LEFT JOIN tbl_app_user au ON eas.USER_ID = au.ID " +
                "LEFT JOIN tbl_achievement a ON eas.ACHIEVEMENT_ID = a.ID " +
                "WHERE eas.ID = ?";
        log.info("Executing query to fetch employee achievement skill by ID: {}", id);
        try {
            Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
            log.info("Successfully fetched employee achievement skill: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error fetching employee achievement skill by ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getEmpAchievementSkillGetsByUserId(String userId) {
        String sql = "SELECT EAS.ID, AU.FULL_NAME, A.ACHIEVEMENT, EAS.ASSESSMENT_YEAR FROM tbl_emp_achievement_skill EAS JOIN tbl_app_user AU ON EAS.USER_ID = AU.ID JOIN tbl_achievement A ON EAS.ACHIEVEMENT_ID = A.ID WHERE EAS.USER_ID = ?";
        log.info("Executing query to fetch employee achievement skill by User ID: {}", userId);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, userId);
            log.info("Successfully fetched employee achievement skill: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error fetching employee achievement skill by User ID: {}. Error: {}", userId, e.getMessage());
            return null;
        }
    }


    @Override
    public EmpAchievementSkill saveEmpAchievementSkill(EmpAchievementSkill empAchievementSkill) {
        empAchievementSkill.prePersist();
        String sql = "INSERT INTO tbl_emp_achievement_skill (ID, USER_ID, NOTES, ACHIEVEMENT_ID, SCORE, ASSESSMENT_YEAR, CREATED_BY) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        log.info("Executing query to save employee achievement skill: {}", empAchievementSkill);
        try {
            int rowsAffected = jdbcTemplate.update(sql, empAchievementSkill.getId(), empAchievementSkill.getUserId(),
                    empAchievementSkill.getNotes(), empAchievementSkill.getAchievementId(), empAchievementSkill.getScore(),
                    empAchievementSkill.getAssessmentYear(), empAchievementSkill.getCreatedBy());
            if (rowsAffected > 0) {
                log.info("Successfully saved employee achievement skill: {}", empAchievementSkill);
                return empAchievementSkill;
            } else {
                log.warn("No rows affected while saving employee achievement skill: {}", empAchievementSkill);
                return null;
            }
        } catch (Exception e) {
            log.error("Error saving employee achievement skill: {}. Error: {}", empAchievementSkill, e.getMessage());
            return null;
        }
    }

    @Override
    public EmpAchievementSkill updateEmpAchievementSkill(EmpAchievementSkill empAchievementSkill) {
        String sql = "UPDATE tbl_emp_achievement_skill SET USER_ID = ?, NOTES = ?, ACHIEVEMENT_ID = ?, SCORE = ?, " +
                "ASSESSMENT_YEAR = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        log.info("Executing query to update employee achievement skill with ID: {}", empAchievementSkill.getId());
        try {
            int rowsAffected = jdbcTemplate.update(sql, empAchievementSkill.getUserId(), empAchievementSkill.getNotes(),
                    empAchievementSkill.getAchievementId(), empAchievementSkill.getScore(), empAchievementSkill.getAssessmentYear(),
                    empAchievementSkill.getUpdatedBy(), empAchievementSkill.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated employee achievement skill: {}", empAchievementSkill);
                return empAchievementSkill;
            } else {
                log.warn("No rows affected while updating employee achievement skill with ID: {}", empAchievementSkill.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error updating employee achievement skill with ID: {}. Error: {}", empAchievementSkill.getId(), e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteEmpAchievementSkill(String id) {
        String sql = "DELETE FROM tbl_emp_achievement_skill WHERE ID = ?";
        log.info("Executing query to delete employee achievement skill with ID: {}", id);
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted employee achievement skill with ID: {}", id);
                return true;
            } else {
                log.warn("No rows affected while deleting employee achievement skill with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error deleting employee achievement skill with ID: {}. Error: {}", id, e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean ifAnyEmpAchievementSkillExist(String userId, String achievementId, int assessmentYear) {
        String sql = "SELECT ID FROM tbl_emp_achievement_skill WHERE USER_ID = ? AND ACHIEVEMENT_ID = ? AND ASSESSMENT_YEAR = ?";
        log.info("Looking for EmpAchievementSkill with User ID: {}, Achievement ID: {}, and Assessment Year: {} with query: {}", userId, achievementId, assessmentYear, sql);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, userId, achievementId, assessmentYear);
            if (result.isEmpty()) {
                log.info("There is no EmpAchievementSkill with User ID: {}, Achievement ID: {}, and Assessment Year: {}", userId, achievementId, assessmentYear);
                return false;
            } else {
                log.info("There is an EmpAchievementSkill with User ID: {}, Achievement ID: {}, and Assessment Year: {}", userId, achievementId, assessmentYear);
                return true;
            }
        } catch (Exception e) {
            log.error("Error while looking EmpAchievementSkill by User ID: {}, Achievement ID: {}, and Assessment Year: {}. Error: {}", userId, achievementId, assessmentYear, e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> getEmpAchievementSkillGetsByUserIdAssessmentYear(String userId, int assessmentYear) {
        String sql = "SELECT EAS.ID, EAS.ACHIEVEMENT_ID, EAS.NOTES, EAS.SCORE, ACH.GROUP_ID FROM tbl_emp_achievement_skill EAS JOIN tbl_achievement ACH ON EAS.ACHIEVEMENT_ID = ACH.ID WHERE EAS.USER_ID = ? AND EAS.ASSESSMENT_YEAR = ?";
        log.info("Fetching all EmpAchievementSkills for User ID: {} and Assessment Year: {} with JOIN query: {}", userId, assessmentYear,sql);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, userId, assessmentYear);
            log.info("Successfully fetched {} EmpAchievementSkills for User ID: {} and Assessment Year: {}", result.size(), userId, assessmentYear);
            return result;
        } catch (Exception e) {
            log.error("Error fetching EmpAchievementSkills for User ID: {} and Assessment Year: {}. Error: {}", userId, assessmentYear, e.getMessage());
            throw e;
        }
    }
}
