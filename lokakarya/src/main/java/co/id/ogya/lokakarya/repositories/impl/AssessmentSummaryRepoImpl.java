package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.AssessmentSummary;
import co.id.ogya.lokakarya.repositories.AssessmentSummaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AssessmentSummaryRepoImpl implements AssessmentSummaryRepo {

    private RowMapper<AssessmentSummary> rowMapper = new BeanPropertyRowMapper<>(AssessmentSummary.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<AssessmentSummary> getAssessmentSummarys() {
        String sql = "SELECT * FROM TBL_ASSESSMENT_SUMMARY";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public AssessmentSummary getAssessmentSummaryById(String id) {
        String sql = "SELECT * FROM TBL_ASSESSMENT_SUMMARY";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public AssessmentSummary saveAssessmentSummary(AssessmentSummary assessmentSummary) {
        String sql = "INSERT INTO TBL_ASSESSMENT_SUMMARY (ID, USER_ID, YEAR, SCORE, STATUS, CREATED_BY) VALUES (?, ?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, assessmentSummary.getId(), assessmentSummary.getUserId(), assessmentSummary.getYear(), assessmentSummary.getScore(), assessmentSummary.getStatus(), assessmentSummary.getCreatedBy());
        if (rowsAffected > 0) {
            return assessmentSummary;
        } else {
            return null;
        }
    }

    @Override
    public AssessmentSummary updateAssessmentSummary(AssessmentSummary assessmentSummary) {
        String sql = "UPDATE TBL_ASSESSMENT_SUMMARY SET USER_ID = ?, YEAR = ?, SCORE = ?, STATUS = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, assessmentSummary.getUserId(), assessmentSummary.getYear(), assessmentSummary.getScore(), assessmentSummary.getStatus(), assessmentSummary.getUpdatedBy(), assessmentSummary.getId());
        if (rowsAffected > 0) {
            return assessmentSummary;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteAssessmentSummary(String id) {
        String sql = "DELETE FROM TBL_ASSESSMENT_SUMMARY WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
