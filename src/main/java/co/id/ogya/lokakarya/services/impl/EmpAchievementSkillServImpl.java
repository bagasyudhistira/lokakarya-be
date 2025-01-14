package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.empachievementskill.*;
import co.id.ogya.lokakarya.entities.EmpAchievementSkill;
import co.id.ogya.lokakarya.repositories.EmpAchievementSkillRepo;
import co.id.ogya.lokakarya.services.EmpAchievementSkillServ;
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
public class EmpAchievementSkillServImpl implements EmpAchievementSkillServ {
    @Autowired
    private EmpAchievementSkillRepo empAchievementSkillRepo;

    @Override
    public List<EmpAchievementSkillDto> getAllEmpAchievementSkill() {
        log.info("Attempting to fetch all EmpAchievementSkills");
        List<EmpAchievementSkillDto> listResult = new ArrayList<>();
        try {
            List<EmpAchievementSkill> listData = empAchievementSkillRepo.getEmpAchievementSkills();
            log.debug("Fetched {} EmpAchievementSkills from repository", listData.size());
            for (EmpAchievementSkill data : listData) {
                EmpAchievementSkillDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all EmpAchievementSkills: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public EmpAchievementSkillDto getEmpAchievementSkillById(String id) {
        log.info("Attempting to fetch EmpAchievementSkill by ID: {}", id);
        EmpAchievementSkillDto result = null;
        try {
            EmpAchievementSkill data = empAchievementSkillRepo.getEmpAchievementSkillById(id);
            result = convertToDto(data);
            log.debug("Fetched EmpAchievementSkill: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching EmpAchievementSkill by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<EmpAchievementSkillGetDto> getAllEmpAchievementSkillGet() {
        log.info("Attempting to fetch all EmpAchievementSkills");
        List<EmpAchievementSkillGetDto> listResult = new ArrayList<>();
        try {
            List<Map<String, Object>> listData = empAchievementSkillRepo.getEmpAchievementSkillGets();
            log.debug("Fetched {} EmpAchievementSkills from repository", listData.size());
            for (Map<String, Object> data : listData) {
                EmpAchievementSkillGetDto result = EmpAchievementSkillGetDto.mapToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all EmpAchievementSkills: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public EmpAchievementSkillGetDto getEmpAchievementSkillGetById(String id) {
        log.info("Attempting to fetch EmpAchievementSkill by ID: {}", id);
        EmpAchievementSkillGetDto result = null;
        try {
            Map<String, Object> data = empAchievementSkillRepo.getEmpAchievementSkillGetById(id);
            result = EmpAchievementSkillGetDto.mapToDto(data);
            log.debug("Fetched EmpAchievementSkill: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching EmpAchievementSkill by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<EmpAchievementSkillGetDto> getAllEmpAchievementSkillGetByUserId(String userId) {
        log.info("Attempting to fetch all EmpAchievementSkills by User ID: {}", userId);
        List<EmpAchievementSkillGetDto> listResult = new ArrayList<>();
        try {
            List<Map<String, Object>> listData = empAchievementSkillRepo.getEmpAchievementSkillGetsByUserId(userId);
            log.debug("Fetched {} EmpAchievementSkills for User ID: {} from repository", listData.size(), userId);
            for (Map<String, Object> data : listData) {
                EmpAchievementSkillGetDto result = EmpAchievementSkillGetDto.mapToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all EmpAchievementSkill By User ID: {} : {}", userId, e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public EmpAchievementSkillDto createEmpAchievementSkill(EmpAchievementSkillCreateDto empAchievementSkillCreateDto) {
        log.info("Attempting to create a new EmpAchievementSkill with data: {}", empAchievementSkillCreateDto);
        EmpAchievementSkillDto result = null;
        try {
            EmpAchievementSkill data = convertToEntityCreate(empAchievementSkillCreateDto);
            EmpAchievementSkill savedData = empAchievementSkillRepo.saveEmpAchievementSkill(data);
            result = convertToDto(savedData);
            log.info("Successfully created EmpAchievementSkill: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while creating EmpAchievementSkill: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public EmpAchievementSkillDto updateEmpAchievementSkill(EmpAchievementSkillUpdateDto empAchievementSkillUpdateDto) {
        log.info("Attempting to update EmpAchievementSkill with data: {}", empAchievementSkillUpdateDto);
        EmpAchievementSkillDto result = null;
        try {
            EmpAchievementSkill data = convertToEntityUpdate(empAchievementSkillUpdateDto);
            EmpAchievementSkill updatedData = empAchievementSkillRepo.updateEmpAchievementSkill(data);
            result = convertToDto(updatedData);
            log.info("Successfully updated EmpAchievementSkill: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while updating EmpAchievementSkill: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean deleteEmpAchievementSkill(String id) {
        log.info("Attempting to delete EmpAchievementSkill with ID: {}", id);
        boolean isDeleted = false;
        try {
            isDeleted = empAchievementSkillRepo.deleteEmpAchievementSkill(id);
            if (isDeleted) {
                log.info("Successfully deleted EmpAchievementSkill with ID: {}", id);
            } else {
                log.warn("Failed to delete EmpAchievementSkill with ID: {}. It might not exist.", id);
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting EmpAchievementSkill with ID {}: {}", id, e.getMessage(), e);
        }
        return isDeleted;
    }

    @Override
    public Boolean ifAnyEmpAchievementSkillExist(String userId, String achievementId, int assessmentYear) {
        log.info("Looking for EmpAchievementSkill with User ID: {}, Achievement ID: {}, and Assessment Year: {}", userId, achievementId, assessmentYear);
        Boolean result = false;
        try {
            result = empAchievementSkillRepo.ifAnyEmpAchievementSkillExist(userId, achievementId, assessmentYear);
            if (result) {
                log.info("There is an EmpAchievementSkill with User ID: {}, Achievement ID: {}, and Assessment Year: {}", userId, achievementId, assessmentYear);
                return true;
            } else {
                log.info("There is no EmpAchievementSkill with User ID: {}, Achievement ID: {}, and Assessment Year: {}", userId, achievementId, assessmentYear);
                return false;
            }
        } catch (Exception e) {
            log.error("Error while looking EmpAchievementSkill by User ID: {}, Achievement ID: {}, and Assessment Year: {}. Error: {}", userId, achievementId, assessmentYear, e.getMessage());
            throw e;
        }
    }

    @Override
    public List<EmpAchievementSkillGetUIDYearDto> getAllEmpAchievementSkillGetByUserIdAssessmentYear(String userId, int assessmentYear) {
        log.info("Attempting to fetch all EmpAchievementSkills by User ID: {} and Assessment Year: {}", userId, assessmentYear);
        List<EmpAchievementSkillGetUIDYearDto> listResult = new ArrayList<>();
        try {
            List<Map<String, Object>> listData = empAchievementSkillRepo.getEmpAchievementSkillGetsByUserIdAssessmentYear(userId, assessmentYear);
            log.debug("Fetched {} EmpAchievementSkills for User ID: {} and Assessment Year: {} from repository", listData.size(), userId, assessmentYear);
            for (Map<String, Object> data : listData) {
                EmpAchievementSkillGetUIDYearDto result = EmpAchievementSkillGetUIDYearDto.mapToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all EmpAchievementSkill By User ID: {} : {}", userId, e.getMessage(), e);
        }
        return listResult;
    }

    private EmpAchievementSkill convertToEntityCreate(EmpAchievementSkillCreateDto convertObject) {
        log.debug("Converting EmpAchievementSkillCreateDto to entity: {}", convertObject);
        EmpAchievementSkill result = EmpAchievementSkill.builder()
                .userId(convertObject.getUserId())
                .notes(convertObject.getNotes())
                .achievementId(convertObject.getAchievementId())
                .score(convertObject.getScore())
                .assessmentYear(convertObject.getAssessmentYear())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private EmpAchievementSkill convertToEntityUpdate(EmpAchievementSkillUpdateDto convertObject) {
        log.debug("Converting EmpAchievementSkillUpdateDto to entity: {}", convertObject);
        EmpAchievementSkill result = EmpAchievementSkill.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .notes(convertObject.getNotes())
                .achievementId(convertObject.getAchievementId())
                .score(convertObject.getScore())
                .assessmentYear(convertObject.getAssessmentYear())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private EmpAchievementSkillDto convertToDto(EmpAchievementSkill convertObject) {
        log.debug("Converting EmpAchievementSkill entity to DTO: {}", convertObject);
        EmpAchievementSkillDto result = EmpAchievementSkillDto.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .notes(convertObject.getNotes())
                .achievementId(convertObject.getAchievementId())
                .score(convertObject.getScore())
                .assessmentYear(convertObject.getAssessmentYear())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }
}
