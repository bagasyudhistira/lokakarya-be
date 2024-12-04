package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.appuser.*;

import java.util.List;

public interface AppUserServ {
    List<AppUserDto> getAllAppUser();

    AppUserDto getAppUserById(String id);

    List<AppUserGetDto> getAllAppUserGet();

    AppUserGetDto getAppUserGetById(String id);

    AppUserGetDto getAppUserByUsername(String username);

    AppUserGetDto getAppUserByFullName(String fullName);

    AppUserCreateDto createAppUser(AppUserCreateDto appUserCreateDto);

    AppUserDto updateAppUser(AppUserUpdateDto appUserUpdateDto);

    boolean deleteAppUser(String id);

    List<AppUserCommonDto> getAllAppUserCommons();

}
