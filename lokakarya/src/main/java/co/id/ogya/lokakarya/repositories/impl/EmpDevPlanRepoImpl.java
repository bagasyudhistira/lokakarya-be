package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.DevPlan;
import co.id.ogya.lokakarya.entities.EmpDevPlan;
import co.id.ogya.lokakarya.repositories.EmpDevPlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpDevPlanRepoImpl implements EmpDevPlanRepo {

    RowMapper<EmpDevPlan> rowMapper = new BeanPropertyRowMapper<>(EmpDevPlan.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EmpDevPlan> getEmpDevPlans() {
        String sql = "SELECT * FROM TBL_EMP_DEV_PLAN";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public EmpDevPlan getEmpDevPlanById(String id) {
        String sql = "SELECT * FROM TBL_EMP_DEV_PLAN WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public EmpDevPlan saveEmpDevPlan(EmpDevPlan empDevPlan) {
        String sql = "INSERT INTO TBL_EMP_DEV_PLAN(ID, USER_ID, DEV_PLAN_ID, ASSESSMENT_YEAR, CREATED_BY) VALUES(?,?,?,?)";
        int rowsAffected = jdbcTemplate.update(sql, empDevPlan.getId(), empDevPlan.getUserId(), empDevPlan.getDevPlanId(), empDevPlan.getAssessmentYear(), empDevPlan.getCreatedBy());
        if (rowsAffected > 0) {
            return empDevPlan;
        } else {
            return null;
        }
    }

    @Override
    public EmpDevPlan updateEmpDevPlan(EmpDevPlan empDevPlan) {
        String sql = "UPDATE TBL_EMP_DEV_PLAN SET USER_ID = ?, DEV_PLAN_ID = ?, ASSESSMENT_YEAR = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, empDevPlan.getUserId(), empDevPlan.getDevPlanId(), empDevPlan.getAssessmentYear(), empDevPlan.getCreatedBy(), empDevPlan.getId());
        if (rowsAffected > 0) {
            return empDevPlan;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteEmpDevPlan(String id) {
        String sql = "DELETE FROM TBL_EMP_DEV_PLAN WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
