package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.AppRole;

import java.util.List;

public interface AppRoleRepo {
    List<AppRole> getAppRoles();

    AppRole getAppRoleById(String id);

    AppRole saveAppRole(AppRole appRole);

    AppRole updateAppRole(AppRole appRole);

    Boolean deleteAppRole(String id);
}
