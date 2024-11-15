package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.accessdivision.AccessDivisionCreateDto;
import co.id.ogya.lokakarya.dto.accessdivision.AccessDivisionDto;
import co.id.ogya.lokakarya.dto.accessdivision.AccessDivisionUpdateDto;
import co.id.ogya.lokakarya.entities.AccessDivision;
import co.id.ogya.lokakarya.repositories.AccessDivisionRepo;
import co.id.ogya.lokakarya.services.AccessDivisionServ;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
@Slf4j
public class AccessDivisionServImpl implements AccessDivisionServ {
    @Autowired
    private AccessDivisionRepo accessDivisionRepo;

    @Override
    public List<AccessDivisionDto> getAllAccessDivision() {
        log.info("Attempting to fetch all access divisions");
        List<AccessDivisionDto> listResult = new ArrayList<>();
        try {
            List<AccessDivision> listData = accessDivisionRepo.getAccessDivisions();
            log.debug("Fetched {} access divisions from repository", listData.size());
            for (AccessDivision data : listData) {
                AccessDivisionDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all access divisions: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public AccessDivisionDto getAccessDivisionById(String id) {
        log.info("Attempting to fetch access division by ID: {}", id);
        AccessDivisionDto result = null;
        try {
            AccessDivision data = accessDivisionRepo.getAccessDivisionById(id);
            if (data != null) {
                result = convertToDto(data);
                log.debug("Fetched access division: {}", result);
            } else {
                log.warn("No access division found with ID: {}", id);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching access division by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public AccessDivisionDto createAccessDivision(AccessDivisionCreateDto accessDivisionCreateDto) {
        log.info("Attempting to create a new access division with data: {}", accessDivisionCreateDto);
        AccessDivisionDto result = null;
        try {
            AccessDivision data = convertToEntityCreate(accessDivisionCreateDto);
            AccessDivision savedData = accessDivisionRepo.saveAccessDivision(data);
            result = convertToDto(savedData);
            log.info("Successfully created access division: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while creating access division: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public AccessDivisionDto updateAccessDivision(AccessDivisionUpdateDto accessDivisionUpdateDto) {
        log.info("Attempting to update access division with data: {}", accessDivisionUpdateDto);
        AccessDivisionDto result = null;
        try {
            AccessDivision data = convertToEntityUpdate(accessDivisionUpdateDto);
            AccessDivision updatedData = accessDivisionRepo.updateAccessDivision(data);
            result = convertToDto(updatedData);
            log.info("Successfully updated access division: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while updating access division: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean deleteAccessDivision(String id) {
        log.info("Attempting to delete access division with ID: {}", id);
        boolean isDeleted = false;
        try {
            isDeleted = accessDivisionRepo.deleteAccessDivision(id);
            if (isDeleted) {
                log.info("Successfully deleted access division with ID: {}", id);
            } else {
                log.warn("Failed to delete access division with ID: {}. Division might not exist.", id);
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting access division with ID {}: {}", id, e.getMessage(), e);
        }
        return isDeleted;
    }

    private AccessDivision convertToEntity(AccessDivisionDto convertObject) {
        log.debug("Converting AccessDivisionDto to entity: {}", convertObject);
        AccessDivision result = AccessDivision.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .divisionId(convertObject.getDivisionId())
                .build();
        return result;
    }

    private AccessDivision convertToEntityCreate(AccessDivisionCreateDto convertObject) {
        log.debug("Converting AccessDivisionCreateDto to entity: {}", convertObject);
        AccessDivision result = AccessDivision.builder()
                .userId(convertObject.getUserId())
                .divisionId(convertObject.getDivisionId())
                .build();
        return result;
    }

    private AccessDivision convertToEntityUpdate(AccessDivisionUpdateDto convertObject) {
        log.debug("Converting AccessDivisionUpdateDto to entity: {}", convertObject);
        AccessDivision result = AccessDivision.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .divisionId(convertObject.getDivisionId())
                .build();
        return result;
    }

    private AccessDivisionDto convertToDto(AccessDivision convertObject) {
        log.debug("Converting AccessDivision entity to DTO: {}", convertObject);
        AccessDivisionDto result = AccessDivisionDto.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .divisionId(convertObject.getDivisionId())
                .build();
        return result;
    }
}
