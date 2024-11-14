package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.empattitudeskill.*;
import co.id.ogya.lokakarya.services.EmpAttitudeSkillServ;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class EmpAttitudeSkillServImpl implements EmpAttitudeSkillServ {
    @Autowired
    private EmpAttitudeSkillRepo empAttitudeSkillRepo;

    @Override
    public List<EmpAttitudeSkillDto> getAllEmpAttitudeSkill() {
        List<EmpAttitudeSkill> listData = empAttitudeSkillRepo.getEmpAttitudeSkills();
        List<EmpAttitudeSkillDto> listResult = new ArrayList<>();
        for(EmpAttitudeSkill data : listData){
            EmpAttitudeSkillDto result = convertToDto(data);
            listResult.add(result);
        }
        return listResult;
    }

    @Override
    public EmpAttitudeSkillDto getEmpAttitudeSkillById(String id) {
        EmpAttitudeSkill data = empAttitudeSkillRepo.getEmpAttitudeSkillById(id);
        EmpAttitudeSkillDto result = convertToDto(data);
        return result;
    }

    @Override
    public EmpAttitudeSkillDto createEmpAttitudeSkill(EmpAttitudeSkillCreateDto empAttitudeSkillCreateDto) {
        EmpAttitudeSkill data = convertToEntityCreate(empAttitudeSkillCreateDto);
        EmpAttitudeSkill result = empAttitudeSkillRepo.saveEmpAttitudeSkill(data);
        return convertToDto(result);
    }

    @Override
    public EmpAttitudeSkillDto updateEmpAttitudeSkill(EmpAttitudeSkillUpdateDto empAttitudeSkillUpdateDto) {
        EmpAttitudeSkill data = convertToEntityUpdate(empAttitudeSkillUpdateDto);
        EmpAttitudeSkill result = empAttitudeSkillRepo.updateEmpAttitudeSkill(data);
        return convertToDto(result);
    }

    @Override
    public boolean deleteEmpAttitudeSkill(String id) {
        return empAttitudeSkillRepo.deleteEmpAttitudeSkill(id);
    }

    private EmpAttitudeSkill convertToEntity(EmpAttitudeSkillDto convertObject) {
        EmpAttitudeSkill result = EmpAttitudeSkill.builder()
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

    private EmpAttitudeSkill convertToEntityCreate(EmpAttitudeSkillCreateDto convertObject) {
        EmpAttitudeSkill result = EmpAttitudeSkill.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .attitudeSkillId(convertObject.getAttitudeSkillId())
                .score(convertObject.getScore())
                .assessmentYear(convertObject.getAssessmentYear())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private EmpAttitudeSkill convertToEntityUpdate(EmpAttitudeSkillUpdateDto convertObject) {
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
