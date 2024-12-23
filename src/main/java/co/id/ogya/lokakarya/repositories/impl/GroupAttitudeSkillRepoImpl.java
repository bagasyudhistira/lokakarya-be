package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.Division;
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
        String sql = "SELECT * FROM tbl_group_attitude_skill";
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
    public List<GroupAttitudeSkill> getGroupAttitudeSkillsPerPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM tbl_group_attitude_skill ORDER BY group_name ASC LIMIT ? OFFSET ?";
        log.info("Executing query to fetch all GroupAttitudeSkills for page {} with maximum {} entries : {}", page, pageSize, sql);
        try {
            List<GroupAttitudeSkill> result = jdbcTemplate.query(sql, rowMapper, pageSize, offset);
            log.info("Successfully fetched GroupAttitudeSkills for Page {} ({} entries)", page, result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching GroupAttitudeSkills. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public GroupAttitudeSkill getGroupAttitudeSkillById(String id) {
        String sql = "SELECT * FROM tbl_group_attitude_skill " +
                "WHERE ID = ?";
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
        groupAttitudeSkill.prePersist();
        String sql = "INSERT INTO tbl_group_attitude_skill (ID, GROUP_NAME, PERCENTAGE, ENABLED, CREATED_BY) VALUES (?, ?, ?, ?, ?)";
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
        String sql = "UPDATE tbl_group_attitude_skill SET GROUP_NAME = ?, PERCENTAGE = ?, ENABLED = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? " +
                "WHERE ID = ?";
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
        String sql = "DELETE FROM tbl_group_attitude_skill " +
                "WHERE ID = ?";
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

    @Override
    public GroupAttitudeSkill getGroupAttitudeSkillByGroupName(String groupName) {
        String sql = "SELECT * FROM tbl_group_attitude_skill WHERE LOWER(GROUP_NAME) = LOWER(?)";
        log.info("Executing query to fetch GroupAttitudeSkill by Group Name: {}. Query: {}", groupName, sql);
        try {
            GroupAttitudeSkill result = jdbcTemplate.queryForObject(sql, rowMapper, groupName);
            log.info("Successfully fetched GroupAttitudeSkill: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error while fetching GroupAttitudeSkill with Group Name: {}. Error: {}", groupName, e.getMessage());
            return null;
        }
    }

    @Override
    public Long countGroupAttitudeSkills() {
        String sql = "SELECT COUNT(ID) FROM tbl_group_attitude_skill;";
        try {
            log.info("Fetching total number of GroupAttitudeSkills");
            Long total = jdbcTemplate.queryForObject(sql, Long.class);
            log.info("Total GroupAttitudeSkills: {}", total);
            return total;
        } catch (Exception e) {
            log.error("Error fetching GroupAttitudeSkills count: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<GroupAttitudeSkill> sortGroupAttitudeSkills(String order, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM tbl_group_attitude_skill ORDER BY GROUP_NAME " + order + " LIMIT ? OFFSET ?";
        log.info("Executing query to sort GroupAttitudeSkills order {} for page {} with maximum {} entries : {}", order, page, pageSize, sql);
        try {
            List<GroupAttitudeSkill> result = jdbcTemplate.query(sql, rowMapper, pageSize, offset);
            log.info("Successfully sorted GroupAttitudeSkills order {} for Page {} ({} entries)", order, page, result.size());
            return result;
        } catch (Exception e) {
            log.error("Error sorting GroupAttitudeSkills. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<GroupAttitudeSkill> sorchGroupAttitudeSkills(String keyword, String column, String order, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM tbl_group_attitude_skill WHERE LOWER(GROUP_NAME) LIKE LOWER(CONCAT('%', COALESCE(?, ''), '%')) ORDER BY " + column + " " + order + " LIMIT ? OFFSET ?";
        log.info("Executing query to sorch GroupAttitudeSkills using keyword: {} for page {} with maximum {} entries : {}", keyword, page, pageSize, sql);
        try {
            List<GroupAttitudeSkill> result = jdbcTemplate.query(sql, rowMapper, keyword, pageSize, offset);
            log.info("Successfully sorched GroupAttitudeSkills using keyword: {} for Page {} ({} entries)", keyword, page, result.size());
            return result;
        } catch (Exception e) {
            log.error("Error sorching GroupAttitudeSkills. Error: {}", e.getMessage());
            throw e;
        }
    }
}
