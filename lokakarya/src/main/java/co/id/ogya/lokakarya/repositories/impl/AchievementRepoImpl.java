package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.Achievement;
import co.id.ogya.lokakarya.repositories.AchievementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AchievementRepoImpl implements AchievementRepo {

    private RowMapper<Achievement> rowMapper = new BeanPropertyRowMapper<>(Achievement.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Achievement> getAchievements() {
        String sql = "SELECT * FROM TBL_ACHIEVEMENT";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Achievement getAchievementById(String id) {
        String sql = "SELECT * FROM TBL_ACHIEVEMENT WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public Achievement saveAchievement(Achievement achievement) {
        String sql = "INSERT INTO TBL_ACHIEVEMENT (ID, ACHIEVEMENT, GROUP_ID, ENABLED, CREATED_BY) VALUES (?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, achievement.getId(), achievement.getAchievement(), achievement.getGroupId(), achievement.isEnabled(), achievement.getCreatedBy());
        if (rowsAffected > 0) {
            return achievement;
        } else {
            return null;
        }
    }

    @Override
    public Achievement updateAchievement(Achievement achievement) {
        String sql = "UPDATE TBL_ACHIEVEMENT SET ACHIEVEMENT = ?, GROUP_ID = ?, ENABLED = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, achievement.getAchievement(), achievement.getGroupId(), achievement.isEnabled(), achievement.getUpdatedBy(), achievement.getId());
        if (rowsAffected > 0) {
            return achievement;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteAchievement(String id) {
        String sql = "DELETE FROM TBL_ACHIEVEMENT WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
