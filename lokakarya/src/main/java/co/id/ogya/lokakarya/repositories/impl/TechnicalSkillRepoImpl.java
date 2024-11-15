package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.TechnicalSkill;
import co.id.ogya.lokakarya.repositories.TechnicalSkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TechnicalSkillRepoImpl implements TechnicalSkillRepo {

    private RowMapper<TechnicalSkill> rowMapper = new BeanPropertyRowMapper<>(TechnicalSkill.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TechnicalSkill> getTechnicalSkills() {
        String sql = "SELECT * FROM TBL_TECHNICAL_SKILL";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public TechnicalSkill getTechnicalSkillById(String id) {
        String sql = "SELECT * FROM TBL_TECHNICAL_SKILL WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper);
    }

    @Override
    public TechnicalSkill saveTechnicalSkill(TechnicalSkill technicalSkill) {
        String sql = "INSERT INTO TBL_TECHNICAL_SKILL (ID, TECHNICAL_SKILL, ENABLED, CREATED_BY) VALUES (?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, technicalSkill.getId(), technicalSkill.getTechnicalSkill(), technicalSkill.isEnabled(), technicalSkill.getCreatedBy());
        if (rowsAffected > 0) {
            return technicalSkill;
        } else {
            return null;
        }
    }

    @Override
    public TechnicalSkill updateTechnicalSkill(TechnicalSkill technicalSkill) {
        String sql = "UPDATE TBL_TECHNICAL_SKILL SET TECHNICAL_SKILL = ?, ENABLED = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, technicalSkill.getTechnicalSkill(), technicalSkill.isEnabled(), technicalSkill.getUpdatedBy(), technicalSkill.getId());
        if (rowsAffected > 0) {
            return technicalSkill;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteTechnicalSkill(String id) {
        String sql = "DELETE FROM TBL_TECHNICAL_SKILL WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
