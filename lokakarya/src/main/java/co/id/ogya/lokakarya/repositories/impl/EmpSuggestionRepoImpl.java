package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.EmpSuggestion;
import co.id.ogya.lokakarya.repositories.EmpSuggestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpSuggestionRepoImpl implements EmpSuggestionRepo {

    RowMapper<EmpSuggestion> rowMapper = new BeanPropertyRowMapper<>(EmpSuggestion.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EmpSuggestion> getEmpSuggestions() {
        String sql = "SELECT * FROM TBL_EMP_SUGGESTION";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public EmpSuggestion getEmpSuggestionById(String id) {
        String sql = "SELECT * FROM TBL_EMP_SUGGESTION WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public EmpSuggestion saveEmpSuggestion(EmpSuggestion empSuggestion) {
        String sql = "INSERT INTO TBL_EMP_SUGGESTION (ID, USER_ID, SUGGESTION, ASSESSMENT_YEAR, CREATED_BY) VALUES (?,?,?,?,?)";
        int rowsAffected = jdbcTemplate.update(sql, empSuggestion.getId(), empSuggestion.getUserId(), empSuggestion.getSuggestion(), empSuggestion.getAssessmentYear(), empSuggestion.getCreatedBy());
        if (rowsAffected > 0) {
            return empSuggestion;
        } else {
            return null;
        }
    }

    @Override
    public EmpSuggestion updateEmpSuggestion(EmpSuggestion empSuggestion) {
        String sql = "UPDATE TBL_EMP_SUGGESTION SET USER_ID = ?, SUGGESSTION = ?, ASSESSMENT_YEAR = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, empSuggestion.getUserId(), empSuggestion.getSuggestion(), empSuggestion.getAssessmentYear(), empSuggestion.getUpdatedBy(), empSuggestion.getId());
        if (rowsAffected > 0) {
            return empSuggestion;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteEmpSuggestion(String id) {
        String sql = "DELETE FROM TBL_EMP_SUGGESTION WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
