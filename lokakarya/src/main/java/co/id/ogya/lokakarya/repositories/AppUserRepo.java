package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.AppUser;

import java.util.List;
import java.util.Map;

public interface AppUserRepo {
    List<AppUser> getAppUsers();
    AppUser getAppUserById(String id);

    List<Map<String,Object>> getAppUserGets();

    Map<String, Object> getAppUserGetById(String id);

    Map<String, Object> getAppUserByUsername(String username);

    Map<String, Object> getAppUserByFullName(String fullName);

    AppUser saveAppUser(AppUser appUser);
    AppUser updateAppUser(AppUser appUser);
    Boolean deleteAppUser(String id);
}
