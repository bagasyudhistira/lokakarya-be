package co.id.ogya.lokakarya.security.repositories.impl;

import co.id.ogya.lokakarya.entities.AppUser;
import co.id.ogya.lokakarya.security.repositories.AuthRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class AuthRepoImpl implements AuthRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<AppUser> rowMapper = new BeanPropertyRowMapper<>(AppUser.class);

    @Override
    public AppUser getAppUser(String username) {
        String sql = "SELECT * FROM TBL_APP_USER WHERE USERNAME = ?";
        log.info("Executing query to fetch AppUser by username: {} using query: {}", username, sql);
        try {
            AppUser appUser = jdbcTemplate.queryForObject(sql, rowMapper, username);
            log.info("Successfully fetched AppUser: {}", appUser);
            return appUser;
        } catch (Exception e) {
            log.error("Error fetching AppUser by username: {}. Error: {}", username, e.getMessage());
            return null;
        }
    }
}
