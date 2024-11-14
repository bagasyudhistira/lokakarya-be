package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.AppRoleMenu;

import java.util.List;

public interface AppRoleMenuRepo {
    List<AppRoleMenu> getAppRoleMenus();
    AppRoleMenu getAppRoleMenuById(String id);
    AppRoleMenu saveAppRoleMenu(AppRoleMenu appRoleMenu);
    AppRoleMenu updateAppRoleMenu(AppRoleMenu appRoleMenu);
    Boolean deleteAppRoleMenu(String id);
}
