package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.GroupAttitudeSkill;
import co.id.ogya.lokakarya.repositories.GroupAttitudeSkillRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class GroupAttitudeSkillRepoImpl implements GroupAttitudeSkillRepo {

    private final RowMapper<GroupAttitudeSkill> rowMapper = new BeanPropertyRowMapper<>(GroupAttitudeSkill.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<GroupAttitudeSkill> getGroupAttitudeSkills() {
        String sql = "SELECT * FROM TBL_GROUP_ATTITUDE_SKILL";
        log.info("Executing query to fetch all GroupAttitudeSkills: {}", sql);
        try {
            List<GroupAttitudeSkill> result = jdbcTemplate.query(sql, rowMapper);
            log.info("Successfully fetched {} GroupAttitudeSkills", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error while fetching all GroupAttitudeSkills. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public GroupAttitudeSkill getGroupAttitudeSkillById(String id) {
        String sql = "SELECT * FROM TBL_GROUP_ATTITUDE_SKILL WHERE ID = ?";
        log.info("Executing query to fetch GroupAttitudeSkill by ID: {}. Query: {}", id, sql);
        try {
            GroupAttitudeSkill result = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Successfully fetched GroupAttitudeSkill: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error while fetching GroupAttitudeSkill with ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public GroupAttitudeSkill saveGroupAttitudeSkill(GroupAttitudeSkill groupAttitudeSkill) {
        String sql = "INSERT INTO TBL_GROUP_ATTITUDE_SKILL (ID, GROUP_NAME, PERCENTAGE, ENABLED, CREATED_BY) VALUES (?, ?, ?, ?, ?)";
        log.info("Executing query to save GroupAttitudeSkill: {}. Query: {}", groupAttitudeSkill, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, groupAttitudeSkill.getId(), groupAttitudeSkill.getGroupName(), groupAttitudeSkill.getPercentage(), groupAttitudeSkill.isEnabled(), groupAttitudeSkill.getCreatedBy());
            if (rowsAffected > 0) {
                log.info("Successfully saved GroupAttitudeSkill: {}", groupAttitudeSkill);
                return groupAttitudeSkill;
            } else {
                log.warn("No rows affected while saving GroupAttitudeSkill: {}", groupAttitudeSkill);
                return null;
            }
        } catch (Exception e) {
            log.error("Error while saving GroupAttitudeSkill: {}. Error: {}", groupAttitudeSkill, e.getMessage());
            return null;
        }
    }

    @Override
    public GroupAttitudeSkill updateGroupAttitudeSkill(GroupAttitudeSkill groupAttitudeSkill) {
        String sql = "UPDATE TBL_GROUP_ATTITUDE_SKILL SET GROUP_NAME = ?, PERCENTAGE = ?, ENABLED = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        log.info("Executing query to update GroupAttitudeSkill with ID: {}. Query: {}", groupAttitudeSkill.getId(), sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, groupAttitudeSkill.getGroupName(), groupAttitudeSkill.getPercentage(), groupAttitudeSkill.isEnabled(), groupAttitudeSkill.getUpdatedBy(), groupAttitudeSkill.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated GroupAttitudeSkill: {}", groupAttitudeSkill);
                return groupAttitudeSkill;
            } else {
                log.warn("No rows affected while updating GroupAttitudeSkill with ID: {}", groupAttitudeSkill.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error while updating GroupAttitudeSkill with ID: {}. Error: {}", groupAttitudeSkill.getId(), e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteGroupAttitudeSkill(String id) {
        String sql = "DELETE FROM TBL_GROUP_ATTITUDE_SKILL WHERE ID = ?";
        log.info("Executing query to delete GroupAttitudeSkill with ID: {}. Query: {}", id, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted GroupAttitudeSkill with ID: {}", id);
                return true;
            } else {
                log.warn("No rows affected while deleting GroupAttitudeSkill with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error while deleting GroupAttitudeSkill with ID: {}. Error: {}", id, e.getMessage());
            return false;
        }
    }
}
