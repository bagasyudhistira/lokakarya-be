package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.AppUser;
import co.id.ogya.lokakarya.repositories.AppUserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class AppUserRepoImpl implements AppUserRepo {

    private final RowMapper<AppUser> rowMapper = new BeanPropertyRowMapper<>(AppUser.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AppUser> getAppUsers() {
        String sql = "SELECT * FROM TBL_APP_USER";
        log.info("Executing query to fetch all AppUsers: {}", sql);
        List<AppUser> appUsers = jdbcTemplate.query(sql, rowMapper);
        log.info("Successfully fetched {} AppUsers", appUsers.size());
        return appUsers;
    }

    @Override
    public AppUser getAppUserById(String id) {
        String sql = "SELECT * FROM TBL_APP_USER WHERE ID = ?";
        log.info("Executing query to fetch AppUser by ID: {} using query: {}", id, sql);
        try {
            AppUser appUser = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Successfully fetched AppUser: {}", appUser);
            return appUser;
        } catch (Exception e) {
            log.error("Error fetching AppUser by ID: {}. Error: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public AppUser saveAppUser(AppUser appUser) {
        appUser.prePersist();
        String sql = "INSERT INTO TBL_APP_USER (ID, USERNAME, FULL_NAME, POSITION, EMAIL_ADDRESS, EMPLOYEE_STATUS, JOIN_DATE, ENABLED, PASSWORD, ROLE_ID, DIVISION_ID, CREATED_BY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        log.info("Executing query to save AppUser: {} using query: {}", appUser, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, appUser.getId(), appUser.getUsername(), appUser.getFullName(), appUser.getPosition(), appUser.getEmailAddress(), appUser.getEmployeeStatus(), appUser.getJoinDate(), appUser.isEnabled(), appUser.getPassword(), appUser.getRoleId(), appUser.getDivisionId(), appUser.getCreatedBy());
            if (rowsAffected > 0) {
                log.info("Successfully saved AppUser: {}", appUser);
                return appUser;
            } else {
                log.warn("Failed to save AppUser: {}", appUser);
                return null;
            }
        } catch (Exception e) {
            log.error("Error saving AppUser: {}. Error: {}", appUser, e.getMessage());
            return null;
        }
    }

    @Override
    public AppUser updateAppUser(AppUser appUser) {
        String sql = "UPDATE TBL_APP_USER SET FULL_NAME = ?, POSITION = ?, EMAIL_ADDRESS = ?, EMPLOYEE_STATUS = ?, JOIN_DATE = ?, ENABLED = ?, PASSWORD = ?, ROLE_ID = ?, DIVISION_ID = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        log.info("Executing query to update AppUser with ID: {} using query: {}", appUser.getId(), sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, appUser.getFullName(), appUser.getPosition(), appUser.getEmailAddress(), appUser.getEmployeeStatus(), appUser.getJoinDate(), appUser.isEnabled(), appUser.getPassword(), appUser.getRoleId(), appUser.getDivisionId(), appUser.getUpdatedBy(), appUser.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated AppUser: {}", appUser);
                return appUser;
            } else {
                log.warn("Failed to update AppUser with ID: {}", appUser.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error updating AppUser with ID: {}. Error: {}", appUser.getId(), e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteAppUser(String id) {
        String sql = "DELETE FROM TBL_APP_USER WHERE ID = ?";
        log.info("Executing query to delete AppUser with ID: {} using query: {}", id, sql);
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted AppUser with ID: {}", id);
                return true;
            } else {
                log.warn("Failed to delete AppUser with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error deleting AppUser with ID: {}. Error: {}", id, e.getMessage());
            return false;
        }
    }
}
