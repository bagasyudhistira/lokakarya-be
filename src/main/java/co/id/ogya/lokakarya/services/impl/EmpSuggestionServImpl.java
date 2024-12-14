package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.empsuggestion.*;
import co.id.ogya.lokakarya.entities.EmpSuggestion;
import co.id.ogya.lokakarya.repositories.EmpSuggestionRepo;
import co.id.ogya.lokakarya.services.EmpSuggestionServ;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class EmpSuggestionServImpl implements EmpSuggestionServ {
    @Autowired
    private EmpSuggestionRepo empSuggestionRepo;

    @Override
    public List<EmpSuggestionDto> getAllEmpSuggestion() {
        log.info("Attempting to fetch all EmpSuggestions");
        List<EmpSuggestionDto> listResult = new ArrayList<>();
        try {
            List<EmpSuggestion> listData = empSuggestionRepo.getEmpSuggestions();
            log.debug("Fetched {} EmpSuggestions from repository", listData.size());
            for (EmpSuggestion data : listData) {
                EmpSuggestionDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all EmpSuggestions: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public EmpSuggestionDto getEmpSuggestionById(String id) {
        log.info("Attempting to fetch EmpSuggestion by ID: {}", id);
        EmpSuggestionDto result = null;
        try {
            EmpSuggestion data = empSuggestionRepo.getEmpSuggestionById(id);
            result = convertToDto(data);
            log.debug("Fetched EmpSuggestion: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching EmpSuggestion by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public EmpSuggestionDto createEmpSuggestion(EmpSuggestionCreateDto empSuggestionCreateDto) {
        log.info("Attempting to create a new EmpSuggestion with data: {}", empSuggestionCreateDto);
        EmpSuggestionDto result = null;
        try {
            EmpSuggestion data = convertToEntityCreate(empSuggestionCreateDto);
            EmpSuggestion savedData = empSuggestionRepo.saveEmpSuggestion(data);
            result = convertToDto(savedData);
            log.info("Successfully created EmpSuggestion: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while creating EmpSuggestion: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public EmpSuggestionDto updateEmpSuggestion(EmpSuggestionUpdateDto empSuggestionUpdateDto) {
        log.info("Attempting to update EmpSuggestion with data: {}", empSuggestionUpdateDto);
        EmpSuggestionDto result = null;
        try {
            EmpSuggestion data = convertToEntityUpdate(empSuggestionUpdateDto);
            EmpSuggestion updatedData = empSuggestionRepo.updateEmpSuggestion(data);
            result = convertToDto(updatedData);
            log.info("Successfully updated EmpSuggestion: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while updating EmpSuggestion: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean deleteEmpSuggestion(String id) {
        log.info("Attempting to delete EmpSuggestion with ID: {}", id);
        boolean isDeleted = false;
        try {
            isDeleted = empSuggestionRepo.deleteEmpSuggestion(id);
            if (isDeleted) {
                log.info("Successfully deleted EmpSuggestion with ID: {}", id);
            } else {
                log.warn("Failed to delete EmpSuggestion with ID: {}. It might not exist.", id);
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting EmpSuggestion with ID {}: {}", id, e.getMessage(), e);
        }
        return isDeleted;
    }

    @Override
    public List<EmpSuggestionGetDto> getAllEmpSuggestionGets() {
        log.info("Fetching all employee suggestions...");
        List<EmpSuggestionGetDto> resultList = new ArrayList<>();
        try {
            List<Map<String, Object>> empSuggestionRepoList = empSuggestionRepo.getEmpSuggestionGets();
            log.debug("Retrieved {} employee suggestions from repository", empSuggestionRepoList.size());
            empSuggestionRepoList.forEach(map -> resultList.add(EmpSuggestionGetDto.mapToDto(map)));
            log.info("Successfully fetched all employee suggestions.");
        } catch (Exception e) {
            log.error("Failed to fetch employee suggestions. Error: {}", e.getMessage(), e);
            throw e;
        }
        return resultList;
    }

    @Override
    public List<EmpSuggestionGetDto> getEmpSuggestionGetByUserId(String userId) {
        log.info("Fetching employee suggestions for user ID: {}", userId);
        List<EmpSuggestionGetDto> resultList = new ArrayList<>();
        try {
            List<Map<String, Object>> empSuggestionRepoList = empSuggestionRepo.getEmpSuggestionGetByUserId(userId);
            log.debug("Retrieved {} employee suggestions for user ID: {}", empSuggestionRepoList.size(), userId);
            empSuggestionRepoList.forEach(map -> resultList.add(EmpSuggestionGetDto.mapToDto(map)));
            log.info("Successfully fetched employee suggestions for user ID: {}", userId);
        } catch (Exception e) {
            log.error("Failed to fetch employee suggestions for user ID: {}. Error: {}", userId, e.getMessage(), e);
            throw e;
        }
        return resultList;
    }

    @Override
    public List<EmpSuggestionGetDto> getEmpSuggestionGetByCreatedBy(String userId) {
        log.info("Fetching employee suggestions by user ID: {}", userId);
        List<EmpSuggestionGetDto> resultList = new ArrayList<>();
        try {
            List<Map<String, Object>> empSuggestionRepoList = empSuggestionRepo.getEmpSuggestionGetByCreatedBy(userId);
            log.debug("Retrieved {} employee suggestions by user ID: {}", empSuggestionRepoList.size(), userId);
            empSuggestionRepoList.forEach(map -> resultList.add(EmpSuggestionGetDto.mapToDto(map)));
            log.info("Successfully fetched employee suggestions for user ID: {}", userId);
        } catch (Exception e) {
            log.error("Failed to fetch employee suggestions by user ID: {}. Error: {}", userId, e.getMessage(), e);
            throw e;
        }
        return resultList;
    }

    @Override
    public EmpSuggestionOneDto ifAnyEmpSuggestionExist(String userId, int assessmentYear) {
        log.info("Looking for EmpSuggestion with User ID: {} and Assessment Year: {}", userId, assessmentYear);
        try {
            Map<String, Object> data = empSuggestionRepo.ifAnyEmpSuggestionExist(userId, assessmentYear);
            if (data != null) {
                EmpSuggestionOneDto result = EmpSuggestionOneDto.mapToDto(data);
                log.info("There is an EmpSuggestion with UserID: {} and Assessment Year: {}", userId, assessmentYear);
                log.info(result.toString());
                return result;
            } else {
                log.info("There is no EmpSuggestion with UserID: {} and Assessment Year: {}", userId, assessmentYear);
                return null;
            }
        } catch (Exception e) {
            log.error("Error while looking EmpSuggestion by UserID: {} and Assessment Year: {}. Error: {}", userId, assessmentYear, e.getMessage());
            throw e;
        }
    }

    private EmpSuggestion convertToEntityCreate(EmpSuggestionCreateDto convertObject) {
        log.debug("Converting EmpSuggestionCreateDto to entity: {}", convertObject);
        EmpSuggestion result = EmpSuggestion.builder()
                .userId(convertObject.getUserId())
                .suggestion(convertObject.getSuggestion())
                .assessmentYear(convertObject.getAssessmentYear())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private EmpSuggestion convertToEntityUpdate(EmpSuggestionUpdateDto convertObject) {
        log.debug("Converting EmpSuggestionUpdateDto to entity: {}", convertObject);
        EmpSuggestion result = EmpSuggestion.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .suggestion(convertObject.getSuggestion())
                .assessmentYear(convertObject.getAssessmentYear())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private EmpSuggestionDto convertToDto(EmpSuggestion convertObject) {
        log.debug("Converting EmpSuggestion entity to DTO: {}", convertObject);
        EmpSuggestionDto result = EmpSuggestionDto.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .suggestion(convertObject.getSuggestion())
                .assessmentYear(convertObject.getAssessmentYear())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }
}
