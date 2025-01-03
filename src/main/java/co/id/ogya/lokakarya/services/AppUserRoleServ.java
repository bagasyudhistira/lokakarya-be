package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.appuserrole.AppUserRoleCreateDto;
import co.id.ogya.lokakarya.dto.appuserrole.AppUserRoleDto;
import co.id.ogya.lokakarya.dto.appuserrole.AppUserRoleGetDto;
import co.id.ogya.lokakarya.dto.appuserrole.AppUserRoleUpdateDto;

import java.util.List;

public interface AppUserRoleServ {
    List<AppUserRoleDto> getAllAppUserRole();

    AppUserRoleDto getAppUserRoleById(String id);

    AppUserRoleDto createAppUserRole(AppUserRoleCreateDto appUserRoleCreateDto);

    AppUserRoleDto updateAppUserRole(AppUserRoleUpdateDto appUserRoleUpdateDto);

    boolean deleteAppUserRole(String id);

    List<AppUserRoleGetDto> getAllAppUserRoleGets();

    List<AppUserRoleGetDto> getAppUserRoleGetById(String userId);

    List<AppUserRoleDto> getAppUserRoleDtoByUserId(String userId);

    String getAppUserRoleByUserIdRoleId(String userId, String roleId);
}
