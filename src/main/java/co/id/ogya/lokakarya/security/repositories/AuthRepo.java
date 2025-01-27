package co.id.ogya.lokakarya.security.repositories;

import co.id.ogya.lokakarya.entities.AppUser;

public interface AuthRepo {
    AppUser getAppUser(String username);

    Integer changePassword(String userId, String newPassword);
}
