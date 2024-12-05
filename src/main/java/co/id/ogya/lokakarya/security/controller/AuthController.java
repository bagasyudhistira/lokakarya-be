package co.id.ogya.lokakarya.security.controller;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.approlemenu.AppRoleMenuGetDto;
import co.id.ogya.lokakarya.dto.appuser.AppUserDto;
import co.id.ogya.lokakarya.dto.appuserrole.AppUserRoleGetDto;
import co.id.ogya.lokakarya.exceptions.UserException;
import co.id.ogya.lokakarya.repositories.AppUserRoleRepo;
import co.id.ogya.lokakarya.security.dto.AuthDto;
import co.id.ogya.lokakarya.security.dto.AuthGetDto;
import co.id.ogya.lokakarya.security.dto.AuthPasswordChangeDto;
import co.id.ogya.lokakarya.security.service.AuthServ;
import co.id.ogya.lokakarya.security.util.SecurityConstants;
import co.id.ogya.lokakarya.services.AppRoleMenuServ;
import co.id.ogya.lokakarya.services.AppUserRoleServ;
import co.id.ogya.lokakarya.services.AppUserServ;
import co.id.ogya.lokakarya.utils.ServerResponseList;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController extends ServerResponseList {
    @Autowired
    private AuthServ authServ;

    @Autowired
    private AppUserServ appUserServ;

    @Autowired
    private AppRoleMenuServ appRoleMenuServ;

    @Autowired
    private AppUserRoleRepo appUserRoleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/sign-in")
    public ResponseEntity<String> signInHandler(@RequestBody AuthDto authRequestDto)
            throws BadCredentialsException, UserException {
        log.info("Received sign-in request for username: {}", authRequestDto.getUsername());

        // Validate user credentials and fetch user details
        AuthDto userDetails = authServ.login(authRequestDto.getUsername());


        if (userDetails == null || !passwordEncoder.matches(authRequestDto.getPassword(), userDetails.getPassword())) {
            log.warn("Failed sign-in attempt for username: {}", authRequestDto.getUsername());
            throw new BadCredentialsException("Invalid username or password");
        }

        String token = generateJwtToken(userDetails);

        AuthDto responseDto = AuthDto.builder()
                .username(userDetails.getUsername())
                .build();

        log.info("User signed in successfully: {}", responseDto.getUsername());

        return ResponseEntity.ok()
                .body(token);
    }

    @GetMapping("/authenticate")
    public ResponseEntity<?> authenticateUser() throws UserException {
        log.info("Received request for logged-in user access.");

        AuthGetDto user = authServ.authenticateUser();

        String message = "Logged-in as: " + user.getUsername();
        log.info("Access granted for: {}", user.getUsername());

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    private String generateJwtToken(AuthDto userDetails) {
        SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());

        List<String> roles = appUserRoleRepo.getAppUserRoleNamesById(appUserServ.getAppUserByUsername(userDetails.getUsername()).getId());
        String userId = appUserServ.getAppUserByUsername(userDetails.getUsername()).getId();

        return Jwts.builder()
                .setIssuer("Admin")
                .setSubject("JWT Token")
                .claim("userId", userId)
                .claim("username", userDetails.getUsername())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 30000000))
                .signWith(key)
                .compact();
    }

    @GetMapping("/role/{rolename}")
    public ResponseEntity<?> getAppRoleMenuByRolename(@PathVariable String rolename) {
        log.info("Fetching AppRoleMenu with role: {}", rolename);
        long startTime = System.currentTimeMillis();

        try {
            List<AppRoleMenuGetDto> result = appRoleMenuServ.getAppRoleMenuByRolename(rolename);
            ManagerDto<List<AppRoleMenuGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched AppRoleMenus for role {} in {} ms", rolename, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching AppRoleMenus for role {}: {}", rolename,e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppRoleMenus", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/changepassword")
    public ResponseEntity<?> changePassword(@RequestBody AuthPasswordChangeDto authPasswordChangeDto) {
        log.info("Received change password request.");
        long startTime = System.currentTimeMillis();
        try {
            authPasswordChangeDto.setNewPassword(passwordEncoder.encode(authPasswordChangeDto.getNewPassword()));

            String result = authServ.changePassword(authPasswordChangeDto.getUserId(), authPasswordChangeDto.getNewPassword());
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Password Changed for User ID: {} in {} ms", authPasswordChangeDto.getUserId(), endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);


        } catch (Exception e) {
            log.error("Error fetching change password request", e);
            return new ResponseEntity<>("Failed to fetch change password request", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping ("/resetpassword")
    public ResponseEntity<?> resetPassword(@RequestBody AuthPasswordChangeDto authPasswordChangeDto) {
        log.info("Received change password request.");
        long startTime = System.currentTimeMillis();
        try {
            String generatedPassword = UUID.randomUUID().toString();
            authPasswordChangeDto.setNewPassword(passwordEncoder.encode(generatedPassword));

            authServ.changePassword(authPasswordChangeDto.getUserId(), authPasswordChangeDto.getNewPassword());
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(generatedPassword);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Password Changed for User ID: {} in {} ms", authPasswordChangeDto.getUserId(), endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);


        } catch (Exception e) {
            log.error("Error fetching change password request", e);
            return new ResponseEntity<>("Failed to fetch change password request", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
