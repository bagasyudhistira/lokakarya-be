package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.appuser.*;

import java.util.List;

public interface AppUserServ {
    List<AppUserDto> getAllAppUser();

    AppUserDto getAppUserById(String id);

    List<AppUserDto> getAppUserByDivisionId(String divisionId);

    List<AppUserGetDto> getAllAppUserGet();

    List<AppUserGetDto> getAllAppUserGetPerPage(int page, int pageSize);

    AppUserGetDto getAppUserGetById(String id);

    AppUserGetDto getAppUserByUsername(String username);

    AppUserGetDto getAppUserByFullName(String fullName);

    AppUserGetDto getAppUserByEmail(String email);

    AppUserCreateDto createAppUser(AppUserCreateDto appUserCreateDto);

    AppUserDto updateAppUser(AppUserUpdateDto appUserUpdateDto);

    boolean deleteAppUser(String id);

    List<AppUserCommonDto> getAllAppUserCommons();

    Long getAllAppUsersCount();
}
