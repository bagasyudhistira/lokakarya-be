package co.id.ogya.lokakarya.security.controller;

import co.id.ogya.lokakarya.dto.appuserrole.AppUserRoleGetDto;
import co.id.ogya.lokakarya.exceptions.UserException;
import co.id.ogya.lokakarya.security.dto.AuthDto;
import co.id.ogya.lokakarya.security.service.AuthServ;
import co.id.ogya.lokakarya.security.util.SecurityConstants;
import co.id.ogya.lokakarya.services.AppUserRoleServ;
import co.id.ogya.lokakarya.services.AppUserServ;
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

@Slf4j
@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthServ authServ;

    @Autowired
    private AppUserServ appUserServ;

    @Autowired
    private AppUserRoleServ appUserRoleServ;

    @PostMapping("/sign-in")
    public ResponseEntity<String> signInHandler(@RequestBody AuthDto authRequestDto)
            throws BadCredentialsException, UserException {
        log.info("Received sign-in request for username: {}", authRequestDto.getUsername());

        // Validate user credentials and fetch user details
        AuthDto userDetails = authServ.login(authRequestDto.getUsername());

        if (userDetails == null || !authRequestDto.getPassword().equals(userDetails.getPassword())) {
            log.warn("Failed sign-in attempt for username: {}", authRequestDto.getUsername());
            throw new BadCredentialsException("Invalid username or password");
        }

        // Generate JWT token
        String token = generateJwtToken(userDetails);

        // Build the response object
        AuthDto responseDto = AuthDto.builder()
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .roleId(userDetails.getRoleId())
                .build();

        log.info("User signed in successfully: {}", responseDto.getUsername());

        // Return the response with the JWT token in headers
        return ResponseEntity.ok()

                .body(token);
    }

    private String generateJwtToken(AuthDto userDetails) {
        SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
        List<AppUserRoleGetDto> roles = appUserRoleServ.getAppUserRoleGetById(appUserServ.getAppUserByUsername(userDetails.getUsername()).getId());
        return Jwts.builder()
                .setIssuer("Admin")
                .setSubject("JWT Token")
                .claim("username", userDetails.getUsername())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 30000000)) // Set token expiration
                .signWith(key)
                .compact();
    }

}
