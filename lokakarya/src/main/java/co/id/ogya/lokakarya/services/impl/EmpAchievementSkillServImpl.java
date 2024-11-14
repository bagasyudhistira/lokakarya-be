package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.empachievementskill.*;
import co.id.ogya.lokakarya.services.EmpAchievementSkillServ;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class EmpAchievementSkillServImpl implements EmpAchievementSkillServ {
    @Autowired
    private EmpAchievementSkillRepo empAchievementSkillRepo;

    @Override
    public List<EmpAchievementSkillDto> getAllEmpAchievementSkill() {
        List<EmpAchievementSkill> listData = empAchievementSkillRepo.getEmpAchievementSkills();
        List<EmpAchievementSkillDto> listResult = new ArrayList<>();
        for(EmpAchievementSkill data : listData){
            EmpAchievementSkillDto result = convertToDto(data);
            listResult.add(result);
        }
        return listResult;
    }

    @Override
    public EmpAchievementSkillDto getEmpAchievementSkillById(String id) {
        EmpAchievementSkill data = empAchievementSkillRepo.getEmpAchievementSkillById(id);
        EmpAchievementSkillDto result = convertToDto(data);
        return result;
    }

    @Override
    public EmpAchievementSkillDto createEmpAchievementSkill(EmpAchievementSkillCreateDto empAchievementSkillCreateDto) {
        EmpAchievementSkill data = convertToEntityCreate(empAchievementSkillCreateDto);
        EmpAchievementSkill result = empAchievementSkillRepo.saveEmpAchievementSkill(data);
        return convertToDto(result);
    }

    @Override
    public EmpAchievementSkillDto updateEmpAchievementSkill(EmpAchievementSkillUpdateDto empAchievementSkillUpdateDto) {
        EmpAchievementSkill data = convertToEntityUpdate(empAchievementSkillUpdateDto);
        EmpAchievementSkill result = empAchievementSkillRepo.updateEmpAchievementSkill(data);
        return convertToDto(result);
    }

    @Override
    public boolean deleteEmpAchievementSkill(String id) {
        return empAchievementSkillRepo.deleteEmpAchievementSkill(id);
    }

    private EmpAchievementSkill convertToEntity(EmpAchievementSkillDto convertObject) {
        EmpAchievementSkill result = EmpAchievementSkill.builder()
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

    private EmpAchievementSkill convertToEntityCreate(EmpAchievementSkillCreateDto convertObject) {
        EmpAchievementSkill result = EmpAchievementSkill.builder()
                .id(convertObject.getId())
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
