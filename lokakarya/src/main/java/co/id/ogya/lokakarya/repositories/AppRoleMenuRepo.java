package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.AppRoleMenu;

import java.util.List;
import java.util.Map;

public interface AppRoleMenuRepo {
    List<AppRoleMenu> getAppRoleMenus();
    AppRoleMenu getAppRoleMenuById(String id);
    AppRoleMenu saveAppRoleMenu(AppRoleMenu appRoleMenu);
    AppRoleMenu updateAppRoleMenu(AppRoleMenu appRoleMenu);
    Boolean deleteAppRoleMenu(String id);
    List<Map<String, Object>> getAppRoleMenuGets();
    List<Map<String, Object>> getAppRoleMenuGetByRoleId(String roleId);
    List<Map<String, Object>> getAppRoleMenuGetByMenuId(String menuId);
}
