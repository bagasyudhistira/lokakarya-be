package co.id.ogya.lokakarya.repositories.impl;

import co.id.ogya.lokakarya.entities.AppUser;
import co.id.ogya.lokakarya.repositories.AppUserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class AppUserRepoImpl implements AppUserRepo {

    private final RowMapper<AppUser> rowMapper = new BeanPropertyRowMapper<>(AppUser.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<AppUser> getAppUsers() {
        String sql = "SELECT * FROM tbl_app_user ORDER BY username";
        try {
            log.info("Fetching all AppUsers");
            List<AppUser> appUsers = jdbcTemplate.query(sql, rowMapper);
            log.info("Fetched {} AppUsers", appUsers.size());
            return appUsers;
        } catch (Exception e) {
            log.error("Error fetching AppUsers: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public AppUser getAppUserById(String id) {
        String sql = "SELECT * FROM tbl_app_user WHERE ID = ?";
        try {
            log.info("Fetching AppUser by ID: {}", id);
            AppUser appUser = jdbcTemplate.queryForObject(sql, rowMapper, id);
            log.info("Fetched AppUser: {}", appUser);
            return appUser;
        } catch (Exception e) {
            log.error("Error fetching AppUser by ID: {}. Error: {}", id, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<AppUser> getAppUsersByDivisionId(String divisionId) {
        String sql = "SELECT * FROM tbl_app_user WHERE DIVISION_ID = ?";
        try {
            log.info("Fetching AppUsers for Division ID: {}", divisionId);
            List<AppUser> appUsers = jdbcTemplate.query(sql, rowMapper, divisionId);
            log.info("Fetched {} AppUsers", appUsers.size());
            return appUsers;
        } catch (Exception e) {
            log.error("Error fetching AppUsers: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> getAppUserGets() {
        String sql = "SELECT au.ID, USERNAME, FULL_NAME, POSITION, EMAIL_ADDRESS, EMPLOYEE_STATUS, " +
                "JOIN_DATE, ENABLED, PASSWORD, DIVISION_ID, DIVISION_NAME " +
                "FROM tbl_app_user au " +
                "LEFT JOIN tbl_division d ON au.DIVISION_ID = d.ID";
        try {
            log.info("Fetching all detailed AppUsers with LEFT JOIN");
            List<Map<String, Object>> appUsers = jdbcTemplate.queryForList(sql);
            log.info("Fetched {} detailed AppUsers", appUsers.size());
            return appUsers;
        } catch (Exception e) {
            log.error("Error fetching detailed AppUsers: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> getAppUserGetsPerPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT au.ID, USERNAME, FULL_NAME, POSITION, EMAIL_ADDRESS, EMPLOYEE_STATUS, " +
                "JOIN_DATE, ENABLED, PASSWORD, DIVISION_ID, DIVISION_NAME " +
                "FROM tbl_app_user au " +
                "LEFT JOIN tbl_division d ON au.DIVISION_ID = d.ID ORDER BY au.ID LIMIT ? OFFSET ?";
        try {
            log.info("Fetching all AppUsers");
            List<Map<String, Object>> appUsers = jdbcTemplate.queryForList(sql, pageSize, offset);
            log.info("Fetched {} AppUsers", appUsers.size());
            return appUsers;
        } catch (Exception e) {
            log.error("Error fetching AppUsers: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Map<String, Object> getAppUserGetById(String id) {
        String sql = "SELECT au.ID, USERNAME, FULL_NAME, POSITION, EMAIL_ADDRESS, EMPLOYEE_STATUS, " +
                "JOIN_DATE, ENABLED, PASSWORD, DIVISION_ID, DIVISION_NAME " +
                "FROM tbl_app_user au " +
                "LEFT JOIN tbl_division d ON au.DIVISION_ID = d.ID " +
                "WHERE au.ID = ?";
        try {
            log.info("Fetching detailed AppUser by ID: {}", id);
            Map<String, Object> appUser = jdbcTemplate.queryForMap(sql, id);
            log.info("Fetched detailed AppUser: {}", appUser);
            return appUser;
        } catch (Exception e) {
            log.error("Error fetching detailed AppUser by ID: {}. Error: {}", id, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Map<String, Object> getAppUserByUsername(String username) {
        String sql = "SELECT au.ID, USERNAME, FULL_NAME, POSITION, EMAIL_ADDRESS, EMPLOYEE_STATUS, " +
                "JOIN_DATE, ENABLED, PASSWORD, DIVISION_ID, DIVISION_NAME " +
                "FROM tbl_app_user au " +
                "LEFT JOIN tbl_division d ON au.DIVISION_ID = d.ID " +
                "WHERE LOWER(USERNAME) = ?";
        try {
            log.info("Fetching detailed AppUser by username: {}", username);

            List<Map<String, Object>> users = jdbcTemplate.queryForList(sql, username);

            if (users.isEmpty()) {
                log.warn("No AppUser found for username: {}", username);
                return null;
            }

            if (users.size() > 1) {
                log.warn("Multiple AppUsers found for username: {}. Returning the first match.", username);
            }

            Map<String, Object> appUser = users.get(0);
            log.info("Fetched detailed AppUser: {}", appUser);
            return appUser;

        } catch (Exception e) {
            log.error("Error fetching detailed AppUser by username: {}. Error: {}", username, e.getMessage(), e);
            return null;
        }
    }


    @Override
    public Map<String, Object> getAppUserByFullName(String fullName) {
        String sql = "SELECT au.ID, USERNAME, FULL_NAME, POSITION, EMAIL_ADDRESS, EMPLOYEE_STATUS, " +
                "JOIN_DATE, ENABLED, PASSWORD, DIVISION_NAME " +
                "FROM tbl_app_user au " +
                "LEFT JOIN tbl_division d ON au.DIVISION_ID = d.ID " +
                "WHERE LOWER(FULL_NAME) LIKE LOWER(CONCAT('%', ?, '%'))";
        try {
            log.info("Fetching detailed AppUser by full name: {}", fullName);

            List<Map<String, Object>> users = jdbcTemplate.queryForList(sql, fullName);

            if (users.isEmpty()) {
                log.warn("No AppUser found for full name: {}", fullName);
                return null;
            }

            if (users.size() > 1) {
                log.warn("Multiple AppUsers found for full name: {}. Returning the first match.", fullName);
            }

            // Return the first match
            Map<String, Object> appUser = users.get(0);
            log.info("Fetched detailed AppUser: {}", appUser);
            return appUser;

        } catch (Exception e) {
            log.error("Error fetching detailed AppUser by full name: {}. Error: {}", fullName, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Map<String, Object> getAppUserByEmail(String email) {
        String sql = "SELECT au.ID, USERNAME, FULL_NAME, POSITION, EMAIL_ADDRESS, EMPLOYEE_STATUS, " +
                "JOIN_DATE, ENABLED, PASSWORD, DIVISION_ID, DIVISION_NAME " +
                "FROM tbl_app_user au " +
                "LEFT JOIN tbl_division d ON au.DIVISION_ID = d.ID " +
                "WHERE LOWER(EMAIL_ADDRESS) = ?";
        try {
            log.info("Fetching detailed AppUser by Email: {}", email);

            List<Map<String, Object>> users = jdbcTemplate.queryForList(sql, email);

            if (users.isEmpty()) {
                log.warn("No AppUser found for Email: {}", email);
                return null;
            }

            if (users.size() > 1) {
                log.warn("Multiple AppUsers found for Email: {}. Returning the first match.", email);
            }

            Map<String, Object> appUser = users.get(0);
            log.info("Fetched detailed AppUser: {}", appUser);
            return appUser;

        } catch (Exception e) {
            log.error("Error fetching detailed AppUser by Email: {}. Error: {}", email, e.getMessage(), e);
            return null;
        }
    }


    @Override
    public AppUser saveAppUser(AppUser appUser) {
        appUser.prePersist();
        String sql = "INSERT INTO tbl_app_user (ID, USERNAME, FULL_NAME, POSITION, EMAIL_ADDRESS, EMPLOYEE_STATUS, JOIN_DATE, ENABLED, PASSWORD, DIVISION_ID, CREATED_BY) " +
                "VALUES (?, LOWER(?), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            log.info("Saving AppUser: {}", appUser);
            int rowsAffected = jdbcTemplate.update(sql, appUser.getId(), appUser.getUsername(), appUser.getFullName(), appUser.getPosition(), appUser.getEmailAddress(),
                    appUser.getEmployeeStatus(), appUser.getJoinDate(), appUser.isEnabled(), passwordEncoder.encode(appUser.getPassword()), appUser.getDivisionId(), appUser.getCreatedBy());

            if (rowsAffected > 0) {
                log.info("Successfully saved AppUser: {}", appUser);
                return appUser;
            } else {
                log.warn("Failed to save AppUser: {}", appUser);
                return null;
            }
        } catch (Exception e) {
            log.error("Error saving AppUser: {}. Error: {}", appUser, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public AppUser updateAppUser(AppUser appUser) {
        String sql = "UPDATE tbl_app_user SET FULL_NAME = ?, POSITION = ?, EMAIL_ADDRESS = ?, EMPLOYEE_STATUS = ?, JOIN_DATE = ?, ENABLED = ?, " +
                "DIVISION_ID = ?, UPDATED_AT = SYSDATE(), UPDATED_BY = ? WHERE ID = ?";
        try {
            log.info("Updating AppUser with ID: {}", appUser.getId());
            int rowsAffected = jdbcTemplate.update(sql, appUser.getFullName(), appUser.getPosition(), appUser.getEmailAddress(), appUser.getEmployeeStatus(),
                    appUser.getJoinDate(), appUser.isEnabled(), appUser.getDivisionId(), appUser.getUpdatedBy(), appUser.getId());
            if (rowsAffected > 0) {
                log.info("Successfully updated AppUser: {}", appUser);
                return appUser;
            } else {
                log.warn("Failed to update AppUser with ID: {}", appUser.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error updating AppUser with ID: {}. Error: {}", appUser.getId(), e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Boolean deleteAppUser(String id) {
        String sql = "DELETE FROM tbl_app_user WHERE ID = ?";
        try {
            log.info("Deleting AppUser with ID: {}", id);
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                log.info("Successfully deleted AppUser with ID: {}", id);
                return true;
            } else {
                log.warn("Failed to delete AppUser with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error deleting AppUser with ID: {}. Error: {}", id, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> getAppUsersCommons() {
        String sql = "SELECT ID, FULL_NAME FROM tbl_app_user";
        log.info("Executing query to fetch AppUsers using query: {}", sql);
        try {
            log.info("Successfully fetched AppUsers");
            return jdbcTemplate.queryForList(sql);
        } catch (Exception e) {
            log.error("Error fetching AppUsers. Error: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Long getAppUsersCount() {
        String sql = "SELECT COUNT(ID) FROM tbl_app_user;";
        try {
            log.info("Fetching total number of AppUsers");
            Long totalUsers = jdbcTemplate.queryForObject(sql, Long.class);
            log.info("Total users: {}", totalUsers);
            return totalUsers;
        } catch (Exception e) {
            log.error("Error fetching AppUsers count: {}", e.getMessage(), e);
            throw e;
        }
    }

}
