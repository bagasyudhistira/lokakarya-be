package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.GroupAchievement;
import co.id.ogya.lokakarya.repositories.GroupAchievementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupAchievementRepoImpl implements GroupAchievementRepo {

    private RowMapper<GroupAchievement> rowMapper = new BeanPropertyRowMapper<>(GroupAchievement.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<GroupAchievement> getGroupAchievements() {
        String sql = "SELECT * FROM TBL_GROUP_ACHIEVEMENT";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public GroupAchievement getGroupAchievementById(String id) {
        String sql = "SELECT * FROM TBL_GROUP_ACHIEVEMENT WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public GroupAchievement saveGroupAchievement(GroupAchievement groupAchievement) {
        String sql = "INSERT INTO tbl_group_achievement(id, group_name, percentage, enabled, created_by) VALUES(?,?,?,?,?)";
        int rowsAffected = jdbcTemplate.update(sql, groupAchievement.getId(), groupAchievement.getGroupName(), groupAchievement.getPercentage(), groupAchievement.isEnabled(), groupAchievement.getCreatedBy());
        if (rowsAffected > 0) {
            return groupAchievement;
        } else {
            return null;
        }
    }

    @Override
    public GroupAchievement updateGroupAchievement(GroupAchievement groupAchievement) {
        String sql = "UPDATE TBL_GROUP_ACHIEVEMENT SET GROUP_NAME = ?, PERCENTAGE = ?, ENABLED = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, groupAchievement.getGroupName(), groupAchievement.getPercentage(), groupAchievement.isEnabled(), groupAchievement.getUpdatedBy(), groupAchievement.getId());
        if (rowsAffected > 0) {
            return groupAchievement;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteGroupAchievement(String id) {
        String sql = "DELETE FROM TBL_GROUP_ACHIEVEMENT WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
