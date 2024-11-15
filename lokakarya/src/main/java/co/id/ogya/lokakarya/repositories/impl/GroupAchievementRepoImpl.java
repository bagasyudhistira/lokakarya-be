package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.GroupAchievement;
import co.id.ogya.lokakarya.repositories.GroupAchievementRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class GroupAchievementRepoImpl implements GroupAchievementRepo {

    private final RowMapper<GroupAchievement> rowMapper = new BeanPropertyRowMapper<>(GroupAchievement.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<GroupAchievement> getGroupAchievements() {
        String sql = "SELECT * FROM TBL_GROUP_ACHIEVEMENT";
        log.info("Executing query to fetch all GroupAchievements: {}", sql);
        try {
            List<GroupAchievement> result = jdbcTemplate.query(sql, rowMapper);
            log.info("Successfully fetched {} GroupAchievements", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error while fetching all GroupAchievements. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public GroupAchievement getGroupAchievementById(String id) {
        String sql = "SELECT * FROM TBL_GROUP_ACHIEVEMENT WHERE ID = ?";
        log.info("Executing query to fetch GroupAchievement by ID: {}. Query: {}", id, sql);
        try {
            GroupAchievement result = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Successfully fetched GroupAchievement: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error while fetching GroupAchievement with ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public GroupAchievement saveGroupAchievement(GroupAchievement groupAchievement) {
        String sql = "INSERT INTO tbl_group_achievement(id, group_name, percentage, enabled, created_by) VALUES(?,?,?,?,?)";
        log.info("Executing query to save GroupAchievement: {}. Query: {}", groupAchievement, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, groupAchievement.getId(), groupAchievement.getGroupName(), groupAchievement.getPercentage(), groupAchievement.isEnabled(), groupAchievement.getCreatedBy());
            if (rowsAffected > 0) {
                log.info("Successfully saved GroupAchievement: {}", groupAchievement);
                return groupAchievement;
            } else {
                log.warn("No rows affected while saving GroupAchievement: {}", groupAchievement);
                return null;
            }
        } catch (Exception e) {
            log.error("Error while saving GroupAchievement: {}. Error: {}", groupAchievement, e.getMessage());
            return null;
        }
    }

    @Override
    public GroupAchievement updateGroupAchievement(GroupAchievement groupAchievement) {
        String sql = "UPDATE TBL_GROUP_ACHIEVEMENT SET GROUP_NAME = ?, PERCENTAGE = ?, ENABLED = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        log.info("Executing query to update GroupAchievement with ID: {}. Query: {}", groupAchievement.getId(), sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, groupAchievement.getGroupName(), groupAchievement.getPercentage(), groupAchievement.isEnabled(), groupAchievement.getUpdatedBy(), groupAchievement.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated GroupAchievement: {}", groupAchievement);
                return groupAchievement;
            } else {
                log.warn("No rows affected while updating GroupAchievement with ID: {}", groupAchievement.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error while updating GroupAchievement with ID: {}. Error: {}", groupAchievement.getId(), e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteGroupAchievement(String id) {
        String sql = "DELETE FROM TBL_GROUP_ACHIEVEMENT WHERE ID = ?";
        log.info("Executing query to delete GroupAchievement with ID: {}. Query: {}", id, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted GroupAchievement with ID: {}", id);
                return true;
            } else {
                log.warn("No rows affected while deleting GroupAchievement with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error while deleting GroupAchievement with ID: {}. Error: {}", id, e.getMessage());
            return false;
        }
    }
}
