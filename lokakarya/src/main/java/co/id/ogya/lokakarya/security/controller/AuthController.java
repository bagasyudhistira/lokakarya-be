//package co.id.ogya.lokakarya.security.controller;
//
//import co.id.ogya.lokakarya.exceptions.UserException;
//import co.id.ogya.lokakarya.security.dto.AuthDto;
//import co.id.ogya.lokakarya.security.service.AuthServ;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@Validated
//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//    @Autowired
//    private AuthServ authServ;
//
//    @GetMapping("/sign-in")
//    public ResponseEntity<AuthDto> signInHandler(Authentication auth) throws BadCredentialsException, UserException {
//        log.info("Received sign-in request for username: {}", auth.getName());
//
//        AuthDto customer = authServ.login(auth.getName());
//
//        if (customer != null) {
//            log.info("User signed in successfully with username: {}", customer.getUsername());
//            return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
//        }
//
//        log.warn("Failed sign-in attempt for username: {}", auth.getName());
//        throw new BadCredentialsException("Invalid Name or password");
//    }
//
//
//}
