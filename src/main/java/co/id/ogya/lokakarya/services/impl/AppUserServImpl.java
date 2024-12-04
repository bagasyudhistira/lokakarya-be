package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.appuser.*;
import co.id.ogya.lokakarya.entities.AppUser;
import co.id.ogya.lokakarya.repositories.AppUserRepo;
import co.id.ogya.lokakarya.services.AppUserServ;
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
public class AppUserServImpl implements AppUserServ {
    @Autowired
    private AppUserRepo appUserRepo;

    @Override
    public List<AppUserDto> getAllAppUser() {
        log.info("Attempting to fetch all AppUsers");
        List<AppUserDto> listResult = new ArrayList<>();
        try {
            List<AppUser> listData = appUserRepo.getAppUsers();
            log.debug("Fetched {} AppUsers from repository", listData.size());
            for (AppUser data : listData) {
                AppUserDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all AppUsers: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public AppUserDto getAppUserById(String id) {
        log.info("Attempting to fetch AppUser by ID: {}", id);
        AppUserDto result = null;
        try {
            AppUser data = appUserRepo.getAppUserById(id);
            result = convertToDto(data);
            log.debug("Fetched AppUser: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching AppUser by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<AppUserGetDto> getAllAppUserGet() {
        log.info("Attempting to fetch all AppUsers");
        List<AppUserGetDto> listResult = new ArrayList<>();
        try {
            List<Map<String,Object>> listData = appUserRepo.getAppUserGets();
            log.debug("Fetched {} AppUsers from repository", listData.size());
            for (Map<String,Object> data : listData) {
                AppUserGetDto result =  AppUserGetDto.mapToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all AppUsers: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public AppUserGetDto getAppUserGetById(String id) {
        log.info("Attempting to fetch AppUser by ID: {}", id);
        AppUserGetDto result = null;
        try {
            Map<String,Object> data = appUserRepo.getAppUserGetById(id);
            result =  AppUserGetDto.mapToDto(data);
            log.debug("Fetched AppUser: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching AppUser by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public AppUserGetDto getAppUserByUsername(String username) {
        log.info("Attempting to fetch AppUser by username: {}", username);
        AppUserGetDto result = null;
        try {
            Map<String,Object> data = appUserRepo.getAppUserByUsername(username);
            result =  AppUserGetDto.mapToDto(data);
            log.debug("Fetched AppUser: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching AppUser by username {}: {}", username, e.getMessage(), e);
        }
        return result;
    }


    @Override
    public AppUserGetDto getAppUserByFullName(String fullName) {
        log.info("Attempting to fetch AppUser by full name: {}", fullName);
        AppUserGetDto result = null;
        try {
            Map<String,Object> data = appUserRepo.getAppUserByFullName(fullName);
            result =  AppUserGetDto.mapToDto(data);
            log.debug("Fetched AppUser: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching AppUser by full name {}: {}", fullName, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public AppUserCreateDto createAppUser(AppUserCreateDto appUserCreateDto) {
        log.info("Attempting to create a new AppUser with data: {}", appUserCreateDto);
        AppUserCreateDto result = null;
        try {
            AppUser data = convertToEntityCreate(appUserCreateDto);
            AppUser savedData = appUserRepo.saveAppUser(data);
            result = convertToCreateDto(savedData);
            log.info("Successfully created AppUser: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while creating AppUser: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public AppUserDto updateAppUser(AppUserUpdateDto appUserUpdateDto) {
        log.info("Attempting to update AppUser with data: {}", appUserUpdateDto);
        AppUserDto result = null;
        try {
            AppUser data = convertToEntityUpdate(appUserUpdateDto);
            AppUser updatedData = appUserRepo.updateAppUser(data);
            result = convertToDto(updatedData);
            log.info("Successfully updated AppUser: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while updating AppUser: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean deleteAppUser(String id) {
        log.info("Attempting to delete AppUser with ID: {}", id);
        boolean isDeleted = false;
        try {
            isDeleted = appUserRepo.deleteAppUser(id);
            if (isDeleted) {
                log.info("Successfully deleted AppUser with ID: {}", id);
            } else {
                log.warn("Failed to delete AppUser with ID: {}. It might not exist.", id);
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting AppUser with ID {}: {}", id, e.getMessage(), e);
        }
        return isDeleted;
    }

    @Override
    public List<AppUserCommonDto> getAllAppUserCommons() {
        log.info("Attempting to fetch all AppUserCommons");
        List<AppUserCommonDto> listResult = new ArrayList<>();
        try {
            List<Map<String,Object>> listData = appUserRepo.getAppUsersCommons();
            log.debug("Fetched {} AppUsers from repository", listData.size());
            for (Map<String, Object> data : listData) {
                AppUserCommonDto result = AppUserCommonDto.mapToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all AppUserCommons: {}", e.getMessage(), e);
        }
        return listResult;
    }

    private AppUser convertToEntity(AppUserDto convertObject) {
        log.debug("Converting AppUserDto to entity: {}", convertObject);
        AppUser result = AppUser.builder()
                .id(convertObject.getId())
                .username(convertObject.getUsername())
                .fullName(convertObject.getFullName())
                .position(convertObject.getPosition())
                .emailAddress(convertObject.getEmailAddress())
                .employeeStatus(convertObject.getEmployeeStatus())
                .joinDate(convertObject.getJoinDate())
                .enabled(convertObject.isEnabled())
//                .password(convertObject.getPassword())
                .divisionId(convertObject.getDivisionId())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private AppUser convertToEntityCreate(AppUserCreateDto convertObject) {
        log.debug("Converting AppUserCreateDto to entity: {}", convertObject);
        AppUser result = AppUser.builder()
                .username(convertObject.getUsername())
                .fullName(convertObject.getFullName())
                .position(convertObject.getPosition())
                .emailAddress(convertObject.getEmailAddress())
                .employeeStatus(convertObject.getEmployeeStatus())
                .joinDate(convertObject.getJoinDate())
                .enabled(convertObject.isEnabled())
                .password(convertObject.getPassword())
                .divisionId(convertObject.getDivisionId())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private AppUser convertToEntityUpdate(AppUserUpdateDto convertObject) {
        log.debug("Converting AppUserUpdateDto to entity: {}", convertObject);
        AppUser result = AppUser.builder()
                .id(convertObject.getId())
                .username(convertObject.getUsername())
                .fullName(convertObject.getFullName())
                .position(convertObject.getPosition())
                .emailAddress(convertObject.getEmailAddress())
                .employeeStatus(convertObject.getEmployeeStatus())
                .joinDate(convertObject.getJoinDate())
                .enabled(convertObject.isEnabled())
//                .password(convertObject.getPassword())
                .divisionId(convertObject.getDivisionId())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private AppUserDto convertToDto(AppUser convertObject) {
        log.debug("Converting AppUser entity to DTO: {}", convertObject);
        AppUserDto result = AppUserDto.builder()
                .id(convertObject.getId())
                .username(convertObject.getUsername())
                .fullName(convertObject.getFullName())
                .position(convertObject.getPosition())
                .emailAddress(convertObject.getEmailAddress())
                .employeeStatus(convertObject.getEmployeeStatus())
                .joinDate(convertObject.getJoinDate())
                .enabled(convertObject.isEnabled())
//                .password(convertObject.getPassword())
                .divisionId(convertObject.getDivisionId())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private AppUserCreateDto convertToCreateDto(AppUser convertObject) {
        log.debug("Converting AppUser entity to DTO: {}", convertObject);
        AppUserCreateDto result = AppUserCreateDto.builder()
//                .id(convertObject.getId())
                .username(convertObject.getUsername())
                .fullName(convertObject.getFullName())
                .position(convertObject.getPosition())
                .emailAddress(convertObject.getEmailAddress())
                .employeeStatus(convertObject.getEmployeeStatus())
                .joinDate(convertObject.getJoinDate())
                .enabled(convertObject.isEnabled())
                .password(convertObject.getPassword())
                .divisionId(convertObject.getDivisionId())
//                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
//                .updatedAt(convertObject.getUpdatedAt())
//                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }
}
