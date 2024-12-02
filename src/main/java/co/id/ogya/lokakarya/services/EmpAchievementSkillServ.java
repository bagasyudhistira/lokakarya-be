package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.empachievementskill.EmpAchievementSkillCreateDto;
import co.id.ogya.lokakarya.dto.empachievementskill.EmpAchievementSkillDto;
import co.id.ogya.lokakarya.dto.empachievementskill.EmpAchievementSkillGetDto;
import co.id.ogya.lokakarya.dto.empachievementskill.EmpAchievementSkillUpdateDto;

import java.util.List;

public interface EmpAchievementSkillServ {
    List<EmpAchievementSkillDto> getAllEmpAchievementSkill();

    EmpAchievementSkillDto getEmpAchievementSkillById(String id);

    List<EmpAchievementSkillGetDto> getAllEmpAchievementSkillGet();

    EmpAchievementSkillGetDto getEmpAchievementSkillGetById(String id);

    List<EmpAchievementSkillGetDto> getAllEmpAchievementSkillGetByUserId(String userId);

    EmpAchievementSkillDto createEmpAchievementSkill(EmpAchievementSkillCreateDto empAchievementSkillCreateDto);

    EmpAchievementSkillDto updateEmpAchievementSkill(EmpAchievementSkillUpdateDto empAchievementSkillUpdateDto);

    boolean deleteEmpAchievementSkill(String id);

    Boolean ifAnyEmpAchievementSkillExist(String userId, String achievementId, int assessmentYear);
}
