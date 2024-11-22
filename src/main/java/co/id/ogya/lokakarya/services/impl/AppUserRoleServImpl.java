package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.appuserrole.AppUserRoleCreateDto;
import co.id.ogya.lokakarya.dto.appuserrole.AppUserRoleDto;
import co.id.ogya.lokakarya.dto.appuserrole.AppUserRoleGetDto;
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
import java.util.Map;

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

    @Override
    public List<AppUserRoleGetDto> getAllAppUserRoleGets() {
        log.info("Fetching all AppUserRole mappings...");
        List<AppUserRoleGetDto> resultList = new ArrayList<>();
        try {
            List<Map<String, Object>> appUserRoles = appUserRoleRepo.getAppUserRoleGets();
            log.debug("Retrieved {} AppUserRole mappings from repository", appUserRoles.size());
            appUserRoles.forEach(map -> resultList.add(AppUserRoleGetDto.mapToDto(map)));
            log.info("Successfully fetched all AppUserRole mappings.");
        } catch (Exception e) {
            log.error("Failed to fetch AppUserRole mappings. Error: {}", e.getMessage(), e);
            throw e;
        }
        return resultList;
    }

    @Override
    public List<AppUserRoleGetDto> getAppUserRoleGetById(String userId) {
        log.info("Fetching AppUserRole mappings for user ID: {}", userId);
        List<AppUserRoleGetDto> resultList = new ArrayList<>();
        try {
            List<Map<String, Object>> appUserRoleGetById = appUserRoleRepo.getAppUserRoleGetById(userId);
            log.debug("Retrieved {} AppUserRole mappings for user ID: {}", appUserRoleGetById.size(), userId);
            appUserRoleGetById.forEach(map -> resultList.add(AppUserRoleGetDto.mapToDto(map)));
            log.info("Successfully fetched AppUserRole mappings for user ID: {}", userId);
        } catch (Exception e) {
            log.error("Failed to fetch AppUserRole mappings for user ID: {}. Error: {}", userId, e.getMessage(), e);
            throw e;
        }
        return resultList;
    }

    @Override
    public List<AppUserRoleDto> getAppUserRoleDtoByUserId(String userId) {
        log.info("Attempting to fetch AppUserRoles for User ID {}", userId);
        List<AppUserRoleDto> listResult = new ArrayList<>();
        try {
            List<AppUserRole> listData = appUserRoleRepo.getAppUserRolesByUserId(userId);
            log.debug("Fetched AppUserRoles for User ID: {}", userId);
            for (AppUserRole data : listData) {
                AppUserRoleDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching AppUserRoles for User ID {} : {}", userId, e.getMessage(), e);
        }
        return listResult;
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
