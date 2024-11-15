package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.AppUserRole;

import java.util.List;

public interface AppUserRoleRepo {
    List<AppUserRole> getAppUserRoles();
    AppUserRole getAppUserRoleById(String id);
    AppUserRole saveAppUserRole(AppUserRole appUserRole);
    AppUserRole updateAppUserRole(AppUserRole appUserRole);
    Boolean deleteAppUserRole(String id);
}
