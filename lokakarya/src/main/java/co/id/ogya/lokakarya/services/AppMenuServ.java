package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.appmenu.AppMenuCreateDto;
import co.id.ogya.lokakarya.dto.appmenu.AppMenuDto;
import co.id.ogya.lokakarya.dto.appmenu.AppMenuUpdateDto;

import java.util.List;

public interface AppMenuServ {
    List<AppMenuDto> getAllAppMenu();
    AppMenuDto getAppMenuById(String id);
    AppMenuDto createAppMenu(AppMenuCreateDto appMenuCreateDto);
    AppMenuDto updateAppMenu(AppMenuUpdateDto appMenuUpdateDto);
    boolean deleteAppMenu(String id);

}
