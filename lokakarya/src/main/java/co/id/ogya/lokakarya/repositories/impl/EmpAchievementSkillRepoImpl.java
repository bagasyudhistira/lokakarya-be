package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.EmpAchievementSkill;
import co.id.ogya.lokakarya.repositories.EmpAchievementSkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpAchievementSkillRepoImpl implements EmpAchievementSkillRepo {

    private RowMapper<EmpAchievementSkill> rowMapper = new BeanPropertyRowMapper<>(EmpAchievementSkill.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EmpAchievementSkill> getEmpAchievementSkills() {
        String sql = "SELECT * FROM TBL_EMP_ACHIEVEMENT_SKILL";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public EmpAchievementSkill getEmpAchievementSkillById(String id) {
        String sql = "SELECT * FROM TBL_EMP_ACHIEVEMENT_SKILL WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public EmpAchievementSkill saveEmpAchievementSkill(EmpAchievementSkill empAchievementSkill) {
        String sql = "INSERT INTO TBL_EMP_AHCIEVEMENT_SKILL (ID, USER_ID, NOTES, ACHIEVEMENT_ID, SCORE, ASSESSMENT_YEAR, CREATED_BY) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, empAchievementSkill.getId(), empAchievementSkill.getUserId(), empAchievementSkill.getNotes(), empAchievementSkill.getAchievementId(), empAchievementSkill.getScore(), empAchievementSkill.getAssessmentYear(), empAchievementSkill.getCreatedBy());
        if (rowsAffected > 0) {
            return empAchievementSkill;
        } else {
            return null;
        }
    }

    @Override
    public EmpAchievementSkill updateEmpAchievementSkill(EmpAchievementSkill empAchievementSkill) {
        String sql = "UPDATE TBL_EMP_ACHIEVEMENT_SKILL SET USER_ID = ?, NOTES = ?, ACHIEVEMENT_ID = ?, SCORE = ?, ASSESSMENT_YEAR = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql , empAchievementSkill.getUserId(), empAchievementSkill.getNotes(), empAchievementSkill.getAchievementId(), empAchievementSkill.getScore(), empAchievementSkill.getAssessmentYear(), empAchievementSkill.getUpdatedBy(), empAchievementSkill.getId());
        if (rowsAffected > 0) {
            return empAchievementSkill;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteEmpAchievementSkill(String id) {
        String sql = "DELETE FROM TBL_EMP_ACHIEVEMENT_SKILL WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
