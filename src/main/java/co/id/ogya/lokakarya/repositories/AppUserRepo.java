package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.AppUser;

import java.util.List;
import java.util.Map;

public interface AppUserRepo {
    List<AppUser> getAppUsers();

    AppUser getAppUserById(String id);

    List<AppUser> getAppUsersByDivisionId(String divisionId);

    List<Map<String,Object>> getAppUserGets();

    List<Map<String, Object>> getAppUserGetsPerPage(int page, int pageSize);

    Map<String, Object> getAppUserGetById(String id);

    Map<String, Object> getAppUserByUsername(String username);

    Map<String, Object> getAppUserByFullName(String fullName);

    Map<String, Object> getAppUserByEmail(String email);

    AppUser saveAppUser(AppUser appUser);

    AppUser updateAppUser(AppUser appUser);

    Boolean deleteAppUser(String id);

    List<Map<String,Object>> getAppUsersCommons();

    Long countAppUsers();

    List<Map<String, Object>> sortAppUserGetsOrderBy(String column, String order, int page, int pageSize);
}
