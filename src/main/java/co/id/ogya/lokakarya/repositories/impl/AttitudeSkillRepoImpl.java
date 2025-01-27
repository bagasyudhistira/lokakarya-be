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
import java.util.Map;

@Slf4j
@Repository
public class AttitudeSkillRepoImpl implements AttitudeSkillRepo {

    private final RowMapper<AttitudeSkill> rowMapper = new BeanPropertyRowMapper<>(AttitudeSkill.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AttitudeSkill> getAttitudeSkills() {
        String sql = "SELECT * FROM tbl_attitude_skill";
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
        String sql = "SELECT * FROM tbl_attitude_skill WHERE ID = ?";
        log.info("Executing query to fetch AttitudeSkill by ID: {}", id);
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
    public List<Map<String, Object>> getAttitudeSkillGets() {
        String sql = "SELECT ats.ID, ATTITUDE_SKILL, GROUP_NAME, ats.ENABLED " +
                "FROM tbl_attitude_skill ats " +
                "LEFT JOIN tbl_group_attitude_skill gas ON ats.GROUP_ID = gas.ID";
        log.info("Executing query to fetch all AttitudeSkills with group details: {}", sql);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
            log.info("Successfully fetched {} AttitudeSkills with group details.", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching all AttitudeSkills with group details. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> getAttitudeSkillGetsPerPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT ats.ID, ATTITUDE_SKILL, GROUP_NAME, ats.ENABLED " +
                "FROM tbl_attitude_skill ats " +
                "LEFT JOIN tbl_group_attitude_skill gas ON ats.GROUP_ID = gas.ID ORDER BY ATTITUDE_SKILL LIMIT ? OFFSET ?";
        log.info("Executing query to fetch all AttitudeSkills for page {} with maximum {} entries : {}", page, pageSize, sql);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, pageSize, offset);
            log.info("Successfully fetched AttitudeSkills for Page {} ({} entries)", page, result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching all AttitudeSkills with group details. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Map<String, Object> getAttitudeSkillGetById(String id) {
        String sql = "SELECT ats.ID, ATTITUDE_SKILL, GROUP_NAME, ats.ENABLED " +
                "FROM tbl_attitude_skill ats " +
                "LEFT JOIN tbl_group_attitude_skill gas ON ats.GROUP_ID = gas.ID " +
                "WHERE ats.ID = ?";
        log.info("Executing query to fetch AttitudeSkill by ID with group details: {}", id);
        try {
            Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
            log.info("Successfully fetched AttitudeSkill with group details: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error fetching AttitudeSkill by ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public AttitudeSkill saveAttitudeSkill(AttitudeSkill attitudeSkill) {
        attitudeSkill.prePersist();
        String sql = "INSERT INTO tbl_attitude_skill (ID, ATTITUDE_SKILL, GROUP_ID, ENABLED, CREATED_BY) " +
                "VALUES (?, ?, ?, ?, ?)";
        log.info("Executing query to save AttitudeSkill: {}", attitudeSkill);
        try {
            int rowsAffected = jdbcTemplate.update(sql, attitudeSkill.getId(), attitudeSkill.getAttitudeSkill(),
                    attitudeSkill.getGroupId(), attitudeSkill.isEnabled(), attitudeSkill.getCreatedBy());
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
        String sql = "UPDATE tbl_attitude_skill SET ATTITUDE_SKILL = ?, GROUP_ID = ?, ENABLED = ?, " +
                "UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        log.info("Executing query to update AttitudeSkill with ID: {}", attitudeSkill.getId());
        try {
            int rowsAffected = jdbcTemplate.update(sql, attitudeSkill.getAttitudeSkill(), attitudeSkill.getGroupId(),
                    attitudeSkill.isEnabled(), attitudeSkill.getUpdatedBy(), attitudeSkill.getId());
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
        String sql = "DELETE FROM tbl_attitude_skill WHERE ID = ?";
        log.info("Executing query to delete AttitudeSkill with ID: {}", id);
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

    @Override
    public AttitudeSkill getAttitudeSkillByName(String attitudeSkillName) {
        String sql = "SELECT * FROM tbl_attitude_skill WHERE LOWER(ATTITUDE_SKILL) = LOWER(?)";
        log.info("Executing query to fetch AttitudeSkill by Attitude Skill: {}", attitudeSkillName);
        try {
            AttitudeSkill result = jdbcTemplate.queryForObject(sql, rowMapper, attitudeSkillName);
            log.info("Successfully fetched AttitudeSkill: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error fetching AttitudeSkill by Attitude Skill: {}. Error: {}", attitudeSkillName, e.getMessage());
            return null;
        }
    }

    @Override
    public Long countAttitudeSkills(String keyword) {
        String sql = "SELECT COUNT(ats.ID) FROM tbl_attitude_skill ats LEFT JOIN tbl_group_attitude_skill gat ON ats.GROUP_ID = gat.ID WHERE LOWER(GROUP_NAME) LIKE LOWER(CONCAT('%', COALESCE(?, ''), '%')) OR LOWER(ATTITUDE_SKILL) LIKE LOWER(CONCAT('%', COALESCE(?, ''), '%'))";
        log.info("Executing query to count AttitudeSkills: {}", sql);
        try {
            Long total = jdbcTemplate.queryForObject(sql, Long.class, keyword, keyword);
            log.info("Total AttitudeSkills: {}", total);
            return total;
        } catch (Exception e) {
            log.error("Error counting AttitudeSkills: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> sortAttitudeSkillGetsOrderBy(String column, String order, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT ats.ID, ATTITUDE_SKILL, GROUP_NAME, ats.ENABLED FROM tbl_attitude_skill ats LEFT JOIN tbl_group_attitude_skill gas ON ats.GROUP_ID = gas.ID ORDER BY " + column + " " + order + " LIMIT ? OFFSET ?";
        log.info("Executing query to sort AttitudeSkills order by {} {} for page {} with maximum {} entries : {}", column, order, page, pageSize, sql);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, pageSize, offset);
            log.info("Successfully sorted AttitudeSkills order by {} {} for Page {} ({} entries)", column, order, page, result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching all AttitudeSkills with group details. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> sorchAttitudeSkillGets(String keyword, String column, String order, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT ats.ID, ATTITUDE_SKILL, ats.ENABLED, GROUP_ID, GROUP_NAME, gat.ENABLED GROUP_ENABLED, PERCENTAGE  FROM tbl_attitude_skill ats LEFT JOIN tbl_group_attitude_skill gat ON ats.GROUP_ID = gat.ID WHERE LOWER(GROUP_NAME) LIKE LOWER(CONCAT('%', COALESCE(?, ''), '%')) OR LOWER(ATTITUDE_SKILL) LIKE LOWER(CONCAT('%', COALESCE(?, ''), '%')) ORDER BY " + column + " " + order + " LIMIT ? OFFSET ?";
        log.info("Executing query to sorch AttitudeSkills using keyword: {} for page {} with maximum {} entries : {}", keyword, page, pageSize, sql);
        try {
            List<Map<String, Object>> appUsers = jdbcTemplate.queryForList(sql, keyword, keyword, pageSize, offset);
            log.info("Successfully sorched AttitudeSkills using keyword: {} for Page {} ({} entries)", keyword, page, appUsers.size());
            return appUsers;
        } catch (Exception e) {
            log.error("Error sorching AttitudeSkills: {}", e.getMessage(), e);
            throw e;
        }
    }
}
