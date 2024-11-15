package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.EmpAttitudeSkill;
import co.id.ogya.lokakarya.repositories.EmpAttitudeSkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpAttitudeSkillRepoImpl implements EmpAttitudeSkillRepo {

    private RowMapper<EmpAttitudeSkill> rowMapper = new BeanPropertyRowMapper<>(EmpAttitudeSkill.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<EmpAttitudeSkill> getEmpAttitudeSkills() {
        String sql = "SELECT * FROM TBL_EMP_ATTITUDE_SKILL";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public EmpAttitudeSkill getEmpAttitudeSkillById(String id) {
        String sql = "SELECT * FROM TBL_EMP_ATTITUDE_SKILL WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public EmpAttitudeSkill saveEmpAttitudeSkill(EmpAttitudeSkill empAttitudeSkill) {
        String sql = "INSERT INTO TBL_EMP_ATTITUDE_SKILL (ID, USER_ID, ATTITUDE_SKILL_ID, SCORE, ASSESSMENT_YEAR, CREATED_BY) VALUES (?, ?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, empAttitudeSkill.getId(), empAttitudeSkill.getUserId(), empAttitudeSkill.getAttitudeSkillId(), empAttitudeSkill.getScore(), empAttitudeSkill.getAssessmentYear(), empAttitudeSkill.getCreatedBy());
        if (rowsAffected > 0) {
            return empAttitudeSkill;
        } else {
            return null;
        }
    }

    @Override
    public EmpAttitudeSkill updateEmpAttitudeSkill(EmpAttitudeSkill empAttitudeSkill) {
        String sql = "UPDATE TBL_EMP_ATTITUDE_SKILL SET USER_ID = ?, ATTITUDE_SKILL_ID = ?, SCORE = ?, ASSESSMENT_YEAR = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql,empAttitudeSkill.getUserId(), empAttitudeSkill.getAttitudeSkillId(), empAttitudeSkill.getScore(), empAttitudeSkill.getAssessmentYear(), empAttitudeSkill.getId());
        if (rowsAffected > 0) {
            return empAttitudeSkill;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteEmpAttitudeSkill(String id) {
        String sql = "DELETE FROM TBL_EMP_ATTITUDE_SKILL WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
