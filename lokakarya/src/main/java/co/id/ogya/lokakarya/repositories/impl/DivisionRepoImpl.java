package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.Division;
import co.id.ogya.lokakarya.repositories.DivisionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DivisionRepoImpl implements DivisionRepo {

    private RowMapper<Division> rowMapper = new BeanPropertyRowMapper<>(Division.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Division> getDivisions() {
        String sql = "SELECT * FROM TBL_DIVISION";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Division getDivisionById(String id) {
        String sql = "SELECT * FROM TBL_DIVISION WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public Division saveDivision(Division division) {
        String sql = "INSERT INTO TBL_DIVISION (ID, DIVISION_NAME, CREATED_BY) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, division.getId(), division.getDivisionName(), division.getCreatedBy());
        if (rowsAffected > 0) {
            return division;
        } else {
            return null;
        }
    }

    @Override
    public Division updateDivision(Division division) {
        String sql = "UPDATE TBL_DIVISION SET DIVISION_NAME = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, division.getDivisionName(), division.getUpdatedBy(), division.getId());
        if (rowsAffected > 0) {
            return division;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteDivision(String id) {
        String sql = "DELETE FROM TBL_DIVISION WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
