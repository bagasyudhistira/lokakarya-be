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
import java.util.Map;

@Slf4j
@Repository
public class EmpTechnicalSkillRepoImpl implements EmpTechnicalSkillRepo {

    private final RowMapper<EmpTechnicalSkill> rowMapper = new BeanPropertyRowMapper<>(EmpTechnicalSkill.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EmpTechnicalSkill> getEmpTechnicalSkills() {
        String sql = "SELECT * FROM TBL_EMP_TECHNICAL_SKILL";
        log.info("Fetching all EmpTechnicalSkills with query: {}", sql);
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
        log.info("Fetching EmpTechnicalSkill by ID: {} with query: {}", id, sql);
        try {
            EmpTechnicalSkill result = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Successfully fetched EmpTechnicalSkill: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error while fetching EmpTechnicalSkill by ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public EmpTechnicalSkill saveEmpTechnicalSkill(EmpTechnicalSkill empTechnicalSkill) {
        empTechnicalSkill.prePersist();
        String sql = "INSERT INTO TBL_EMP_TECHNICAL_SKILL (ID, USER_ID, TECHNICAL_SKILL_ID, SCORE, ASSESSMENT_YEAR, CREATED_BY) VALUES (?, ?, ?, ?, ?, ?)";
        log.info("Saving EmpTechnicalSkill: {} with query: {}", empTechnicalSkill, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, empTechnicalSkill.getId(), empTechnicalSkill.getUserId(),
                    empTechnicalSkill.getTechnicalSkillId(), empTechnicalSkill.getScore(),
                    empTechnicalSkill.getAssessmentYear(), empTechnicalSkill.getCreatedBy());
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
        log.info("Updating EmpTechnicalSkill with ID: {} using query: {}", empTechnicalSkill.getId(), sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, empTechnicalSkill.getUserId(),
                    empTechnicalSkill.getTechnicalSkillId(), empTechnicalSkill.getScore(),
                    empTechnicalSkill.getAssessmentYear(), empTechnicalSkill.getUpdatedBy(), empTechnicalSkill.getId());
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
        log.info("Deleting EmpTechnicalSkill with ID: {} using query: {}", id, sql);
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

    @Override
    public List<Map<String, Object>> getEmpTechnicalSkillGets() {
        String sql = "SELECT ETS.ID, AU.FULL_NAME, TS.TECHNICAL_SKILL, ETS.SCORE, ETS.ASSESSMENT_YEAR " +
                "FROM TBL_EMP_TECHNICAL_SKILL ETS " +
                "LEFT JOIN TBL_APP_USER AU ON ETS.USER_ID = AU.ID " +
                "LEFT JOIN TBL_TECHNICAL_SKILL TS ON ETS.TECHNICAL_SKILL_ID = TS.ID";
        log.info("Fetching all EmpTechnicalSkills with LEFT JOIN query: {}", sql);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
            log.info("Successfully fetched {} EmpTechnicalSkills", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error while fetching all EmpTechnicalSkills. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> getEmpTechnicalSkillGetByUserId(String userId) {
        String sql = "SELECT ETS.ID, ETS.USER_ID, AU.FULL_NAME, TS.TECHNICAL_SKILL, ETS.SCORE, ETS.ASSESSMENT_YEAR " +
                "FROM TBL_EMP_TECHNICAL_SKILL ETS " +
                "LEFT JOIN TBL_APP_USER AU ON ETS.USER_ID = AU.ID " +
                "LEFT JOIN TBL_TECHNICAL_SKILL TS ON ETS.TECHNICAL_SKILL_ID = TS.ID " +
                "WHERE ETS.USER_ID = ?";
        log.info("Fetching EmpTechnicalSkills by UserID: {} with LEFT JOIN query: {}", userId, sql);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, userId);
            log.info("Successfully fetched {} EmpTechnicalSkills for UserID: {}", result.size(), userId);
            return result;
        } catch (Exception e) {
            log.error("Error while fetching EmpTechnicalSkills for UserID: {}. Error: {}", userId, e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean ifAnyEmpTechnicalSkillExist(String userId, String technicalSkillId, int assessmentYear) {
        String sql = "SELECT ID FROM TBL_EMP_TECHNICAL_SKILL WHERE USER_ID = ? AND TECHNICAL_SKILL_ID = ? AND ASSESSMENT_YEAR = ?";
        log.info("Looking for EmpTechnicalSkill with User ID: {}, Technical Skill ID: {}, and Assessment Year: {} with query: {}", userId, technicalSkillId, assessmentYear, sql);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, userId, technicalSkillId, assessmentYear);
            if (result.isEmpty()) {
                log.info("There is no EmpTechnicalSkill with User ID: {}, Technical Skill ID: {}, and Assessment Year: {}", userId, technicalSkillId, assessmentYear);
                return false;
            } else {
                log.info("There is an EmpTechnicalSkill with User ID: {}, Technical Skill ID: {}, and Assessment Year: {}", userId, technicalSkillId, assessmentYear);
                return true;
            }
        } catch (Exception e) {
            log.error("Error while looking EmpTechnicalSkill by User ID: {}, Technical Skill ID: {}, and Assessment Year: {}. Error: {}", userId, technicalSkillId, assessmentYear, e.getMessage());
            throw e;
        }
    }
}
