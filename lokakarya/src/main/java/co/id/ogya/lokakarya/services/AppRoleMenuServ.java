package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.approlemenu.AppRoleMenuCreateDto;
import co.id.ogya.lokakarya.dto.approlemenu.AppRoleMenuDto;
import co.id.ogya.lokakarya.dto.approlemenu.AppRoleMenuUpdateDto;

import java.util.List;

public interface AppRoleMenuServ {
    List<AppRoleMenuDto> getAllAppRoleMenu();
    AppRoleMenuDto getAppRoleMenuById(String id);
    AppRoleMenuDto createAppRoleMenu(AppRoleMenuCreateDto AppRoleMenuCreateDto);
    AppRoleMenuDto updateAppRoleMenu(AppRoleMenuUpdateDto AppRoleMenuUpdateDto);
    boolean deleteAppRoleMenu(String id);

}
