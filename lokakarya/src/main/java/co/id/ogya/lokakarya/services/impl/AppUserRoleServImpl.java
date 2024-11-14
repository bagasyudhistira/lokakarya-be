package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.appuserrole.*;
import co.id.ogya.lokakarya.services.AppUserRoleServ;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class AppUserRoleServImpl implements AppUserRoleServ {
    @Autowired
    private AppUserRoleRepo appUserRoleRepo;

    @Override
    public List<AppUserRoleDto> getAllAppUserRole() {
        List<AppUserRole> listData = appUserRoleRepo.getAppUserRoles();
        List<AppUserRoleDto> listResult = new ArrayList<>();
        for(AppUserRole data : listData){
            AppUserRoleDto result = convertToDto(data);
            listResult.add(result);
        }
        return listResult;
    }

    @Override
    public AppUserRoleDto getAppUserRoleById(String id) {
        AppUserRole data = appUserRoleRepo.getAppUserRoleById(id);
        AppUserRoleDto result = convertToDto(data);
        return result;
    }

    @Override
    public AppUserRoleDto createAppUserRole(AppUserRoleCreateDto appUserRoleCreateDto) {
        AppUserRole data = convertToEntityCreate(appUserRoleCreateDto);
        AppUserRole result = appUserRoleRepo.saveAppUserRole(data);
        return convertToDto(result);
    }

    @Override
    public AppUserRoleDto updateAppUserRole(AppUserRoleUpdateDto appUserRoleUpdateDto) {
        AppUserRole data = convertToEntityUpdate(appUserRoleUpdateDto);
        AppUserRole result = appUserRoleRepo.updateAppUserRole(data);
        return convertToDto(result);
    }

    @Override
    public boolean deleteAppUserRole(String id) {
        return appUserRoleRepo.deleteAppUserRole(id);
    }

    private AppUserRole convertToEntity(AppUserRoleDto convertObject) {
        AppUserRole result = AppUserRole.builder()
                .id(convertObject.getId())
                .roleId(convertObject.getRoleId())
                .userId(convertObject.getUserId())
                .build();
        return result;
    }

    private AppUserRole convertToEntityCreate(AppUserRoleCreateDto convertObject) {
        AppUserRole result = AppUserRole.builder()
                .id(convertObject.getId())
                .roleId(convertObject.getRoleId())
                .userId(convertObject.getUserId())
                .build();
        return result;
    }

    private AppUserRole convertToEntityUpdate(AppUserRoleUpdateDto convertObject) {
        AppUserRole result = AppUserRole.builder()
                .id(convertObject.getId())
                .roleId(convertObject.getRoleId())
                .userId(convertObject.getUserId())
                .build();
        return result;
    }

    private AppUserRoleDto convertToDto(AppUserRole convertObject) {
        AppUserRoleDto result = AppUserRoleDto.builder()
                .id(convertObject.getId())
                .roleId(convertObject.getRoleId())
                .userId(convertObject.getUserId())
                .build();
        return result;
    }
}
