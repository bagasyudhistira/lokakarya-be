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
        log.info("Executing query to fetch employee achievement skill by ID: {} using query: {}", id, sql);
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
    public EmpAchievementSkill saveEmpAchievementSkill(EmpAchievementSkill empAchievementSkill) {
        String sql = "INSERT INTO TBL_EMP_ACHIEVEMENT_SKILL (ID, USER_ID, NOTES, ACHIEVEMENT_ID, SCORE, ASSESSMENT_YEAR, CREATED_BY) VALUES (?, ?, ?, ?, ?, ?, ?)";
        log.info("Executing query to save employee achievement skill: {} using query: {}", empAchievementSkill, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql,
                    empAchievementSkill.getId(),
                    empAchievementSkill.getUserId(),
                    empAchievementSkill.getNotes(),
                    empAchievementSkill.getAchievementId(),
                    empAchievementSkill.getScore(),
                    empAchievementSkill.getAssessmentYear(),
                    empAchievementSkill.getCreatedBy());
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
        String sql = "UPDATE TBL_EMP_ACHIEVEMENT_SKILL SET USER_ID = ?, NOTES = ?, ACHIEVEMENT_ID = ?, SCORE = ?, ASSESSMENT_YEAR = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        log.info("Executing query to update employee achievement skill with ID: {} using query: {}", empAchievementSkill.getId(), sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql,
                    empAchievementSkill.getUserId(),
                    empAchievementSkill.getNotes(),
                    empAchievementSkill.getAchievementId(),
                    empAchievementSkill.getScore(),
                    empAchievementSkill.getAssessmentYear(),
                    empAchievementSkill.getUpdatedBy(),
                    empAchievementSkill.getId());
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
        log.info("Executing query to delete employee achievement skill with ID: {} using query: {}", id, sql);
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
