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
        String sql = "SELECT * FROM TBL_EMP_ACHIEVEMENT_SKILL";
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
        String sql = "SELECT * FROM TBL_EMP_ACHIEVEMENT_SKILL WHERE ID = ?";
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
                "FROM TBL_EMP_ACHIEVEMENT_SKILL eas " +
                "LEFT JOIN TBL_APP_USER au ON eas.USER_ID = au.ID " +
                "LEFT JOIN TBL_ACHIEVEMENT a ON eas.ACHIEVEMENT_ID = a.ID";
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
                "FROM TBL_EMP_ACHIEVEMENT_SKILL eas " +
                "LEFT JOIN TBL_APP_USER au ON eas.USER_ID = au.ID " +
                "LEFT JOIN TBL_ACHIEVEMENT a ON eas.ACHIEVEMENT_ID = a.ID " +
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
        String sql = "SELECT EAS.ID, AU.FULL_NAME, A.ACHIEVEMENT, EAS.ASSESSMENT_YEAR FROM TBL_EMP_ACHIEVEMENT_SKILL EAS JOIN TBL_APP_USER AU ON EAS.USER_ID = AU.ID JOIN TBL_ACHIEVEMENT A ON EAS.ACHIEVEMENT_ID = A.ID WHERE EAS.USER_ID = ?";
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
        String sql = "INSERT INTO TBL_EMP_ACHIEVEMENT_SKILL (ID, USER_ID, NOTES, ACHIEVEMENT_ID, SCORE, ASSESSMENT_YEAR, CREATED_BY) " +
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
        String sql = "UPDATE TBL_EMP_ACHIEVEMENT_SKILL SET USER_ID = ?, NOTES = ?, ACHIEVEMENT_ID = ?, SCORE = ?, " +
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
        String sql = "DELETE FROM TBL_EMP_ACHIEVEMENT_SKILL WHERE ID = ?";
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
}
