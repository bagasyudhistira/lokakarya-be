package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.empdevplan.EmpDevPlanCreateDto;
import co.id.ogya.lokakarya.dto.empdevplan.EmpDevPlanDto;
import co.id.ogya.lokakarya.dto.empdevplan.EmpDevPlanGetDto;
import co.id.ogya.lokakarya.dto.empdevplan.EmpDevPlanUpdateDto;
import co.id.ogya.lokakarya.entities.EmpDevPlan;
import co.id.ogya.lokakarya.repositories.EmpDevPlanRepo;
import co.id.ogya.lokakarya.services.EmpDevPlanServ;
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
public class EmpDevPlanServImpl implements EmpDevPlanServ {
    @Autowired
    private EmpDevPlanRepo empDevPlanRepo;

    @Override
    public List<EmpDevPlanDto> getAllEmpDevPlan() {
        log.info("Attempting to fetch all EmpDevPlans");
        List<EmpDevPlanDto> listResult = new ArrayList<>();
        try {
            List<EmpDevPlan> listData = empDevPlanRepo.getEmpDevPlans();
            log.debug("Fetched {} EmpDevPlans from repository", listData.size());
            for (EmpDevPlan data : listData) {
                EmpDevPlanDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all EmpDevPlans: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public EmpDevPlanDto getEmpDevPlanById(String id) {
        log.info("Attempting to fetch EmpDevPlan by ID: {}", id);
        EmpDevPlanDto result = null;
        try {
            EmpDevPlan data = empDevPlanRepo.getEmpDevPlanById(id);
            result = convertToDto(data);
            log.debug("Fetched EmpDevPlan: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching EmpDevPlan by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public EmpDevPlanDto createEmpDevPlan(EmpDevPlanCreateDto empDevPlanCreateDto) {
        log.info("Attempting to create a new EmpDevPlan with data: {}", empDevPlanCreateDto);
        EmpDevPlanDto result = null;
        try {
            EmpDevPlan data = convertToEntityCreate(empDevPlanCreateDto);
            EmpDevPlan savedData = empDevPlanRepo.saveEmpDevPlan(data);
            result = convertToDto(savedData);
            log.info("Successfully created EmpDevPlan: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while creating EmpDevPlan: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public EmpDevPlanDto updateEmpDevPlan(EmpDevPlanUpdateDto empDevPlanUpdateDto) {
        log.info("Attempting to update EmpDevPlan with data: {}", empDevPlanUpdateDto);
        EmpDevPlanDto result = null;
        try {
            EmpDevPlan data = convertToEntityUpdate(empDevPlanUpdateDto);
            EmpDevPlan updatedData = empDevPlanRepo.updateEmpDevPlan(data);
            result = convertToDto(updatedData);
            log.info("Successfully updated EmpDevPlan: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while updating EmpDevPlan: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean deleteEmpDevPlan(String id) {
        log.info("Attempting to delete EmpDevPlan with ID: {}", id);
        boolean isDeleted = false;
        try {
            isDeleted = empDevPlanRepo.deleteEmpDevPlan(id);
            if (isDeleted) {
                log.info("Successfully deleted EmpDevPlan with ID: {}", id);
            } else {
                log.warn("Failed to delete EmpDevPlan with ID: {}. It might not exist.", id);
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting EmpDevPlan with ID {}: {}", id, e.getMessage(), e);
        }
        return isDeleted;
    }

    @Override
    public List<EmpDevPlanGetDto> getAllEmpDevPlanGets() {
        List<Map<String, Object>> empDevPlanList = empDevPlanRepo.getEmpDevPlanGets();

        List<EmpDevPlanGetDto> resultList = new ArrayList<>();

        for (Map<String, Object> map : empDevPlanList) {
            resultList.add(EmpDevPlanGetDto.mapToDto(map));
        }

        return resultList;
    }

    @Override
    public List<EmpDevPlanGetDto> getEmpDevPlanGetByUserId(String userId) {
        List<Map<String, Object>> empDevPlanList = empDevPlanRepo.getEmpDevPlanGetByUserId(userId);

        List<EmpDevPlanGetDto> resultList = new ArrayList<>();

        for (Map<String, Object> map : empDevPlanList) {
            resultList.add(EmpDevPlanGetDto.mapToDto(map));
        }

        return resultList;
    }

    private EmpDevPlan convertToEntity(EmpDevPlanDto convertObject) {
        log.debug("Converting EmpDevPlanDto to entity: {}", convertObject);
        EmpDevPlan result = EmpDevPlan.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .devPlanId(convertObject.getDevPlanId())
                .assessmentYear(convertObject.getAssessmentYear())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private EmpDevPlan convertToEntityCreate(EmpDevPlanCreateDto convertObject) {
        log.debug("Converting EmpDevPlanCreateDto to entity: {}", convertObject);
        EmpDevPlan result = EmpDevPlan.builder()
                .userId(convertObject.getUserId())
                .devPlanId(convertObject.getDevPlanId())
                .assessmentYear(convertObject.getAssessmentYear())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private EmpDevPlan convertToEntityUpdate(EmpDevPlanUpdateDto convertObject) {
        log.debug("Converting EmpDevPlanUpdateDto to entity: {}", convertObject);
        EmpDevPlan result = EmpDevPlan.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .devPlanId(convertObject.getDevPlanId())
                .assessmentYear(convertObject.getAssessmentYear())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private EmpDevPlanDto convertToDto(EmpDevPlan convertObject) {
        log.debug("Converting EmpDevPlan entity to DTO: {}", convertObject);
        EmpDevPlanDto result = EmpDevPlanDto.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .devPlanId(convertObject.getDevPlanId())
                .assessmentYear(convertObject.getAssessmentYear())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }
}
