package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.appuser.AppUserCreateDto;
import co.id.ogya.lokakarya.dto.appuser.AppUserDto;
import co.id.ogya.lokakarya.dto.appuser.AppUserUpdateDto;

import java.util.List;

public interface AppUserServ {
    List<AppUserDto> getAllAppUser();
    AppUserDto getAppUserById(String id);
    AppUserDto createAppUser(AppUserCreateDto appUserCreateDto);
    AppUserDto updateAppUser(AppUserUpdateDto appUserUpdateDto);
    boolean deleteAppUser(String id);

}
