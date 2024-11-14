package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.AppRoleMenu;
import co.id.ogya.lokakarya.entities.DevPlan;
import co.id.ogya.lokakarya.repositories.DevPlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DevPlanRepoImpl implements DevPlanRepo {

    RowMapper<DevPlan> rowMapper = new BeanPropertyRowMapper<>(DevPlan.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<DevPlan> getDevPlans() {
        String sql = "SELECT * FROM TBL_DEV_PLAN";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public DevPlan getDevPlanById(String id) {
        String sql = "SELECT * FROM TBL_DEV_PLAN WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public DevPlan saveDevPlan(DevPlan devPlan) {
        String sql = "INSERT INTO TBL_DEV_PLAN(ID, PLAN, ENABLED, CREATED_BY) VALUES(?,?,?,?)";
        int rowsAffected = jdbcTemplate.update(sql, devPlan.getId(), devPlan.getPlan(), devPlan.isEnabled(), devPlan.getCreatedBy());
        if (rowsAffected > 0) {
            return devPlan;
        } else {
            return null;
        }
    }

    @Override
    public DevPlan updateDevPlan(DevPlan devPlan) {
        String sql = "UPDATE TBL_DEV_PLAN SET PLAN = ?, ENABLED = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, devPlan.getPlan(), devPlan.isEnabled(), devPlan.getUpdatedBy(), devPlan.getId());
        if (rowsAffected > 0) {
            return devPlan;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteDevPlan(String id) {
        String sql = "DELETE FROM TBL_DEV_PLAN WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
