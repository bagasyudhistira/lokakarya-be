package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.emptechnicalskill.EmpTechnicalSkillCreateDto;
import co.id.ogya.lokakarya.dto.emptechnicalskill.EmpTechnicalSkillDto;
import co.id.ogya.lokakarya.dto.emptechnicalskill.EmpTechnicalSkillGetDto;
import co.id.ogya.lokakarya.dto.emptechnicalskill.EmpTechnicalSkillUpdateDto;
import co.id.ogya.lokakarya.entities.EmpTechnicalSkill;
import co.id.ogya.lokakarya.repositories.EmpTechnicalSkillRepo;
import co.id.ogya.lokakarya.services.EmpTechnicalSkillServ;
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
public class EmpTechnicalSkillServImpl implements EmpTechnicalSkillServ {
    @Autowired
    private EmpTechnicalSkillRepo empTechnicalSkillRepo;

    @Override
    public List<EmpTechnicalSkillDto> getAllEmpTechnicalSkill() {
        log.info("Attempting to fetch all EmpTechnicalSkills");
        List<EmpTechnicalSkillDto> listResult = new ArrayList<>();
        try {
            List<EmpTechnicalSkill> listData = empTechnicalSkillRepo.getEmpTechnicalSkills();
            log.debug("Fetched {} EmpTechnicalSkills from repository", listData.size());
            for (EmpTechnicalSkill data : listData) {
                EmpTechnicalSkillDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all EmpTechnicalSkills: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public EmpTechnicalSkillDto getEmpTechnicalSkillById(String id) {
        log.info("Attempting to fetch EmpTechnicalSkill by ID: {}", id);
        EmpTechnicalSkillDto result = null;
        try {
            EmpTechnicalSkill data = empTechnicalSkillRepo.getEmpTechnicalSkillById(id);
            result = convertToDto(data);
            log.debug("Fetched EmpTechnicalSkill: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching EmpTechnicalSkill by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public EmpTechnicalSkillDto createEmpTechnicalSkill(EmpTechnicalSkillCreateDto empTechnicalSkillCreateDto) {
        log.info("Attempting to create a new EmpTechnicalSkill with data: {}", empTechnicalSkillCreateDto);
        EmpTechnicalSkillDto result = null;
        try {
            EmpTechnicalSkill data = convertToEntityCreate(empTechnicalSkillCreateDto);
            EmpTechnicalSkill savedData = empTechnicalSkillRepo.saveEmpTechnicalSkill(data);
            result = convertToDto(savedData);
            log.info("Successfully created EmpTechnicalSkill: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while creating EmpTechnicalSkill: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public EmpTechnicalSkillDto updateEmpTechnicalSkill(EmpTechnicalSkillUpdateDto empTechnicalSkillUpdateDto) {
        log.info("Attempting to update EmpTechnicalSkill with data: {}", empTechnicalSkillUpdateDto);
        EmpTechnicalSkillDto result = null;
        try {
            EmpTechnicalSkill data = convertToEntityUpdate(empTechnicalSkillUpdateDto);
            EmpTechnicalSkill updatedData = empTechnicalSkillRepo.updateEmpTechnicalSkill(data);
            result = convertToDto(updatedData);
            log.info("Successfully updated EmpTechnicalSkill: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while updating EmpTechnicalSkill: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean deleteEmpTechnicalSkill(String id) {
        log.info("Attempting to delete EmpTechnicalSkill with ID: {}", id);
        boolean isDeleted = false;
        try {
            isDeleted = empTechnicalSkillRepo.deleteEmpTechnicalSkill(id);
            if (isDeleted) {
                log.info("Successfully deleted EmpTechnicalSkill with ID: {}", id);
            } else {
                log.warn("Failed to delete EmpTechnicalSkill with ID: {}. It might not exist.", id);
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting EmpTechnicalSkill with ID {}: {}", id, e.getMessage(), e);
        }
        return isDeleted;
    }

    @Override
    public List<EmpTechnicalSkillGetDto> getAllEmpTechnicalSkillGets() {
        log.info("Fetching all employee technical skills...");
        List<EmpTechnicalSkillGetDto> resultList = new ArrayList<>();
        try {
            List<Map<String, Object>> empTechnicalSkillList = empTechnicalSkillRepo.getEmpTechnicalSkillGets();
            log.debug("Retrieved {} employee technical skills from repository", empTechnicalSkillList.size());
            empTechnicalSkillList.forEach(map -> resultList.add(EmpTechnicalSkillGetDto.mapToDto(map)));
            log.info("Successfully fetched all employee technical skills.");
        } catch (Exception e) {
            log.error("Failed to fetch employee technical skills. Error: {}", e.getMessage(), e);
            throw e;
        }
        return resultList;
    }

    @Override
    public List<EmpTechnicalSkillGetDto> getEmpTechnicalSkillGetByUserId(String userId) {
        log.info("Fetching employee technical skills for user ID: {}", userId);
        List<EmpTechnicalSkillGetDto> resultList = new ArrayList<>();
        try {
            List<Map<String, Object>> empTechnicalSkillList = empTechnicalSkillRepo.getEmpTechnicalSkillGetByUserId(userId);
            log.debug("Retrieved {} employee technical skills for user ID: {}", empTechnicalSkillList.size(), userId);
            empTechnicalSkillList.forEach(map -> resultList.add(EmpTechnicalSkillGetDto.mapToDto(map)));
            log.info("Successfully fetched employee technical skills for user ID: {}", userId);
        } catch (Exception e) {
            log.error("Failed to fetch employee technical skills for user ID: {}. Error: {}", userId, e.getMessage(), e);
            throw e;
        }
        return resultList;
    }

    @Override
    public Boolean ifAnyEmpTechnicalSkillExist(String userId, String technicalSkillId, int assessmentYear) {
        log.info("Looking for EmpTechnicalSkill with User ID: {}, Technical Skill ID: {}, and Assessment Year: {}", userId, technicalSkillId, assessmentYear);
        Boolean result = false;
        try {
            result = empTechnicalSkillRepo.ifAnyEmpTechnicalSkillExist(userId, technicalSkillId, assessmentYear);
            if (result) {
                log.info("There is an EmpTechnicalSkill with User ID: {}, Technical Skill ID: {}, and Assessment Year: {}", userId, technicalSkillId, assessmentYear);
                return true;
            } else {
                log.info("There is no EmpTechnicalSkill with User ID: {}, Technical Skill ID: {}, and Assessment Year: {}", userId, technicalSkillId, assessmentYear);
                return false;
            }
        } catch (Exception e) {
            log.error("Error while looking EmpTechnicalSkill by User ID: {}, Technical Skill ID: {}, and Assessment Year: {}. Error: {}", userId, technicalSkillId, assessmentYear, e.getMessage());
            throw e;
        }
    }

    private EmpTechnicalSkill convertToEntity(EmpTechnicalSkillDto convertObject) {
        log.debug("Converting EmpTechnicalSkillDto to entity: {}", convertObject);
        EmpTechnicalSkill result = EmpTechnicalSkill.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .technicalSkillId(convertObject.getTechnicalSkillId())
                .score(convertObject.getScore())
                .assessmentYear(convertObject.getAssessmentYear())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private EmpTechnicalSkill convertToEntityCreate(EmpTechnicalSkillCreateDto convertObject) {
        log.debug("Converting EmpTechnicalSkillCreateDto to entity: {}", convertObject);
        EmpTechnicalSkill result = EmpTechnicalSkill.builder()
                .userId(convertObject.getUserId())
                .technicalSkillId(convertObject.getTechnicalSkillId())
                .score(convertObject.getScore())
                .assessmentYear(convertObject.getAssessmentYear())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private EmpTechnicalSkill convertToEntityUpdate(EmpTechnicalSkillUpdateDto convertObject) {
        log.debug("Converting EmpTechnicalSkillUpdateDto to entity: {}", convertObject);
        EmpTechnicalSkill result = EmpTechnicalSkill.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .technicalSkillId(convertObject.getTechnicalSkillId())
                .score(convertObject.getScore())
                .assessmentYear(convertObject.getAssessmentYear())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private EmpTechnicalSkillDto convertToDto(EmpTechnicalSkill convertObject) {
        log.debug("Converting EmpTechnicalSkill entity to DTO: {}", convertObject);
        EmpTechnicalSkillDto result = EmpTechnicalSkillDto.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .technicalSkillId(convertObject.getTechnicalSkillId())
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
