package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.DevPlan;
import co.id.ogya.lokakarya.entities.TechnicalSkill;
import co.id.ogya.lokakarya.repositories.TechnicalSkillRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class TechnicalSkillRepoImpl implements TechnicalSkillRepo {

    private final RowMapper<TechnicalSkill> rowMapper = new BeanPropertyRowMapper<>(TechnicalSkill.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TechnicalSkill> getTechnicalSkills() {
        String sql = "SELECT * FROM tbl_technical_skill";
        log.info("Executing query to fetch all TechnicalSkills: {}", sql);
        try {
            List<TechnicalSkill> result = jdbcTemplate.query(sql, rowMapper);
            log.info("Successfully fetched {} TechnicalSkills.", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error while fetching all TechnicalSkills. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<TechnicalSkill> getTechnicalSkillsPerPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM tbl_technical_skill ORDER BY TECHNICAL_SKILL ASC LIMIT ? OFFSET ?";
        log.info("Executing query to fetch all TechnicalSkills for page {} with maximum {} entries", page, pageSize);
        try {
            List<TechnicalSkill> result = jdbcTemplate.query(sql, rowMapper, pageSize, offset);
            log.info("Successfully fetched TechnicalSkills for Page {} ({} entries)", page, result.size());
            return result;
        } catch (Exception e) {
            log.error("Error fetching TechnicalSkills. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public TechnicalSkill getTechnicalSkillById(String id) {
        String sql = "SELECT * FROM tbl_technical_skill " +
                "WHERE ID = ?";
        log.info("Executing query to fetch TechnicalSkill by ID: {}. Query: {}", id, sql);
        try {
            TechnicalSkill result = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Successfully fetched TechnicalSkill: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error while fetching TechnicalSkill with ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public TechnicalSkill saveTechnicalSkill(TechnicalSkill technicalSkill) {
        technicalSkill.prePersist();
        String sql = "INSERT INTO tbl_technical_skill (ID, TECHNICAL_SKILL, ENABLED, CREATED_BY) VALUES (?, ?, ?, ?)";
        log.info("Executing query to save TechnicalSkill: {}. Query: {}", technicalSkill, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, technicalSkill.getId(), technicalSkill.getTechnicalSkill(), technicalSkill.isEnabled(), technicalSkill.getCreatedBy());
            if (rowsAffected > 0) {
                log.info("Successfully saved TechnicalSkill: {}", technicalSkill);
                return technicalSkill;
            } else {
                log.warn("No rows affected while saving TechnicalSkill: {}", technicalSkill);
                return null;
            }
        } catch (Exception e) {
            log.error("Error while saving TechnicalSkill: {}. Error: {}", technicalSkill, e.getMessage());
            return null;
        }
    }

    @Override
    public TechnicalSkill updateTechnicalSkill(TechnicalSkill technicalSkill) {
        String sql = "UPDATE tbl_technical_skill SET TECHNICAL_SKILL = ?, ENABLED = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? " +
                "WHERE ID = ?";
        log.info("Executing query to update TechnicalSkill with ID: {}. Query: {}", technicalSkill.getId(), sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, technicalSkill.getTechnicalSkill(), technicalSkill.isEnabled(), technicalSkill.getUpdatedBy(), technicalSkill.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated TechnicalSkill: {}", technicalSkill);
                return technicalSkill;
            } else {
                log.warn("No rows affected while updating TechnicalSkill with ID: {}", technicalSkill.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error while updating TechnicalSkill with ID: {}. Error: {}", technicalSkill.getId(), e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteTechnicalSkill(String id) {
        String sql = "DELETE FROM tbl_technical_skill " +
                "WHERE ID = ?";
        log.info("Executing query to delete TechnicalSkill with ID: {}. Query: {}", id, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted TechnicalSkill with ID: {}", id);
                return true;
            } else {
                log.warn("No rows affected while deleting TechnicalSkill with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error while deleting TechnicalSkill with ID: {}. Error: {}", id, e.getMessage());
            return false;
        }
    }

    @Override
    public TechnicalSkill getTechnicalSkillByName(String technicalSkillName) {
        String sql = "SELECT * FROM tbl_technical_skill WHERE LOWER(TECHNICAL_SKILL) = LOWER(?)";
        log.info("Executing query to fetch TechnicalSkill by ID: {}. Query: {}", technicalSkillName, sql);
        try {
            TechnicalSkill result = jdbcTemplate.queryForObject(sql, rowMapper, technicalSkillName);
            log.info("Successfully fetched TechnicalSkill: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error while fetching TechnicalSkill with Technical Skill: {}. Error: {}", technicalSkillName, e.getMessage());
            return null;
        }
    }

    @Override
    public Long countTechnicalSkills() {
        String sql = "SELECT COUNT(ID) FROM tbl_technical_skill";
        log.info("Executing query to count TechnicalSkill: {}", sql);
        try {
            Long total = jdbcTemplate.queryForObject(sql, Long.class);
            log.info("Total TechnicalSkills: {}", total);
            return total;
        } catch (Exception e) {
            log.error("Error counting TechnicalSkills: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<TechnicalSkill> sortTechnicalSkills(String order, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM tbl_technical_skill ORDER BY TECHNICAL_SKILL " + order + " LIMIT ? OFFSET ?";
        log.info("Executing query to sort TechnicalSkills order {} for page {} with maximum {} entries : {}", order, page, pageSize, sql);
        try {
            List<TechnicalSkill> result = jdbcTemplate.query(sql, rowMapper, pageSize, offset);
            log.info("Successfully sorted TechnicalSkills order {} for Page {} ({} entries)", order, page, result.size());
            return result;
        } catch (Exception e) {
            log.error("Error sorching TechnicalSkills. Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<TechnicalSkill> sorchTechnicalSkills(String keyword, String column, String order, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM tbl_technical_skill WHERE LOWER(TECHNICAL_SKILL) LIKE LOWER(CONCAT('%', COALESCE(?, ''), '%')) ORDER BY " + column + " " + order + " LIMIT ? OFFSET ?";
        log.info("Executing query to sorch TechnicalSkills using keyword: {} for page {} with maximum {} entries : {}", keyword, page, pageSize, sql);
        try {
            List<TechnicalSkill> result = jdbcTemplate.query(sql, rowMapper, keyword, pageSize, offset);
            log.info("Successfully sorched TechnicalSkills using keyword: {} for Page {} ({} entries)", keyword, page, result.size());
            return result;
        } catch (Exception e) {
            log.error("Error sorching TechnicalSkills. Error: {}", e.getMessage());
            throw e;
        }
    }
}
