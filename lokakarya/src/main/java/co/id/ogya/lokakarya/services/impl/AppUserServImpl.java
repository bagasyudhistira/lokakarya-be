package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.appuser.*;
import co.id.ogya.lokakarya.services.AppUserServ;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class AppUserServImpl implements AppUserServ {
    @Autowired
    private AppUserRepo appUserRepo;

    @Override
    public List<AppUserDto> getAllAppUser() {
        List<AppUser> listData = appUserRepo.getAppUsers();
        List<AppUserDto> listResult = new ArrayList<>();
        for(AppUser data : listData){
            AppUserDto result = convertToDto(data);
            listResult.add(result);
        }
        return listResult;
    }

    @Override
    public AppUserDto getAppUserById(String id) {
        AppUser data = appUserRepo.getAppUserById(id);
        AppUserDto result = convertToDto(data);
        return result;
    }

    @Override
    public AppUserDto createAppUser(AppUserCreateDto appUserCreateDto) {
        AppUser data = convertToEntityCreate(appUserCreateDto);
        AppUser result = appUserRepo.saveAppUser(data);
        return convertToDto(result);
    }

    @Override
    public AppUserDto updateAppUser(AppUserUpdateDto appUserUpdateDto) {
        AppUser data = convertToEntityUpdate(appUserUpdateDto);
        AppUser result = appUserRepo.updateAppUser(data);
        return convertToDto(result);
    }

    @Override
    public boolean deleteAppUser(String id) {
        return appUserRepo.deleteAppUser(id);
    }

    private AppUser convertToEntity(AppUserDto convertObject) {
        AppUser result = AppUser.builder()
                .id(convertObject.getId())
                .username(convertObject.getUsername())
                .fullName(convertObject.getFullName())
                .position(convertObject.getPosition())
                .emailAddress(convertObject.getEmailAddress())
                .employeeStatus(convertObject.getEmployeeStatus())
                .joinDate(convertObject.getJoinDate())
                .enabled(convertObject.isEnabled())
                .password(convertObject.getPassword())
                .roleId(convertObject.getRoleId())
                .divisionId(convertObject.getDivisionId())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private AppUser convertToEntityCreate(AppUserCreateDto convertObject) {
        AppUser result = AppUser.builder()
                .id(convertObject.getId())
                .username(convertObject.getUsername())
                .fullName(convertObject.getFullName())
                .position(convertObject.getPosition())
                .emailAddress(convertObject.getEmailAddress())
                .employeeStatus(convertObject.getEmployeeStatus())
                .joinDate(convertObject.getJoinDate())
                .enabled(convertObject.isEnabled())
                .password(convertObject.getPassword())
                .roleId(convertObject.getRoleId())
                .divisionId(convertObject.getDivisionId())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private AppUser convertToEntityUpdate(AppUserUpdateDto convertObject) {
        AppUser result = AppUser.builder()
                .id(convertObject.getId())
                .username(convertObject.getUsername())
                .fullName(convertObject.getFullName())
                .position(convertObject.getPosition())
                .emailAddress(convertObject.getEmailAddress())
                .employeeStatus(convertObject.getEmployeeStatus())
                .joinDate(convertObject.getJoinDate())
                .enabled(convertObject.isEnabled())
                .password(convertObject.getPassword())
                .roleId(convertObject.getRoleId())
                .divisionId(convertObject.getDivisionId())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private AppUserDto convertToDto(AppUser convertObject) {
        AppUserDto result = AppUserDto.builder()
                .id(convertObject.getId())
                .username(convertObject.getUsername())
                .fullName(convertObject.getFullName())
                .position(convertObject.getPosition())
                .emailAddress(convertObject.getEmailAddress())
                .employeeStatus(convertObject.getEmployeeStatus())
                .joinDate(convertObject.getJoinDate())
                .enabled(convertObject.isEnabled())
                .password(convertObject.getPassword())
                .roleId(convertObject.getRoleId())
                .divisionId(convertObject.getDivisionId())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }
}
