package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.emptechnicalskill.*;
import co.id.ogya.lokakarya.services.EmpTechnicalSkillServ;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class EmpTechnicalSkillServImpl implements EmpTechnicalSkillServ {
    @Autowired
    private EmpTechnicalSkillRepo empTechnicalSkillRepo;

    @Override
    public List<EmpTechnicalSkillDto> getAllEmpTechnicalSkill() {
        List<EmpTechnicalSkill> listData = empTechnicalSkillRepo.getEmpTechnicalSkills();
        List<EmpTechnicalSkillDto> listResult = new ArrayList<>();
        for(EmpTechnicalSkill data : listData){
            EmpTechnicalSkillDto result = convertToDto(data);
            listResult.add(result);
        }
        return listResult;
    }

    @Override
    public EmpTechnicalSkillDto getEmpTechnicalSkillById(String id) {
        EmpTechnicalSkill data = empTechnicalSkillRepo.getEmpTechnicalSkillById(id);
        EmpTechnicalSkillDto result = convertToDto(data);
        return result;
    }

    @Override
    public EmpTechnicalSkillDto createEmpTechnicalSkill(EmpTechnicalSkillCreateDto empTechnicalSkillCreateDto) {
        EmpTechnicalSkill data = convertToEntityCreate(empTechnicalSkillCreateDto);
        EmpTechnicalSkill result = empTechnicalSkillRepo.saveEmpTechnicalSkill(data);
        return convertToDto(result);
    }

    @Override
    public EmpTechnicalSkillDto updateEmpTechnicalSkill(EmpTechnicalSkillUpdateDto empTechnicalSkillUpdateDto) {
        EmpTechnicalSkill data = convertToEntityUpdate(empTechnicalSkillUpdateDto);
        EmpTechnicalSkill result = empTechnicalSkillRepo.updateEmpTechnicalSkill(data);
        return convertToDto(result);
    }

    @Override
    public boolean deleteEmpTechnicalSkill(String id) {
        return empTechnicalSkillRepo.deleteEmpTechnicalSkill(id);
    }

    private EmpTechnicalSkill convertToEntity(EmpTechnicalSkillDto convertObject) {
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
        EmpTechnicalSkill result = EmpTechnicalSkill.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .technicalSkillId(convertObject.getTechnicalSkillId())
                .score(convertObject.getScore())
                .assessmentYear(convertObject.getAssessmentYear())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private EmpTechnicalSkill convertToEntityUpdate(EmpTechnicalSkillUpdateDto convertObject) {
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
