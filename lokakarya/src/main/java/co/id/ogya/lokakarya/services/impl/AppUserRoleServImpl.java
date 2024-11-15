package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.appuserrole.AppUserRoleCreateDto;
import co.id.ogya.lokakarya.dto.appuserrole.AppUserRoleDto;
import co.id.ogya.lokakarya.dto.appuserrole.AppUserRoleUpdateDto;
import co.id.ogya.lokakarya.entities.AppUserRole;
import co.id.ogya.lokakarya.repositories.AppUserRoleRepo;
import co.id.ogya.lokakarya.services.AppUserRoleServ;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class AppUserRoleServImpl implements AppUserRoleServ {
    @Autowired
    private AppUserRoleRepo appUserRoleRepo;

    @Override
    public List<AppUserRoleDto> getAllAppUserRole() {
        log.info("Attempting to fetch all AppUserRoles");
        List<AppUserRoleDto> listResult = new ArrayList<>();
        try {
            List<AppUserRole> listData = appUserRoleRepo.getAppUserRoles();
            log.debug("Fetched {} AppUserRoles from repository", listData.size());
            for (AppUserRole data : listData) {
                AppUserRoleDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all AppUserRoles: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public AppUserRoleDto getAppUserRoleById(String id) {
        log.info("Attempting to fetch AppUserRole by ID: {}", id);
        AppUserRoleDto result = null;
        try {
            AppUserRole data = appUserRoleRepo.getAppUserRoleById(id);
            result = convertToDto(data);
            log.debug("Fetched AppUserRole: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching AppUserRole by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public AppUserRoleDto createAppUserRole(AppUserRoleCreateDto appUserRoleCreateDto) {
        log.info("Attempting to create a new AppUserRole with data: {}", appUserRoleCreateDto);
        AppUserRoleDto result = null;
        try {
            AppUserRole data = convertToEntityCreate(appUserRoleCreateDto);
            AppUserRole savedData = appUserRoleRepo.saveAppUserRole(data);
            result = convertToDto(savedData);
            log.info("Successfully created AppUserRole: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while creating AppUserRole: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public AppUserRoleDto updateAppUserRole(AppUserRoleUpdateDto appUserRoleUpdateDto) {
        log.info("Attempting to update AppUserRole with data: {}", appUserRoleUpdateDto);
        AppUserRoleDto result = null;
        try {
            AppUserRole data = convertToEntityUpdate(appUserRoleUpdateDto);
            AppUserRole updatedData = appUserRoleRepo.updateAppUserRole(data);
            result = convertToDto(updatedData);
            log.info("Successfully updated AppUserRole: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while updating AppUserRole: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean deleteAppUserRole(String id) {
        log.info("Attempting to delete AppUserRole with ID: {}", id);
        boolean isDeleted = false;
        try {
            isDeleted = appUserRoleRepo.deleteAppUserRole(id);
            if (isDeleted) {
                log.info("Successfully deleted AppUserRole with ID: {}", id);
            } else {
                log.warn("Failed to delete AppUserRole with ID: {}. It might not exist.", id);
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting AppUserRole with ID {}: {}", id, e.getMessage(), e);
        }
        return isDeleted;
    }

    private AppUserRole convertToEntity(AppUserRoleDto convertObject) {
        log.debug("Converting AppUserRoleDto to entity: {}", convertObject);
        AppUserRole result = AppUserRole.builder()
                .id(convertObject.getId())
                .roleId(convertObject.getRoleId())
                .userId(convertObject.getUserId())
                .build();
        return result;
    }

    private AppUserRole convertToEntityCreate(AppUserRoleCreateDto convertObject) {
        log.debug("Converting AppUserRoleCreateDto to entity: {}", convertObject);
        AppUserRole result = AppUserRole.builder()
                .id(convertObject.getId())
                .roleId(convertObject.getRoleId())
                .userId(convertObject.getUserId())
                .build();
        return result;
    }

    private AppUserRole convertToEntityUpdate(AppUserRoleUpdateDto convertObject) {
        log.debug("Converting AppUserRoleUpdateDto to entity: {}", convertObject);
        AppUserRole result = AppUserRole.builder()
                .id(convertObject.getId())
                .roleId(convertObject.getRoleId())
                .userId(convertObject.getUserId())
                .build();
        return result;
    }

    private AppUserRoleDto convertToDto(AppUserRole convertObject) {
        log.debug("Converting AppUserRole entity to DTO: {}", convertObject);
        AppUserRoleDto result = AppUserRoleDto.builder()
                .id(convertObject.getId())
                .roleId(convertObject.getRoleId())
                .userId(convertObject.getUserId())
                .build();
        return result;
    }
}
