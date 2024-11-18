package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.AppMenu;

import java.util.List;
import java.util.Map;

public interface AppMenuRepo {
    List<AppMenu> getAppMenus();
    AppMenu getAppMenuById(String id);
    AppMenu saveAppMenu(AppMenu appMenu);
    AppMenu updateAppMenu(AppMenu appMenu);
    Boolean deleteAppMenu(String id);
    List<Map<String, Object>> getAppMenuGets();
    Map<String, Object> getAppMenuGetById(String id);
}
