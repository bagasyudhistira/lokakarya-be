package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.AccessDivision;
import co.id.ogya.lokakarya.repositories.AccessDivisionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccessDivisionRepoImpl implements AccessDivisionRepo {

    RowMapper<AccessDivision> rowMapper = new BeanPropertyRowMapper<>(AccessDivision.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AccessDivision> getAccessDivisions() {
        String sql = "SELECT * FROM TBL_ACCESS_DIVISION";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public AccessDivision getAccessDivisionById(String id) {
        String sql = "SELECT * FROM TBL_ACCESS_DIVISION WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public AccessDivision saveAccessDivision(AccessDivision accessDivision) {
        String sql = "INSERT INTO TBL_ACCESS_DIVISION (ID, USER_ID, DIVISION_ID) VALUES (?,?,?)";
        int rowsAffected = jdbcTemplate.update(sql, accessDivision.getId(), accessDivision.getUserId(), accessDivision.getDivisionId());
        if (rowsAffected > 0) {
            return accessDivision;
        } else {
            return null;
        }
    }

    @Override
    public AccessDivision updateAccessDivision(AccessDivision accessDivision) {
        String sql = "UPDATE TBL_ACCESS_DIVISION SET USER_ID = ?, DIVISION_ID = ? WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, accessDivision.getUserId(), accessDivision.getDivisionId(), accessDivision.getId());
        if (rowsAffected > 0) {
            return accessDivision;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteAccessDivision(String id) {
        String sql = "DELETE FROM TBL_ACCESS_DIVISION WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
