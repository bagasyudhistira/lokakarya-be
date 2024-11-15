package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.AttitudeSkill;
import co.id.ogya.lokakarya.repositories.AttitudeSkillRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class AttitudeSkillRepoImpl implements AttitudeSkillRepo {

    private final RowMapper<AttitudeSkill> rowMapper = new BeanPropertyRowMapper<>(AttitudeSkill.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AttitudeSkill> getAttitudeSkills() {
        String sql = "SELECT * FROM TBL_ATTITUDE_SKILL";
        log.info("Executing query to fetch all AttitudeSkills: {}", sql);
        try {
            List<AttitudeSkill> result = jdbcTemplate.query(sql, rowMapper);
            log.info("Successfully fetched {} AttitudeSkills.", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching all AttitudeSkills. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public AttitudeSkill getAttitudeSkillById(String id) {
        String sql = "SELECT * FROM TBL_ATTITUDE_SKILL WHERE ID = ?";
        log.info("Executing query to fetch AttitudeSkill by ID: {} using query: {}", id, sql);
        try {
            AttitudeSkill result = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Successfully fetched AttitudeSkill: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error fetching AttitudeSkill by ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public AttitudeSkill saveAttitudeSkill(AttitudeSkill attitudeSkill) {
        attitudeSkill.prePersist();
        String sql = "INSERT INTO TBL_ATTITUDE_SKILL (ID, ATTITUDE_SKILL, GROUP_ID, ENABLED, CREATED_BY) VALUES (?, ?, ?, ?, ?)";
        log.info("Executing query to save AttitudeSkill: {} using query: {}", attitudeSkill, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, attitudeSkill.getId(), attitudeSkill.getAttitudeSkill(), attitudeSkill.getGroupId(), attitudeSkill.isEnabled(), attitudeSkill.getCreatedBy());
            if (rowsAffected > 0) {
                log.info("Successfully saved AttitudeSkill: {}", attitudeSkill);
                return attitudeSkill;
            } else {
                log.warn("No rows affected while saving AttitudeSkill: {}", attitudeSkill);
                return null;
            }
        } catch (Exception e) {
            log.error("Error saving AttitudeSkill: {}. Error: {}", attitudeSkill, e.getMessage());
            return null;
        }
    }

    @Override
    public AttitudeSkill updateAttitudeSkill(AttitudeSkill attitudeSkill) {
        String sql = "UPDATE TBL_ATTITUDE_SKILL SET ATTITUDE_SKILL = ?, GROUP_ID = ?, ENABLED = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        log.info("Executing query to update AttitudeSkill with ID: {} using query: {}", attitudeSkill.getId(), sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, attitudeSkill.getAttitudeSkill(), attitudeSkill.getGroupId(), attitudeSkill.isEnabled(), attitudeSkill.getUpdatedBy(), attitudeSkill.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated AttitudeSkill: {}", attitudeSkill);
                return attitudeSkill;
            } else {
                log.warn("No rows affected while updating AttitudeSkill with ID: {}", attitudeSkill.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error updating AttitudeSkill with ID: {}. Error: {}", attitudeSkill.getId(), e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteAttitudeSkill(String id) {
        String sql = "DELETE FROM TBL_ATTITUDE_SKILL WHERE ID = ?";
        log.info("Executing query to delete AttitudeSkill with ID: {} using query: {}", id, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted AttitudeSkill with ID: {}", id);
                return true;
            } else {
                log.warn("No rows affected while deleting AttitudeSkill with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error deleting AttitudeSkill with ID: {}. Error: {}", id, e.getMessage());
            return false;
        }
    }
}
