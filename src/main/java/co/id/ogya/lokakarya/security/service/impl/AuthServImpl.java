package co.id.ogya.lokakarya.security.service.impl;

import co.id.ogya.lokakarya.entities.AppUser;
import co.id.ogya.lokakarya.exceptions.UserException;
import co.id.ogya.lokakarya.repositories.AppUserRepo;
import co.id.ogya.lokakarya.security.dto.AuthDto;
import co.id.ogya.lokakarya.security.dto.AuthGetDto;
import co.id.ogya.lokakarya.security.repositories.AuthRepo;
import co.id.ogya.lokakarya.security.service.AuthServ;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Transactional(rollbackOn = Exception.class)
@Service
public class AuthServImpl implements AuthServ {
    @Autowired
    AuthRepo authRepo;

    @Autowired
    AppUserRepo appUserRepo;

    @Override
    public AuthDto login(String username) throws UserException {
        log.info("Sorching for user by username: {}", username);

        AuthDto user = convertToDto(authRepo.getAppUser(username));
        if (user == null) {
            log.warn("No user found: {}", username);
            throw new UserException("No user found: " + username);
        }

        log.info("User found: {}", user.getUsername());
        return user;
    }

    private AuthDto convertToDto(AppUser convertObject) {
        log.debug("Converting AppUser entity to AuthDTO: {}", convertObject);
        AuthDto result = AuthDto.builder()
                .username(convertObject.getUsername())
                .password(convertObject.getPassword())
                .build();
        return result;
    }

    @Override
    public AuthGetDto authenticateUser() {
        log.info("Retrieving logged-in user information");

        SecurityContext sc = SecurityContextHolder.getContext();
        Authentication auth = sc.getAuthentication();

        if (auth == null) {
            log.error("No authentication found in security context");
            return null;
        }

        String username = auth.getName();
        log.info("Authenticated user: {}", username);

        Map<String, Object> user = appUserRepo.getAppUserByUsername(username);
        AuthGetDto loggedUser = AuthGetDto.mapToDto(user);
        if (user == null) {
            log.error("No user found for username: {}", loggedUser);
        } else {
            log.info("User found: {}", loggedUser);
        }
        return loggedUser;
    }

    @Override
    public String changePassword(String userId, String newPassword) {
        log.info("Attempting to change password for User ID: {}", userId);
        try {
            authRepo.changePassword(userId, newPassword);
            return "Password changed successfully";
        } catch (Exception e) {
            log.error("Error occurred while changing password for User ID: {}: {}", userId, e.getMessage(), e);
            throw e;
        }
    }
}
