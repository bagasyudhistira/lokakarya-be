package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.appmenu.AppMenuCreateDto;
import co.id.ogya.lokakarya.dto.appmenu.AppMenuDto;
import co.id.ogya.lokakarya.dto.appmenu.AppMenuUpdateDto;
import co.id.ogya.lokakarya.services.AppMenuServ;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class AppMenuServImpl implements AppMenuServ {
    @Autowired
    private AppMenuRepo appMenuRepo;

    @Override
    public List<AppMenuDto> getAllAppMenu() {
        log.info("Attempting to fetch all AppMenus");
        List<AppMenuDto> listResult = new ArrayList<>();
        try {
            List<AppMenu> listData = appMenuRepo.getAppMenus();
            log.debug("Fetched {} AppMenus from repository", listData.size());
            for (AppMenu data : listData) {
                AppMenuDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all AppMenus: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public AppMenuDto getAppMenuById(String id) {
        log.info("Attempting to fetch AppMenu by ID: {}", id);
        AppMenuDto result = null;
        try {
            AppMenu data = appMenuRepo.getAppMenuById(id);
            result = convertToDto(data);
            log.debug("Fetched AppMenu: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching AppMenu by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public AppMenuDto createAppMenu(AppMenuCreateDto appMenuCreateDto) {
        log.info("Attempting to create a new AppMenu with data: {}", appMenuCreateDto);
        AppMenuDto result = null;
        try {
            AppMenu data = convertToEntityCreate(appMenuCreateDto);
            AppMenu savedData = appMenuRepo.saveAppMenu(data);
            result = convertToDto(savedData);
            log.info("Successfully created AppMenu: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while creating AppMenu: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public AppMenuDto updateAppMenu(AppMenuUpdateDto appMenuUpdateDto) {
        log.info("Attempting to update AppMenu with data: {}", appMenuUpdateDto);
        AppMenuDto result = null;
        try {
            AppMenu data = convertToEntityUpdate(appMenuUpdateDto);
            AppMenu updatedData = appMenuRepo.updateAppMenu(data);
            result = convertToDto(updatedData);
            log.info("Successfully updated AppMenu: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while updating AppMenu: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean deleteAppMenu(String id) {
        log.info("Attempting to delete AppMenu with ID: {}", id);
        boolean isDeleted = false;
        try {
            isDeleted = appMenuRepo.deleteAppMenu(id);
            if (isDeleted) {
                log.info("Successfully deleted AppMenu with ID: {}", id);
            } else {
                log.warn("Failed to delete AppMenu with ID: {}. It might not exist.", id);
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting AppMenu with ID {}: {}", id, e.getMessage(), e);
        }
        return isDeleted;
    }

    private AppMenu convertToEntity(AppMenuDto convertObject) {
        log.debug("Converting AppMenuDto to entity: {}", convertObject);
        AppMenu result = AppMenu.builder()
                .id(convertObject.getId())
                .menuName(convertObject.getMenuName())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private AppMenu convertToEntityCreate(AppMenuCreateDto convertObject) {
        log.debug("Converting AppMenuCreateDto to entity: {}", convertObject);
        AppMenu result = AppMenu.builder()
                .id(convertObject.getId())
                .menuName(convertObject.getMenuName())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private AppMenu convertToEntityUpdate(AppMenuUpdateDto convertObject) {
        log.debug("Converting AppMenuUpdateDto to entity: {}", convertObject);
        AppMenu result = AppMenu.builder()
                .id(convertObject.getId())
                .menuName(convertObject.getMenuName())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private AppMenuDto convertToDto(AppMenu convertObject) {
        log.debug("Converting AppMenu entity to DTO: {}", convertObject);
        AppMenuDto result = AppMenuDto.builder()
                .id(convertObject.getId())
                .menuName(convertObject.getMenuName())
                .build();
        return result;
    }
}
