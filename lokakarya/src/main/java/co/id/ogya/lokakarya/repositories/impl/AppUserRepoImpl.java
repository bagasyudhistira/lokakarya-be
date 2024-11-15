package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.AppUser;
import co.id.ogya.lokakarya.repositories.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppUserRepoImpl implements AppUserRepo {

    private RowMapper<AppUser> rowMapper = new BeanPropertyRowMapper<>(AppUser.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AppUser> getAppUsers() {
        String sql = "SELECT * FROM TBL_APP_USER";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public AppUser getAppUserById(String id) {
        String sql = "SELECT * FROM TBL_APP_USER WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public AppUser saveAppUser(AppUser appUser) {
        String sql = "INSERT INTO TBL_APP_USER (ID, USERNAME, FULL_NAME, POSITION, EMAIL_ADDRESS, EMPLOYEE_STATUS, JOIN_DATE, ENABLED, PASSWORD, ROLE_ID, DIVISION_ID, CREATED_BY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, appUser.getId(), appUser.getUsername(), appUser.getFullName(), appUser.getPosition(), appUser.getEmailAddress(), appUser.getEmployeeStatus(), appUser.getJoinDate(), appUser.isEnabled(), appUser.getPassword(), appUser.getRoleId(), appUser.getDivisionId(), appUser.getCreatedBy());
        if (rowsAffected > 0) {
            return appUser;
        } else {
            return null;
        }
    }

    @Override
    public AppUser updateAppUser(AppUser appUser) {
        String sql = "UPDATE TBL_APP_USER SET FULL_NAME = ?, POSITION = ?, EMAIL_ADDRESS = ?, EMPLOYEE_STATUS = ?, JOIN_DATE = ?, ENABLED = ?, PASSWORD = ?, ROLE_ID = ?, DIVISION_ID = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, appUser.getFullName(), appUser.getPosition(), appUser.getEmailAddress(), appUser.getEmployeeStatus(), appUser.getJoinDate(), appUser.isEnabled(), appUser.getPassword(), appUser.getRoleId(), appUser.getDivisionId(), appUser.getUpdatedBy(), appUser.getId());
        if (rowsAffected > 0) {
            return appUser;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteAppUser(String id) {
        String sql = "DELETE FROM TBL_APP_USER WHERE ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
