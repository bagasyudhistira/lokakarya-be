package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.approle.AppRoleCreateDto;
import co.id.ogya.lokakarya.dto.approle.AppRoleDto;
import co.id.ogya.lokakarya.dto.approle.AppRoleGetDto;
import co.id.ogya.lokakarya.dto.approle.AppRoleUpdateDto;
import co.id.ogya.lokakarya.entities.AppRole;
import co.id.ogya.lokakarya.repositories.AppRoleRepo;
import co.id.ogya.lokakarya.services.AppRoleServ;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class AppRoleServImpl implements AppRoleServ {
    @Autowired
    private AppRoleRepo appRoleRepo;

    @Override
    public List<AppRoleDto> getAllAppRole() {
        log.info("Attempting to fetch all AppRoles");
        List<AppRoleDto> listResult = new ArrayList<>();
        try {
            List<AppRole> listData = appRoleRepo.getAppRoles();
            log.debug("Fetched {} AppRoles from repository", listData.size());
            for (AppRole data : listData) {
                log.info(data.toString());
                AppRoleDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all AppRoles: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public AppRoleDto getAppRoleById(String id) {
        log.info("Attempting to fetch AppRole by ID: {}", id);
        AppRoleDto result = null;
        try {
            AppRole data = appRoleRepo.getAppRoleById(id);
            result = convertToDto(data);
            log.debug("Fetched AppRole: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching AppRole by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public AppRoleDto createAppRole(AppRoleCreateDto appRoleCreateDto) {
        log.info("Attempting to create a new AppRole with data: {}", appRoleCreateDto);
        AppRoleDto result = null;
        try {
            AppRole data = convertToEntityCreate(appRoleCreateDto);
            AppRole savedData = appRoleRepo.saveAppRole(data);
            result = convertToDto(savedData);
            log.info("Successfully created AppRole: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while creating AppRole: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public AppRoleDto updateAppRole(AppRoleUpdateDto appRoleUpdateDto) {
        log.info("Attempting to update AppRole with data: {}", appRoleUpdateDto);
        AppRoleDto result = null;
        try {
            AppRole data = convertToEntityUpdate(appRoleUpdateDto);
            AppRole updatedData = appRoleRepo.updateAppRole(data);
            result = convertToDto(updatedData);
            log.info("Successfully updated AppRole: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while updating AppRole: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean deleteAppRole(String id) {
        log.info("Attempting to delete AppRole with ID: {}", id);
        boolean isDeleted = false;
        try {
            isDeleted = appRoleRepo.deleteAppRole(id);
            if (isDeleted) {
                log.info("Successfully deleted AppRole with ID: {}", id);
            } else {
                log.warn("Failed to delete AppRole with ID: {}. It might not exist.", id);
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting AppRole with ID {}: {}", id, e.getMessage(), e);
        }
        return isDeleted;
    }

    private AppRole convertToEntity(AppRoleDto convertObject) {
        log.debug("Converting AppRoleDto to entity: {}", convertObject);
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
        log.debug("Converting AppRoleCreateDto to entity: {}", convertObject);
        AppRole result = AppRole.builder()
                .rolename(convertObject.getRolename())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private AppRole convertToEntityUpdate(AppRoleUpdateDto convertObject) {
        log.debug("Converting AppRoleUpdateDto to entity: {}", convertObject);
        AppRole result = AppRole.builder()
                .id(convertObject.getId())
                .rolename(convertObject.getRolename())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private AppRoleDto convertToDto(AppRole convertObject) {
        log.debug("Converting AppRole entity to DTO: {}", convertObject);
        AppRoleDto result = AppRoleDto.builder()
                .id(convertObject.getId())
                .rolename(convertObject.getRolename())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }
}
