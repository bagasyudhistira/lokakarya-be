package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.empattitudeskill.*;
import co.id.ogya.lokakarya.entities.EmpAttitudeSkill;
import co.id.ogya.lokakarya.repositories.EmpAttitudeSkillRepo;
import co.id.ogya.lokakarya.services.EmpAttitudeSkillServ;
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
public class EmpAttitudeSkillServImpl implements EmpAttitudeSkillServ {
    @Autowired
    private EmpAttitudeSkillRepo empAttitudeSkillRepo;

    @Override
    public List<EmpAttitudeSkillDto> getAllEmpAttitudeSkill() {
        log.info("Attempting to fetch all EmpAttitudeSkills");
        List<EmpAttitudeSkillDto> listResult = new ArrayList<>();
        try {
            List<EmpAttitudeSkill> listData = empAttitudeSkillRepo.getEmpAttitudeSkills();
            log.debug("Fetched {} EmpAttitudeSkills from repository", listData.size());
            for (EmpAttitudeSkill data : listData) {
                EmpAttitudeSkillDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all EmpAttitudeSkills: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public EmpAttitudeSkillDto getEmpAttitudeSkillById(String id) {
        log.info("Attempting to fetch EmpAttitudeSkill by ID: {}", id);
        EmpAttitudeSkillDto result = null;
        try {
            EmpAttitudeSkill data = empAttitudeSkillRepo.getEmpAttitudeSkillById(id);
            result = convertToDto(data);
            log.debug("Fetched EmpAttitudeSkill: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching EmpAttitudeSkill by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<EmpAttitudeSkillGetDto> getAllEmpAttitudeSkillGet() {
        List<EmpAttitudeSkillGetDto> listResult = new ArrayList<>();
        log.info("Attempting to fetch all EmpAttitudeSkills");
        try {
            List<Map<String, Object>> listData = empAttitudeSkillRepo.getEmpAttitudeSkillGets();
            log.debug("Fetched {} EmpAttitudeSkills from repository", listData.size());

            for (Map<String, Object> data : listData) {
                EmpAttitudeSkillGetDto result = EmpAttitudeSkillGetDto.mapToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all EmpAttitudeSkills: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public EmpAttitudeSkillGetDto getEmpAttitudeSkillGetById(String id) {
        log.info("Attempting to fetch EmpAttitudeSkill by ID: {}", id);
        EmpAttitudeSkillGetDto result = null;
        try {
            Map<String, Object> data = empAttitudeSkillRepo.getEmpAttitudeSkillGetById(id);
            result = EmpAttitudeSkillGetDto.mapToDto(data);
            log.debug("Fetched EmpAttitudeSkill: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching EmpAttitudeSkill by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<EmpAttitudeSkillGetDto> getAllEmpAttitudeSkillGetByUserId(String userId) {
        List<EmpAttitudeSkillGetDto> listResult = new ArrayList<>();
        log.info("Attempting to fetch all EmpAttitudeSkills for User ID: {}", userId);
        try {
            List<Map<String, Object>> listData = empAttitudeSkillRepo.getEmpAttitudeSkillGetsByUserId(userId);
            log.debug("Fetched {} EmpAttitudeSkills for User ID: {} from repository", userId, listData.size());

            for (Map<String, Object> data : listData) {
                EmpAttitudeSkillGetDto result = EmpAttitudeSkillGetDto.mapToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all EmpAttitudeSkills for User ID: {} : {}", userId, e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public EmpAttitudeSkillDto createEmpAttitudeSkill(EmpAttitudeSkillCreateDto empAttitudeSkillCreateDto) {
        log.info("Attempting to create a new EmpAttitudeSkill with data: {}", empAttitudeSkillCreateDto);
        EmpAttitudeSkillDto result = null;
        try {
            EmpAttitudeSkill data = convertToEntityCreate(empAttitudeSkillCreateDto);
            EmpAttitudeSkill savedData = empAttitudeSkillRepo.saveEmpAttitudeSkill(data);
            result = convertToDto(savedData);
            log.info("Successfully created EmpAttitudeSkill: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while creating EmpAttitudeSkill: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public EmpAttitudeSkillDto updateEmpAttitudeSkill(EmpAttitudeSkillUpdateDto empAttitudeSkillUpdateDto) {
        log.info("Attempting to update EmpAttitudeSkill with data: {}", empAttitudeSkillUpdateDto);
        EmpAttitudeSkillDto result = null;
        try {
            EmpAttitudeSkill data = convertToEntityUpdate(empAttitudeSkillUpdateDto);
            EmpAttitudeSkill updatedData = empAttitudeSkillRepo.updateEmpAttitudeSkill(data);
            result = convertToDto(updatedData);
            log.info("Successfully updated EmpAttitudeSkill: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while updating EmpAttitudeSkill: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean deleteEmpAttitudeSkill(String id) {
        log.info("Attempting to delete EmpAttitudeSkill with ID: {}", id);
        boolean isDeleted = false;
        try {
            isDeleted = empAttitudeSkillRepo.deleteEmpAttitudeSkill(id);
            if (isDeleted) {
                log.info("Successfully deleted EmpAttitudeSkill with ID: {}", id);
            } else {
                log.warn("Failed to delete EmpAttitudeSkill with ID: {}. It might not exist.", id);
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting EmpAttitudeSkill with ID {}: {}", id, e.getMessage(), e);
        }
        return isDeleted;
    }

    @Override
    public Boolean ifAnyEmpAttitudeSkillExist(String userId, String attitudeSkillId, int assessmentYear) {
        log.info("Looking for EmpAttitudeSkill with User ID: {}, Attitude Skill ID: {}, and Assessment Year: {}", userId, attitudeSkillId, assessmentYear);
        Boolean result = false;
        try {
            result = empAttitudeSkillRepo.ifAnyEmpAttitudeSkillExist(userId, attitudeSkillId, assessmentYear);
            if (result) {
                log.info("There is an EmpAttitudeSkill with User ID: {}, Attitude Skill ID: {}, and Assessment Year: {}", userId, attitudeSkillId, assessmentYear);
                return true;
            } else {
                log.info("There is no EmpAttitudeSkill with User ID: {}, Attitude Skill ID: {}, and Assessment Year: {}", userId, attitudeSkillId, assessmentYear);
                return false;
            }
        } catch (Exception e) {
            log.error("Error while looking EmpAttitudeSkill by User ID: {}, Attitude Skill ID: {}, and Assessment Year: {}. Error: {}", userId, attitudeSkillId, assessmentYear, e.getMessage());
            throw e;
        }
    }

    @Override
    public List<EmpAttitudeSkillGetUIDYearDto> getAllEmpAttitudeSkillGetByUserIdAssessmentYear(String userId, int assessmentYear) {
        log.info("Attempting to fetch all EmpAttitudeSkills by User ID: {} and Assessment Year: {}", userId, assessmentYear);
        List<EmpAttitudeSkillGetUIDYearDto> listResult = new ArrayList<>();
        try {
            List<Map<String, Object>> listData = empAttitudeSkillRepo.getEmpAttitudeSkillGetsByUserIdAssessmentYear(userId, assessmentYear);
            log.debug("Fetched {} EmpAttitudeSkills for User ID: {} and Assessment Year: {} from repository", listData.size(), userId, assessmentYear);
            for (Map<String, Object> data : listData) {
                EmpAttitudeSkillGetUIDYearDto result = EmpAttitudeSkillGetUIDYearDto.mapToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all EmpAttitudeSkills By User ID: {} : {}", userId, e.getMessage(), e);
        }
        return listResult;
    }

    private EmpAttitudeSkill convertToEntityCreate(EmpAttitudeSkillCreateDto convertObject) {
        log.debug("Converting EmpAttitudeSkillCreateDto to entity: {}", convertObject);
        EmpAttitudeSkill result = EmpAttitudeSkill.builder()
                .userId(convertObject.getUserId())
                .attitudeSkillId(convertObject.getAttitudeSkillId())
                .score(convertObject.getScore())
                .assessmentYear(convertObject.getAssessmentYear())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private EmpAttitudeSkill convertToEntityUpdate(EmpAttitudeSkillUpdateDto convertObject) {
        log.debug("Converting EmpAttitudeSkillUpdateDto to entity: {}", convertObject);
        EmpAttitudeSkill result = EmpAttitudeSkill.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .attitudeSkillId(convertObject.getAttitudeSkillId())
                .score(convertObject.getScore())
                .assessmentYear(convertObject.getAssessmentYear())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private EmpAttitudeSkillDto convertToDto(EmpAttitudeSkill convertObject) {
        log.debug("Converting EmpAttitudeSkill entity to DTO: {}", convertObject);
        EmpAttitudeSkillDto result = EmpAttitudeSkillDto.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .attitudeSkillId(convertObject.getAttitudeSkillId())
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
