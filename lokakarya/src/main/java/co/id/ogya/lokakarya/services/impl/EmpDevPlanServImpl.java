package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.empdevplan.*;
import co.id.ogya.lokakarya.services.EmpDevPlanServ;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class EmpDevPlanServImpl implements EmpDevPlanServ {
    @Autowired
    private EmpDevPlanRepo empDevPlanRepo;

    @Override
    public List<EmpDevPlanDto> getAllEmpDevPlan() {
        List<EmpDevPlan> listData = empDevPlanRepo.getEmpDevPlans();
        List<EmpDevPlanDto> listResult = new ArrayList<>();
        for(EmpDevPlan data : listData){
            EmpDevPlanDto result = convertToDto(data);
            listResult.add(result);
        }
        return listResult;
    }

    @Override
    public EmpDevPlanDto getEmpDevPlanById(String id) {
        EmpDevPlan data = empDevPlanRepo.getEmpDevPlanById(id);
        EmpDevPlanDto result = convertToDto(data);
        return result;
    }

    @Override
    public EmpDevPlanDto createEmpDevPlan(EmpDevPlanCreateDto empDevPlanCreateDto) {
        EmpDevPlan data = convertToEntityCreate(empDevPlanCreateDto);
        EmpDevPlan result = empDevPlanRepo.saveEmpDevPlan(data);
        return convertToDto(result);
    }

    @Override
    public EmpDevPlanDto updateEmpDevPlan(EmpDevPlanUpdateDto empDevPlanUpdateDto) {
        EmpDevPlan data = convertToEntityUpdate(empDevPlanUpdateDto);
        EmpDevPlan result = empDevPlanRepo.updateEmpDevPlan(data);
        return convertToDto(result);
    }

    @Override
    public boolean deleteEmpDevPlan(String id) {
        return empDevPlanRepo.deleteEmpDevPlan(id);
    }

    private EmpDevPlan convertToEntity(EmpDevPlanDto convertObject) {
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
        EmpDevPlan result = EmpDevPlan.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .devPlanId(convertObject.getDevPlanId())
                .assessmentYear(convertObject.getAssessmentYear())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private EmpDevPlan convertToEntityUpdate(EmpDevPlanUpdateDto convertObject) {
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
