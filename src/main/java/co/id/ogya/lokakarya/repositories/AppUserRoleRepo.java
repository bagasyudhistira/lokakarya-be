package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.AppUserRole;

import java.util.List;
import java.util.Map;

public interface AppUserRoleRepo {
    List<AppUserRole> getAppUserRoles();
    AppUserRole getAppUserRoleById(String id);
    AppUserRole saveAppUserRole(AppUserRole appUserRole);
    AppUserRole updateAppUserRole(AppUserRole appUserRole);
    Boolean deleteAppUserRole(String id);
    List<Map<String, Object>> getAppUserRoleGets();
    List<Map<String, Object>> getAppUserRoleGetById(String userId);
    List<String> getAppUserRoleNamesById(String userId);
    List<AppUserRole> getAppUserRolesByUserId(String userId);
    String getAppUserRoleByUserIdRoleId(String userId, String roleId);
}
