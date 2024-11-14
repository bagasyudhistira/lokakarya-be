package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.AppMenu;

import java.util.List;

public interface AppMenuRepo {
    List<AppMenu> getAppMenus();
    AppMenu getAppMenuById(String id);
    AppMenu saveAppMenu(AppMenu appMenu);
    AppMenu updateAppMenu(AppMenu appMenu);
    Boolean deleteAppMenu(String id);
}
