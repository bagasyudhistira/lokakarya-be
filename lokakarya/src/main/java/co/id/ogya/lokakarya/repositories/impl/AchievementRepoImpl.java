package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.Achievement;
import co.id.ogya.lokakarya.repositories.AchievementRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class AchievementRepoImpl implements AchievementRepo {

    private final RowMapper<Achievement> rowMapper = new BeanPropertyRowMapper<>(Achievement.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Achievement> getAchievements() {
        String sql = "SELECT * FROM TBL_ACHIEVEMENT";
        log.info("Executing query to fetch all achievements: {}", sql);
        List<Achievement> achievements = jdbcTemplate.query(sql, rowMapper);
        log.info("Successfully fetched {} achievements", achievements.size());
        return achievements;
    }

    @Override
    public Achievement getAchievementById(String id) {
        String sql = "SELECT * FROM TBL_ACHIEVEMENT WHERE ID = ?";
        log.info("Executing query to fetch achievement with ID: {} using query: {}", id, sql);
        Achievement achievement = jdbcTemplate.queryForObject(sql, rowMapper, id);
        log.info("Successfully fetched achievement: {}", achievement);
        return achievement;
    }

    @Override
    public Achievement saveAchievement(Achievement achievement) {
        achievement.prePersist();
        String sql = "INSERT INTO TBL_ACHIEVEMENT (ID, ACHIEVEMENT, GROUP_ID, ENABLED, CREATED_BY) VALUES (?, ?, ?, ?, ?)";
        log.info("Executing query to save new achievement: {} using query: {}", achievement, sql);
        int rowsAffected = jdbcTemplate.update(sql, achievement.getId(), achievement.getAchievement(), achievement.getGroupId(), achievement.isEnabled(), achievement.getCreatedBy());
        if (rowsAffected > 0) {
            log.info("Successfully saved achievement: {}", achievement);
            return achievement;
        } else {
            log.warn("Failed to save achievement: {}", achievement);
            return null;
        }
    }

    @Override
    public Achievement updateAchievement(Achievement achievement) {
        String sql = "UPDATE TBL_ACHIEVEMENT SET ACHIEVEMENT = ?, GROUP_ID = ?, ENABLED = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        log.info("Executing query to update achievement with ID: {} using query: {}", achievement.getId(), sql);
        int rowsAffected = jdbcTemplate.update(sql, achievement.getAchievement(), achievement.getGroupId(), achievement.isEnabled(), achievement.getUpdatedBy(), achievement.getId());
        if (rowsAffected > 0) {
            log.info("Successfully updated achievement: {}", achievement);
            return achievement;
        } else {
            log.warn("Failed to update achievement with ID: {}", achievement.getId());
            return null;
        }
    }

    @Override
    public Boolean deleteAchievement(String id) {
        String sql = "DELETE FROM TBL_ACHIEVEMENT WHERE ID = ?";
        log.info("Executing query to delete achievement with ID: {} using query: {}", id, sql);
        int rowsAffected = jdbcTemplate.update(sql, id);
        if (rowsAffected > 0) {
            log.info("Successfully deleted achievement with ID: {}", id);
            return true;
        } else {
            log.warn("Failed to delete achievement with ID: {}", id);
            return false;
        }
    }
}
