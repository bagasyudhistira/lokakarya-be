package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.GroupAchievement;
import co.id.ogya.lokakarya.entities.GroupAttitudeSkill;
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
        String sql = "SELECT * FROM tbl_group_achievement";
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
    public List<GroupAchievement> getGroupAchievementsPerPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM tbl_group_achievement ORDER BY group_name ASC LIMIT ? OFFSET ?";
        log.info("Executing query to fetch all GroupAchievements for page {} with maximum {} entries : {}", page, pageSize, sql);
        try {
            List<GroupAchievement> result = jdbcTemplate.query(sql, rowMapper, pageSize, offset);
            log.info("Successfully fetched GroupAchievements for Page {} ({} entries)", page, result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching GroupAchievements. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public GroupAchievement getGroupAchievementById(String id) {
        String sql = "SELECT * FROM tbl_group_achievement " +
                "WHERE ID = ?";
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
        groupAchievement.prePersist();
        String sql = "INSERT INTO tbl_group_achievement(ID, GROUP_NAME, PERCENTAGE, ENABLED, CREATED_BY) VALUES(?,?,?,?,?)";
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
        String sql = "UPDATE tbl_group_achievement SET GROUP_NAME = ?, PERCENTAGE = ?, ENABLED = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? " +
                "WHERE ID = ?";
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
        String sql = "DELETE FROM tbl_group_achievement " +
                "WHERE ID = ?";
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

    @Override
    public GroupAchievement getGroupAchievementByGroupName(String groupName) {
        String sql = "SELECT * FROM tbl_group_achievement WHERE LOWER(GROUP_NAME) = LOWER(?)";
        log.info("Executing query to fetch GroupAchievement by Group Name: {}. Query: {}", groupName, sql);
        try {
            GroupAchievement result = jdbcTemplate.queryForObject(sql, rowMapper, groupName);
            log.info("Successfully fetched GroupAchievement: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error while fetching GroupAchievement with Group Name: {}. Error: {}", groupName, e.getMessage());
            return null;
        }
    }

    @Override
    public Long countGroupAchievements() {
        String sql = "SELECT COUNT(ID) FROM tbl_group_achievement;";
        try {
            log.info("Fetching total number of GroupAchievements");
            Long total = jdbcTemplate.queryForObject(sql, Long.class);
            log.info("Total GroupAchievements: {}", total);
            return total;
        } catch (Exception e) {
            log.error("Error fetching GroupAchievements count: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<GroupAchievement> sortGroupAchievements(String order, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM tbl_group_achievement ORDER BY GROUP_NAME " + order + " LIMIT ? OFFSET ?";
        log.info("Executing query to sort GroupAttitudeSkills order {} for page {} with maximum {} entries : {}", order, page, pageSize, sql);
        try {
            List<GroupAchievement> result = jdbcTemplate.query(sql, rowMapper, pageSize, offset);
            log.info("Successfully sorted GroupAchievements order {} for Page {} ({} entries)", order, page, result.size());
            return result;
        } catch (Exception e) {
            log.error("Error sorting GroupAchievements. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<GroupAchievement> sorchGroupAchievements(String keyword, String column, String order, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM tbl_group_achievement WHERE LOWER(GROUP_NAME) LIKE LOWER(CONCAT('%', COALESCE(?, ''), '%')) ORDER BY " + column + " " + order + " LIMIT ? OFFSET ?";
        log.info("Executing query to sorch GroupAchievements using keyword: {} for page {} with maximum {} entries : {}", keyword, page, pageSize, sql);
        try {
            List<GroupAchievement> result = jdbcTemplate.query(sql, rowMapper, keyword, pageSize, offset);
            log.info("Successfully sorch GroupAchievements using keyword: {} for Page {} ({} entries)", keyword, page, result.size());
            return result;
        } catch (Exception e) {
            log.error("Error sorching GroupAchievements. Error: {}", e.getMessage());
            throw e;
        }
    }
}
