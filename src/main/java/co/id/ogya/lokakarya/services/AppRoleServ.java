package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.approle.AppRoleCreateDto;
import co.id.ogya.lokakarya.dto.approle.AppRoleDto;
import co.id.ogya.lokakarya.dto.approle.AppRoleUpdateDto;

import java.util.List;

public interface AppRoleServ {
    List<AppRoleDto> getAllAppRole();

    AppRoleDto getAppRoleById(String id);

    AppRoleDto createAppRole(AppRoleCreateDto appRoleCreateDto);

    AppRoleDto updateAppRole(AppRoleUpdateDto appRoleUpdateDto);

    boolean deleteAppRole(String id);

}
