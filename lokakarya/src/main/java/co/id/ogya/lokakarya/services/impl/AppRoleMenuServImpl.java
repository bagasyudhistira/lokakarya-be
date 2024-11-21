package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.approlemenu.AppRoleMenuCreateDto;
import co.id.ogya.lokakarya.dto.approlemenu.AppRoleMenuDto;
import co.id.ogya.lokakarya.dto.approlemenu.AppRoleMenuGetDto;
import co.id.ogya.lokakarya.dto.approlemenu.AppRoleMenuUpdateDto;
import co.id.ogya.lokakarya.entities.AppRoleMenu;
import co.id.ogya.lokakarya.repositories.AppRoleMenuRepo;
import co.id.ogya.lokakarya.services.AppRoleMenuServ;
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
public class AppRoleMenuServImpl implements AppRoleMenuServ {
    @Autowired
    private AppRoleMenuRepo appRoleMenuRepo;

    @Override
    public List<AppRoleMenuDto> getAllAppRoleMenu() {
        log.info("Attempting to fetch all AppRoleMenus");
        List<AppRoleMenuDto> listResult = new ArrayList<>();
        try {
            List<AppRoleMenu> listData = appRoleMenuRepo.getAppRoleMenus();
            log.debug("Fetched {} AppRoleMenus from repository", listData.size());
            for (AppRoleMenu data : listData) {
                AppRoleMenuDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all AppRoleMenus: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public AppRoleMenuDto getAppRoleMenuById(String id) {
        log.info("Attempting to fetch AppRoleMenu by ID: {}", id);
        AppRoleMenuDto result = null;
        try {
            AppRoleMenu data = appRoleMenuRepo.getAppRoleMenuById(id);
            result = convertToDto(data);
            log.debug("Fetched AppRoleMenu: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching AppRoleMenu by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<AppRoleMenuGetDto> getAppRoleMenuByRolename(String rolename) {
        log.info("Attempting to fetch AppRoleMenu by role: {}", rolename);
        List<AppRoleMenuGetDto> listResult = new ArrayList<>();
        try {
            List<Map<String,Object>> listData = appRoleMenuRepo.getAppRoleMenuByRolename(rolename);
            log.debug("Fetched {} AppRoleMenus from repository", listData.size());
            for (Map<String,Object> data : listData) {
                AppRoleMenuGetDto result = AppRoleMenuGetDto.mapToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching AppRoleMenu by role {}: {}", rolename, e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public AppRoleMenuDto createAppRoleMenu(AppRoleMenuCreateDto appRoleMenuCreateDto) {
        log.info("Attempting to create a new AppRoleMenu with data: {}", appRoleMenuCreateDto);
        AppRoleMenuDto result = null;
        try {
            AppRoleMenu data = convertToEntityCreate(appRoleMenuCreateDto);
            AppRoleMenu savedData = appRoleMenuRepo.saveAppRoleMenu(data);
            result = convertToDto(savedData);
            log.info("Successfully created AppRoleMenu: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while creating AppRoleMenu: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public AppRoleMenuDto updateAppRoleMenu(AppRoleMenuUpdateDto appRoleMenuUpdateDto) {
        log.info("Attempting to update AppRoleMenu with data: {}", appRoleMenuUpdateDto);
        AppRoleMenuDto result = null;
        try {
            AppRoleMenu data = convertToEntityUpdate(appRoleMenuUpdateDto);
            AppRoleMenu updatedData = appRoleMenuRepo.updateAppRoleMenu(data);
            result = convertToDto(updatedData);
            log.info("Successfully updated AppRoleMenu: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while updating AppRoleMenu: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean deleteAppRoleMenu(String id) {
        log.info("Attempting to delete AppRoleMenu with ID: {}", id);
        boolean isDeleted = false;
        try {
            isDeleted = appRoleMenuRepo.deleteAppRoleMenu(id);
            if (isDeleted) {
                log.info("Successfully deleted AppRoleMenu with ID: {}", id);
            } else {
                log.warn("Failed to delete AppRoleMenu with ID: {}. It might not exist.", id);
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting AppRoleMenu with ID {}: {}", id, e.getMessage(), e);
        }
        return isDeleted;
    }

    @Override
    public List<AppRoleMenuGetDto> getAllAppRoleMenuGet() {
        log.info("Fetching all AppRoleMenu details...");
        try {
            List<Map<String, Object>> rawData = appRoleMenuRepo.getAppRoleMenuGets();
            List<AppRoleMenuGetDto> resultList = new ArrayList<>();
            rawData.forEach(map -> resultList.add(AppRoleMenuGetDto.mapToDto(map)));
            log.debug("Successfully fetched {} AppRoleMenu details", resultList.size());
            return resultList;
        } catch (Exception e) {
            log.error("Failed to fetch AppRoleMenu details. Error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<AppRoleMenuGetDto> getAppRoleMenuGetByRoleId(String roleId) {
        log.info("Fetching AppRoleMenu details by Role ID: {}", roleId);
        try {
            List<Map<String, Object>> rawData = appRoleMenuRepo.getAppRoleMenuGetByRoleId(roleId);
            List<AppRoleMenuGetDto> resultList = new ArrayList<>();
            rawData.forEach(map -> resultList.add(AppRoleMenuGetDto.mapToDto(map)));
            log.debug("Successfully fetched {} AppRoleMenu details for Role ID: {}", resultList.size(), roleId);
            return resultList;
        } catch (Exception e) {
            log.error("Failed to fetch AppRoleMenu details by Role ID: {}. Error: {}", roleId, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<AppRoleMenuGetDto> getAppRoleMenuGetByMenuId(String menuId) {
        log.info("Fetching AppRoleMenu details by Menu ID: {}", menuId);
        try {
            List<Map<String, Object>> rawData = appRoleMenuRepo.getAppRoleMenuGetByMenuId(menuId);
            List<AppRoleMenuGetDto> resultList = new ArrayList<>();
            rawData.forEach(map -> resultList.add(AppRoleMenuGetDto.mapToDto(map)));
            log.debug("Successfully fetched {} AppRoleMenu details for Menu ID: {}", resultList.size(), menuId);
            return resultList;
        } catch (Exception e) {
            log.error("Failed to fetch AppRoleMenu details by Menu ID: {}. Error: {}", menuId, e.getMessage(), e);
            throw e;
        }
    }

    private AppRoleMenu convertToEntity(AppRoleMenuDto convertObject) {
        log.debug("Converting AppRoleMenuDto to entity: {}", convertObject);
        AppRoleMenu result = AppRoleMenu.builder()
                .id(convertObject.getId())
                .roleId(convertObject.getRoleId())
                .menuId(convertObject.getMenuId())
                .build();
        return result;
    }

    private AppRoleMenu convertToEntityCreate(AppRoleMenuCreateDto convertObject) {
        log.debug("Converting AppRoleMenuCreateDto to entity: {}", convertObject);
        AppRoleMenu result = AppRoleMenu.builder()
                .roleId(convertObject.getRoleId())
                .menuId(convertObject.getMenuId())
                .build();
        return result;
    }

    private AppRoleMenu convertToEntityUpdate(AppRoleMenuUpdateDto convertObject) {
        log.debug("Converting AppRoleMenuUpdateDto to entity: {}", convertObject);
        AppRoleMenu result = AppRoleMenu.builder()
                .id(convertObject.getId())
                .roleId(convertObject.getRoleId())
                .menuId(convertObject.getMenuId())
                .build();
        return result;
    }

    private AppRoleMenuDto convertToDto(AppRoleMenu convertObject) {
        log.debug("Converting AppRoleMenu entity to DTO: {}", convertObject);
        AppRoleMenuDto result = AppRoleMenuDto.builder()
                .id(convertObject.getId())
                .roleId(convertObject.getRoleId())
                .menuId(convertObject.getMenuId())
                .build();
        return result;
    }
}
