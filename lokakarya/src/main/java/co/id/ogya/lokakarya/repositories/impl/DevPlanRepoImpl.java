package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.DevPlan;
import co.id.ogya.lokakarya.repositories.DevPlanRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class DevPlanRepoImpl implements DevPlanRepo {

    private final RowMapper<DevPlan> rowMapper = new BeanPropertyRowMapper<>(DevPlan.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<DevPlan> getDevPlans() {
        String sql = "SELECT * FROM TBL_DEV_PLAN";
        log.info("Executing query to fetch all DevPlans: {}", sql);
        try {
            List<DevPlan> result = jdbcTemplate.query(sql, rowMapper);
            log.info("Successfully fetched {} DevPlans.", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching all DevPlans. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public DevPlan getDevPlanById(String id) {
        String sql = "SELECT * FROM TBL_DEV_PLAN WHERE ID = ?";
        log.info("Executing query to fetch DevPlan by ID: {} using query: {}", id, sql);
        try {
            DevPlan result = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Successfully fetched DevPlan: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error fetching DevPlan by ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public DevPlan saveDevPlan(DevPlan devPlan) {
        devPlan.prePersist();
        String sql = "INSERT INTO TBL_DEV_PLAN(ID, PLAN, ENABLED, CREATED_BY) VALUES(?,?,?,?)";
        log.info("Executing query to save DevPlan: {} using query: {}", devPlan, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, devPlan.getId(), devPlan.getPlan(), devPlan.isEnabled(), devPlan.getCreatedBy());
            if (rowsAffected > 0) {
                log.info("Successfully saved DevPlan: {}", devPlan);
                return devPlan;
            } else {
                log.warn("No rows affected while saving DevPlan: {}", devPlan);
                return null;
            }
        } catch (Exception e) {
            log.error("Error saving DevPlan: {}. Error: {}", devPlan, e.getMessage());
            return null;
        }
    }

    @Override
    public DevPlan updateDevPlan(DevPlan devPlan) {
        String sql = "UPDATE TBL_DEV_PLAN SET PLAN = ?, ENABLED = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        log.info("Executing query to update DevPlan with ID: {} using query: {}", devPlan.getId(), sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, devPlan.getPlan(), devPlan.isEnabled(), devPlan.getUpdatedBy(), devPlan.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated DevPlan: {}", devPlan);
                return devPlan;
            } else {
                log.warn("No rows affected while updating DevPlan with ID: {}", devPlan.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error updating DevPlan with ID: {}. Error: {}", devPlan.getId(), e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteDevPlan(String id) {
        String sql = "DELETE FROM TBL_DEV_PLAN WHERE ID = ?";
        log.info("Executing query to delete DevPlan with ID: {} using query: {}", id, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted DevPlan with ID: {}", id);
                return true;
            } else {
                log.warn("No rows affected while deleting DevPlan with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error deleting DevPlan with ID: {}. Error: {}", id, e.getMessage());
            return false;
        }
    }
}
