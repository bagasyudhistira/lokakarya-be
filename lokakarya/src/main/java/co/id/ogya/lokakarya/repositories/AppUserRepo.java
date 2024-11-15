package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.AppUser;

import java.util.List;

public interface AppUserRepo {
    List<AppUser> getAppUsers();
    AppUser getAppUserById(String id);
    AppUser saveAppUser(AppUser appUser);
    AppUser updateAppUser(AppUser appUser);
    Boolean deleteAppUser(String id);
}
