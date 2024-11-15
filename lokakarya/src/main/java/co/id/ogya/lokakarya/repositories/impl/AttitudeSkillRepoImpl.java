package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.AttitudeSkill;
import co.id.ogya.lokakarya.repositories.AttitudeSkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttitudeSkillRepoImpl implements AttitudeSkillRepo {

    private RowMapper<AttitudeSkill> rowMapper = new BeanPropertyRowMapper<>(AttitudeSkill.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AttitudeSkill> getAttitudeSkills() {
        String sql = "SELECT * FROM TBL_ATTITUDE_SKILL";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public AttitudeSkill getAttitudeSkillById(String id) {
        String sql = "SELECT * FROM TBL_ATTITUDE_SKILL WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public AttitudeSkill saveAttitudeSkill(AttitudeSkill attitudeSkill) {
        String sql = "INSERT INTO TBL_ATTITUDE_SKILL (ID, ATTITUDE_SKILL, GROUP_ID, ENABLED, CREATED_BY) VALUES (?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, attitudeSkill.getId(),attitudeSkill.getAttitudeSkill(), attitudeSkill.getGroupId(),attitudeSkill.isEnabled(),attitudeSkill.getCreatedBy());
        if (rowsAffected > 0) {
            return attitudeSkill;
        } else {
            return null;
        }
    }

    @Override
    public AttitudeSkill updateAttitudeSkill(AttitudeSkill attitudeSkill) {
        String sql = "UPDATE TBL_ATTITUDE_SKILL SET ATTITUDE_SKILL = ?, GROUP_ID = ?, ENABLED = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, attitudeSkill.getAttitudeSkill(), attitudeSkill.getGroupId(), attitudeSkill.isEnabled(), attitudeSkill.getUpdatedBy(), attitudeSkill.getId());
        if (rowsAffected > 0) {
            return attitudeSkill;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteAttitudeSkill(String id) {
        String sql = "DELETE FROM TBL_ATTITUDE_SKILL WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
