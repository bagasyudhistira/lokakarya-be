package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.EmpTechnicalSkill;
import co.id.ogya.lokakarya.repositories.EmpTechnicalSkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpTechnicalSkillRepoImpl implements EmpTechnicalSkillRepo {

    RowMapper<EmpTechnicalSkill> rowMapper = new BeanPropertyRowMapper<>(EmpTechnicalSkill.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EmpTechnicalSkill> getEmpTechnicalSkills() {
        String sql = "SELECT * FROM TBL_EMP_TECHNICAL_SKILL";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public EmpTechnicalSkill getEmpTechnicalSkillById(String id) {
        String sql = "SELECT * FROM TBL_EMP_TECHNICAL_SKILL WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public EmpTechnicalSkill saveEmpTechnicalSkill(EmpTechnicalSkill empTechnicalSkill) {
        String sql = "INSERT INTO TBL_EMP_TECHNICAL_SKILL(ID, USER_ID, TECHNICAL_SKILL_ID, SCORE, ASSESSMENT_YEAR, CREATED_BY) VALUES(?,?,?,?,?)";
        int rowsAffected = jdbcTemplate.update(sql, empTechnicalSkill.getId(), empTechnicalSkill.getUserId(), empTechnicalSkill.getTechnicalSkillId(), empTechnicalSkill.getScore(), empTechnicalSkill.getAssessmentYear(), empTechnicalSkill.getCreatedBy());
        if (rowsAffected > 0) {
            return empTechnicalSkill;
        } else {
            return null;
        }
    }

    @Override
    public EmpTechnicalSkill updateEmpTechnicalSkill(EmpTechnicalSkill empTechnicalSkill) {
        String sql = "UPDATE TBL_EMP_TECHNICAL_SKILL SET USER_ID = ?, TECHNICAL_SKILL_ID = ?, SCORE = ?, ASSESSMENT_YEAR = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, empTechnicalSkill.getUserId(), empTechnicalSkill.getTechnicalSkillId(), empTechnicalSkill.getScore(), empTechnicalSkill.getAssessmentYear(), empTechnicalSkill.getUpdatedBy(), empTechnicalSkill.getId());
        if (rowsAffected > 0) {
            return empTechnicalSkill;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteEmpTechnicalSkill(String id) {
        String sql = "DELETE FROM TBL_EMP_TECHNICAL_SKILL WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
