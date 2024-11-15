package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.GroupAttitudeSkill;
import co.id.ogya.lokakarya.repositories.GroupAttitudeSkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupAttitudeSkillRepoImpl implements GroupAttitudeSkillRepo {

    private RowMapper<GroupAttitudeSkill> rowMapper = new BeanPropertyRowMapper<>(GroupAttitudeSkill.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<GroupAttitudeSkill> getGroupAttitudeSkills() {
        String sql = "SELECT * FROM TBL_GROUP_ATTITUDE_SKILL";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public GroupAttitudeSkill getGroupAttitudeSkillById(String id) {
        String sql = "SELECT * FROM TBL_GROUP_ATTITUDE_SKILL WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public GroupAttitudeSkill saveGroupAttitudeSkill(GroupAttitudeSkill groupAttitudeSkill) {
        String sql = "INSERT INTO TBL_GROUP_ATTITUDE_SKILL (ID, GROUP_NAME, PERCENTAGE, ENABLED, CREATED_BY) VALUES (?, ?, ?, ?, ?) ";
        int rowsAffected = jdbcTemplate.update(sql, groupAttitudeSkill.getId(), groupAttitudeSkill.getGroupName(), groupAttitudeSkill.getPercentage(), groupAttitudeSkill.isEnabled(), groupAttitudeSkill.getCreatedBy());
        if (rowsAffected > 0) {
            return groupAttitudeSkill;
        } else {
            return null;
        }
    }

    @Override
    public GroupAttitudeSkill updateGroupAttitudeSkill(GroupAttitudeSkill groupAttitudeSkill) {
        String sql = "UPDATE TBL_GROUP_ATTITUDE_SKILL SET GROUP_NAME = ?, PERCENTAGE = ?, ENABLED = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(groupAttitudeSkill.getGroupName(), groupAttitudeSkill.getPercentage(), groupAttitudeSkill.isEnabled(), groupAttitudeSkill.getUpdatedBy(), groupAttitudeSkill.getId());
        if (rowsAffected > 0) {
            return groupAttitudeSkill;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteGroupAttitudeSkill(String id) {
        String sql = "DELETE FROM TBL_GROUP_ATTITUDE_SKILL WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
