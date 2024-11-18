//package co.id.ogya.lokakarya.security.service.impl;
//
//import co.id.ogya.lokakarya.entities.AppUser;
//import co.id.ogya.lokakarya.exceptions.UserException;
//import co.id.ogya.lokakarya.security.dto.AuthDto;
//import co.id.ogya.lokakarya.security.repositories.AuthRepo;
//import co.id.ogya.lokakarya.security.service.AuthServ;
//import jakarta.transaction.Transactional;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Transactional(rollbackOn = Exception.class)
//@Service
//public class AuthServImpl implements AuthServ {
//    @Autowired
//    AuthRepo authRepo;
//
//    @Override
//    public AuthDto login(String username) throws UserException {
//        log.info("Searching for user by username: {}", username);
//
//        AuthDto user = convertToDto(authRepo.getAppUser(username));
//        if (user == null) {
//            log.warn("No user found: {}", username);
//            throw new UserException("No user found: " + username);
//        }
//
//        log.info("User found: {}", user.getUsername());
//        return user;
//    }
//
//    private AuthDto convertToDto(AppUser convertObject) {
//        log.debug("Converting AppUser entity to AuthDTO: {}", convertObject);
//        AuthDto result = AuthDto.builder()
//                .id(convertObject.getId())
//                .username(convertObject.getUsername())
//                .password(convertObject.getPassword())
//                .roleId(convertObject.getRoleId())
//                .build();
//        return result;
//    }
//}
