package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.Achievement;
import co.id.ogya.lokakarya.repositories.AchievementRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class AchievementRepoImpl implements AchievementRepo {

    private final RowMapper<Achievement> rowMapper = new BeanPropertyRowMapper<>(Achievement.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Achievement> getAchievements() {
        String sql = "SELECT * FROM tbl_achievement";
        log.info("Executing query to fetch all achievements: {}", sql);
        try {
            List<Achievement> achievements = jdbcTemplate.query(sql, rowMapper);
            log.info("Successfully fetched {} achievements", achievements.size());
            return achievements;
        } catch (DataAccessException ex) {
            log.error("Error fetching achievements: {}", ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public Achievement getAchievementById(String id) {
        String sql = "SELECT * FROM tbl_achievement WHERE ID = ?";
        log.info("Executing query to fetch achievement with ID: {} using query: {}", id, sql);
        try {
            Achievement achievement = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Successfully fetched achievement: {}", achievement);
            return achievement;
        } catch (DataAccessException ex) {
            log.error("Error fetching achievement with ID {}: {}", id, ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public List<Map<String, Object>> getAchievementGets() {
        String sql = "SELECT a.ID, ACHIEVEMENT, GROUP_NAME, a.ENABLED " +
                "FROM tbl_achievement a " +
                "LEFT JOIN tbl_group_achievement ga ON a.GROUP_ID = ga.ID";
        log.info("Executing query to fetch all achievements: {}", sql);
        try {
            List<Map<String, Object>> achievements = jdbcTemplate.queryForList(sql);
            log.info("Successfully fetched {} achievements", achievements.size());
            return achievements;
        } catch (DataAccessException ex) {
            log.error("Error fetching achievements: {}", ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public List<Map<String, Object>> getAchievementGetsPerPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT ach.ID, ACHIEVEMENT, GROUP_NAME, ach.ENABLED " +
                "FROM tbl_achievement ach " +
                "LEFT JOIN tbl_group_achievement gac ON ach.GROUP_ID = gac.ID ORDER BY ACHIEVEMENT LIMIT ? OFFSET ?";
        log.info("Executing query to fetch all Achievements for page {} with maximum {} entries : {}", page, pageSize, sql);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, pageSize, offset);
            log.info("Successfully fetched Achievements for Page {} ({} entries)", page, result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching all Achievements with group details. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Map<String, Object> getAchievementGetById(String id) {
        String sql = "SELECT a.ID, ACHIEVEMENT, GROUP_NAME, a.ENABLED " +
                "FROM tbl_achievement a " +
                "LEFT JOIN tbl_group_achievement ga ON a.GROUP_ID = ga.ID " +
                "WHERE a.ID = ?";
        log.info("Executing query to fetch achievement with ID: {} using query: {}", id, sql);
        try {
            Map<String, Object> achievement = jdbcTemplate.queryForMap(sql, id);
            log.info("Successfully fetched achievement: {}", achievement);
            return achievement;
        } catch (DataAccessException ex) {
            log.error("Error fetching achievement with ID {}: {}", id, ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public Achievement saveAchievement(Achievement achievement) {
        achievement.prePersist();
        String sql = "INSERT INTO tbl_achievement (ID, ACHIEVEMENT, GROUP_ID, ENABLED, CREATED_BY) VALUES (?, ?, ?, ?, ?)";
        log.info("Executing query to save new achievement: {} using query: {}", achievement, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, achievement.getId(), achievement.getAchievement(), achievement.getGroupId(), achievement.isEnabled(), achievement.getCreatedBy());
            if (rowsAffected > 0) {
                log.info("Successfully saved achievement: {}", achievement);
                return achievement;
            } else {
                log.warn("Failed to save achievement: {}", achievement);
                return null;
            }
        } catch (DataAccessException ex) {
            log.error("Error saving achievement: {}", ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public Achievement updateAchievement(Achievement achievement) {
        String sql = "UPDATE tbl_achievement SET ACHIEVEMENT = ?, GROUP_ID = ?, ENABLED = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        log.info("Executing query to update achievement with ID: {} using query: {}", achievement.getId(), sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, achievement.getAchievement(), achievement.getGroupId(), achievement.isEnabled(), achievement.getUpdatedBy(), achievement.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated achievement: {}", achievement);
                return achievement;
            } else {
                log.warn("Failed to update achievement with ID: {}", achievement.getId());
                return null;
            }
        } catch (DataAccessException ex) {
            log.error("Error updating achievement with ID {}: {}", achievement.getId(), ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public Boolean deleteAchievement(String id) {
        String sql = "DELETE FROM tbl_achievement WHERE ID = ?";
        log.info("Executing query to delete achievement with ID: {} using query: {}", id, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted achievement with ID: {}", id);
                return true;
            } else {
                log.warn("Failed to delete achievement with ID: {}", id);
                return false;
            }
        } catch (DataAccessException ex) {
            log.error("Error deleting achievement with ID {}: {}", id, ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public Achievement getAchievementByName(String achievementName) {
        String sql = "SELECT * FROM tbl_achievement WHERE LOWER(ACHIEVEMENT) = LOWER(?)";
        log.info("Executing query to fetch achievement with Achievement: {} using query: {}", achievementName, sql);
        try {
            Achievement achievement = jdbcTemplate.queryForObject(sql, rowMapper, achievementName);
            log.info("Successfully fetched achievement: {}", achievement);
            return achievement;
        } catch (DataAccessException ex) {
            log.error("Error fetching achievement with ID {}: {}", achievementName, ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public Long countAchievement(String keyword) {
        String sql = "SELECT COUNT(ach.ID) FROM tbl_achievement ach LEFT JOIN tbl_group_achievement gac ON ach.GROUP_ID = gac.ID WHERE LOWER(GROUP_NAME) LIKE LOWER(CONCAT('%', COALESCE(?, ''), '%')) OR LOWER(ACHIEVEMENT) LIKE LOWER(CONCAT('%', COALESCE(?, ''), '%'))";
        log.info("Executing query to count Achievements: {}", sql);
        try {
            Long total = jdbcTemplate.queryForObject(sql, Long.class, keyword, keyword);
            log.info("Total Achievements: {}", total);
            return total;
        } catch (Exception e) {
            log.error("Error counting Achievements: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> sortAchievementGetsOrderBy(String column, String order, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT ach.ID, ACHIEVEMENT, GROUP_NAME, ach.ENABLED FROM tbl_achievement ach LEFT JOIN tbl_group_achievement gac ON ach.GROUP_ID = gac.ID ORDER BY " + column + " " + order + " LIMIT ? OFFSET ?";
        log.info("Executing query to sort Achievements order by {} {} for page {} with maximum {} entries : {}", column, order, page, pageSize, sql);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, pageSize, offset);
            log.info("Successfully sorted Achievements order by {} {} for Page {} ({} entries)", column, order, page, result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching all Achievements with group details. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> sorchAchievementGets(String keyword, String column, String order, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT ach.ID, ACHIEVEMENT, ach.ENABLED, GROUP_ID, GROUP_NAME, gac.ENABLED GROUP_ENABLED, PERCENTAGE  FROM tbl_achievement ach LEFT JOIN tbl_group_achievement gac ON ach.GROUP_ID = gac.ID WHERE LOWER(GROUP_NAME) LIKE LOWER(CONCAT('%', COALESCE(?, ''), '%')) OR LOWER(ACHIEVEMENT) LIKE LOWER(CONCAT('%', COALESCE(?, ''), '%')) ORDER BY " + column + " " + order + " LIMIT ? OFFSET ?";
        log.info("Executing query to sorch Achievements using keyword: {} for page {} with maximum {} entries : {}", keyword, page, pageSize, sql);
        try {
            List<Map<String, Object>> appUsers = jdbcTemplate.queryForList(sql, keyword, keyword, pageSize, offset);
            log.info("Successfully sorched Achievements using keyword: {} for Page {} ({} entries)", keyword, page, appUsers.size());
            return appUsers;
        } catch (Exception e) {
            log.error("Error sorching Achievements: {}", e.getMessage(), e);
            throw e;
        }
    }
}
