package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.EmpTechnicalSkill;
import co.id.ogya.lokakarya.repositories.EmpTechnicalSkillRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class EmpTechnicalSkillRepoImpl implements EmpTechnicalSkillRepo {

    private final RowMapper<EmpTechnicalSkill> rowMapper = new BeanPropertyRowMapper<>(EmpTechnicalSkill.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EmpTechnicalSkill> getEmpTechnicalSkills() {
        String sql = "SELECT * FROM TBL_EMP_TECHNICAL_SKILL";
        log.info("Executing query to fetch all EmpTechnicalSkills: {}", sql);
        try {
            List<EmpTechnicalSkill> result = jdbcTemplate.query(sql, rowMapper);
            log.info("Successfully fetched {} EmpTechnicalSkills", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error while fetching all EmpTechnicalSkills. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public EmpTechnicalSkill getEmpTechnicalSkillById(String id) {
        String sql = "SELECT * FROM TBL_EMP_TECHNICAL_SKILL WHERE ID = ?";
        log.info("Executing query to fetch EmpTechnicalSkill by ID: {}. Query: {}", id, sql);
        try {
            EmpTechnicalSkill result = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Successfully fetched EmpTechnicalSkill: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error while fetching EmpTechnicalSkill with ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public EmpTechnicalSkill saveEmpTechnicalSkill(EmpTechnicalSkill empTechnicalSkill) {
        String sql = "INSERT INTO TBL_EMP_TECHNICAL_SKILL (ID, USER_ID, TECHNICAL_SKILL_ID, SCORE, ASSESSMENT_YEAR, CREATED_BY) VALUES (?, ?, ?, ?, ?, ?)";
        log.info("Executing query to save EmpTechnicalSkill: {}. Query: {}", empTechnicalSkill, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, empTechnicalSkill.getId(), empTechnicalSkill.getUserId(), empTechnicalSkill.getTechnicalSkillId(), empTechnicalSkill.getScore(), empTechnicalSkill.getAssessmentYear(), empTechnicalSkill.getCreatedBy());
            if (rowsAffected > 0) {
                log.info("Successfully saved EmpTechnicalSkill: {}", empTechnicalSkill);
                return empTechnicalSkill;
            } else {
                log.warn("No rows affected while saving EmpTechnicalSkill: {}", empTechnicalSkill);
                return null;
            }
        } catch (Exception e) {
            log.error("Error while saving EmpTechnicalSkill: {}. Error: {}", empTechnicalSkill, e.getMessage());
            return null;
        }
    }

    @Override
    public EmpTechnicalSkill updateEmpTechnicalSkill(EmpTechnicalSkill empTechnicalSkill) {
        String sql = "UPDATE TBL_EMP_TECHNICAL_SKILL SET USER_ID = ?, TECHNICAL_SKILL_ID = ?, SCORE = ?, ASSESSMENT_YEAR = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        log.info("Executing query to update EmpTechnicalSkill with ID: {}. Query: {}", empTechnicalSkill.getId(), sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, empTechnicalSkill.getUserId(), empTechnicalSkill.getTechnicalSkillId(), empTechnicalSkill.getScore(), empTechnicalSkill.getAssessmentYear(), empTechnicalSkill.getUpdatedBy(), empTechnicalSkill.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated EmpTechnicalSkill: {}", empTechnicalSkill);
                return empTechnicalSkill;
            } else {
                log.warn("No rows affected while updating EmpTechnicalSkill with ID: {}", empTechnicalSkill.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error while updating EmpTechnicalSkill with ID: {}. Error: {}", empTechnicalSkill.getId(), e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteEmpTechnicalSkill(String id) {
        String sql = "DELETE FROM TBL_EMP_TECHNICAL_SKILL WHERE ID = ?";
        log.info("Executing query to delete EmpTechnicalSkill with ID: {}. Query: {}", id, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted EmpTechnicalSkill with ID: {}", id);
                return true;
            } else {
                log.warn("No rows affected while deleting EmpTechnicalSkill with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error while deleting EmpTechnicalSkill with ID: {}. Error: {}", id, e.getMessage());
            return false;
        }
    }
}
