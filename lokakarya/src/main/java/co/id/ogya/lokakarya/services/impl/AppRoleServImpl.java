package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.approle.AppRoleCreateDto;
import co.id.ogya.lokakarya.dto.approle.AppRoleDto;
import co.id.ogya.lokakarya.dto.approle.AppRoleUpdateDto;
import co.id.ogya.lokakarya.services.AppRoleServ;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class AppRoleServImpl implements AppRoleServ {
    @Autowired
    private AppRoleRepo appRoleRepo;

    @Override
    public List<AppRoleDto> getAllAppRole() {
        List<AppRole> listData = appRoleRepo.getAppRoles();
        List<AppRoleDto> listResult = new ArrayList<>();
        for(AppRole data : listData){
            AppRoleDto result = convertToDto(data);
            listResult.add(result);
        }
        return listResult;
    }

    @Override
    public AppRoleDto getAppRoleById(String id) {
        AppRole data = appRoleRepo.getAppRoleById(id);
        AppRoleDto result = convertToDto(data);
        return result;
    }

    @Override
    public AppRoleDto createAppRole(AppRoleCreateDto appRoleCreateDto) {
        AppRole data = convertToEntityCreate(appRoleCreateDto);
        AppRole result = appRoleRepo.saveAppRole(data);
        return convertToDto(result);
    }

    @Override
    public AppRoleDto updateAppRole(AppRoleUpdateDto appRoleUpdateDto) {
        AppRole data = convertToEntityUpdate(appRoleUpdateDto);
        AppRole result = appRoleRepo.updateAppRole(data);
        return convertToDto(result);
    }

    @Override
    public boolean deleteAppRole(String id) {
        return appRoleRepo.deleteAppRole(id);
    }

    private AppRole convertToEntity(AppRoleDto convertObject) {
        AppRole result = AppRole.builder()
                .id(convertObject.getId())
                .rolename(convertObject.getRolename())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private AppRole convertToEntityCreate(AppRoleCreateDto convertObject) {
        AppRole result = AppRole.builder()
                .id(convertObject.getId())
                .rolename(convertObject.getRolename())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private AppRole convertToEntityUpdate(AppRoleUpdateDto convertObject) {
        AppRole result = AppRole.builder()
                .id(convertObject.getId())
                .rolename(convertObject.getRolename())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private AppRoleDto convertToDto(AppRole convertObject) {
        AppRoleDto result = AppRoleDto.builder()
                .id(convertObject.getId())
                .rolename(convertObject.getRolename())
                .build();
        return result;
    }
}
