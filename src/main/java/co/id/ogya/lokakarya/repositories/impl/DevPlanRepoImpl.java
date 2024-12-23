package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.DevPlan;
import co.id.ogya.lokakarya.entities.Division;
import co.id.ogya.lokakarya.entities.GroupAchievement;
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
        String sql = "SELECT * FROM tbl_dev_plan";
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
    public List<DevPlan> getDevPlansPerPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM tbl_dev_plan ORDER BY PLAN ASC LIMIT ? OFFSET ?";
        log.info("Executing query to fetch all DevPlans for page {} with maximum {} entries", page, pageSize);
        try {
            List<DevPlan> result = jdbcTemplate.query(sql, rowMapper, pageSize, offset);
            log.info("Successfully fetched DevPlans for Page {} ({} entries)", page, result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching divisions. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public DevPlan getDevPlanById(String id) {
        String sql = "SELECT * FROM tbl_dev_plan " +
                "WHERE ID = ?";
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
        String sql = "INSERT INTO tbl_dev_plan(ID, PLAN, ENABLED, CREATED_BY) VALUES(?,?,?,?)";
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
        String sql = "UPDATE tbl_dev_plan SET PLAN = ?, ENABLED = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? " +
                "WHERE ID = ?";
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
        String sql = "DELETE FROM tbl_dev_plan " +
                "WHERE ID = ?";
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

    @Override
    public DevPlan getDevPlanByName(String planName) {
        String sql = "SELECT * FROM tbl_dev_plan WHERE LOWER(PLAN) = LOWER(?)";
        log.info("Executing query to fetch DevPlan by Plan Name: {} using query: {}", planName, sql);
        try {
            DevPlan result = jdbcTemplate.queryForObject(sql, rowMapper, planName);
            log.info("Successfully fetched DevPlan: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error fetching DevPlan by Plan Name: {}. Error: {}", planName, e.getMessage());
            return null;
        }
    }

    @Override
    public Long countDevPlans() {
        String sql = "SELECT COUNT(ID) FROM tbl_dev_plan";
        log.info("Executing query to count DevPlans: {}", sql);
        try {
            Long total = jdbcTemplate.queryForObject(sql, Long.class);
            log.info("Total DevPlans: {}", total);
            return total;
        } catch (Exception e) {
            log.error("Error counting DevPlans: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<DevPlan> sortDevPlans(String order, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM tbl_dev_plan ORDER BY PLAN " + order + " LIMIT ? OFFSET ?";
        log.info("Executing query to sort DevPlans order {} for page {} with maximum {} entries : {}", order, page, pageSize, sql);
        try {
            List<DevPlan> result = jdbcTemplate.query(sql, rowMapper, pageSize, offset);
            log.info("Successfully sorted DevPlans order {} for Page {} ({} entries)", order, page, result.size());
            return result;
        } catch (Exception e) {
            log.error("Error sorting DevPlans. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<DevPlan> sorchDevPlans(String keyword, String column, String order, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM tbl_dev_plan WHERE LOWER(PLAN) LIKE LOWER(CONCAT('%', COALESCE(?, ''), '%')) ORDER BY " + column + " " + order + " LIMIT ? OFFSET ?";
        log.info("Executing query to sorch DevPlans using keyword: {} for page {} with maximum {} entries : {}", keyword, page, pageSize, sql);
        try {
            List<DevPlan> result = jdbcTemplate.query(sql, rowMapper, keyword, pageSize, offset);
            log.info("Successfully sorched DevPlans using keyword: {} for Page {} ({} entries)", keyword, page, result.size());
            return result;
        } catch (Exception e) {
            log.error("Error sorching DevPlans. Error: {}", e.getMessage());
            throw e;
        }
    }
}
