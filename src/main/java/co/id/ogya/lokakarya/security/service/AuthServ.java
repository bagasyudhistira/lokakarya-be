package co.id.ogya.lokakarya.security.service;

import co.id.ogya.lokakarya.exceptions.UserException;
import co.id.ogya.lokakarya.security.dto.AuthDto;
import co.id.ogya.lokakarya.security.dto.AuthGetDto;
import co.id.ogya.lokakarya.security.dto.AuthPasswordChangeDto;

public interface AuthServ {
    AuthDto login(String username) throws UserException;

    AuthGetDto authenticateUser();

    String changePassword(String userId, String newPassword);
}
