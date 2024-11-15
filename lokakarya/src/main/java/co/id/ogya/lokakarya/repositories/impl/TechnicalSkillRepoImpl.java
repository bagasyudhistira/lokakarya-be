package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.TechnicalSkill;
import co.id.ogya.lokakarya.repositories.TechnicalSkillRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class TechnicalSkillRepoImpl implements TechnicalSkillRepo {

    private final RowMapper<TechnicalSkill> rowMapper = new BeanPropertyRowMapper<>(TechnicalSkill.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TechnicalSkill> getTechnicalSkills() {
        String sql = "SELECT * FROM TBL_TECHNICAL_SKILL";
        log.info("Executing query to fetch all TechnicalSkills: {}", sql);
        try {
            List<TechnicalSkill> result = jdbcTemplate.query(sql, rowMapper);
            log.info("Successfully fetched {} TechnicalSkills.", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error while fetching all TechnicalSkills. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public TechnicalSkill getTechnicalSkillById(String id) {
        String sql = "SELECT * FROM TBL_TECHNICAL_SKILL WHERE ID = ?";
        log.info("Executing query to fetch TechnicalSkill by ID: {}. Query: {}", id, sql);
        try {
            TechnicalSkill result = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Successfully fetched TechnicalSkill: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error while fetching TechnicalSkill with ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public TechnicalSkill saveTechnicalSkill(TechnicalSkill technicalSkill) {
        String sql = "INSERT INTO TBL_TECHNICAL_SKILL (ID, TECHNICAL_SKILL, ENABLED, CREATED_BY) VALUES (?, ?, ?, ?)";
        log.info("Executing query to save TechnicalSkill: {}. Query: {}", technicalSkill, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, technicalSkill.getId(), technicalSkill.getTechnicalSkill(), technicalSkill.isEnabled(), technicalSkill.getCreatedBy());
            if (rowsAffected > 0) {
                log.info("Successfully saved TechnicalSkill: {}", technicalSkill);
                return technicalSkill;
            } else {
                log.warn("No rows affected while saving TechnicalSkill: {}", technicalSkill);
                return null;
            }
        } catch (Exception e) {
            log.error("Error while saving TechnicalSkill: {}. Error: {}", technicalSkill, e.getMessage());
            return null;
        }
    }

    @Override
    public TechnicalSkill updateTechnicalSkill(TechnicalSkill technicalSkill) {
        String sql = "UPDATE TBL_TECHNICAL_SKILL SET TECHNICAL_SKILL = ?, ENABLED = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        log.info("Executing query to update TechnicalSkill with ID: {}. Query: {}", technicalSkill.getId(), sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, technicalSkill.getTechnicalSkill(), technicalSkill.isEnabled(), technicalSkill.getUpdatedBy(), technicalSkill.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated TechnicalSkill: {}", technicalSkill);
                return technicalSkill;
            } else {
                log.warn("No rows affected while updating TechnicalSkill with ID: {}", technicalSkill.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error while updating TechnicalSkill with ID: {}. Error: {}", technicalSkill.getId(), e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteTechnicalSkill(String id) {
        String sql = "DELETE FROM TBL_TECHNICAL_SKILL WHERE ID = ?";
        log.info("Executing query to delete TechnicalSkill with ID: {}. Query: {}", id, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted TechnicalSkill with ID: {}", id);
                return true;
            } else {
                log.warn("No rows affected while deleting TechnicalSkill with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error while deleting TechnicalSkill with ID: {}. Error: {}", id, e.getMessage());
            return false;
        }
    }
}
