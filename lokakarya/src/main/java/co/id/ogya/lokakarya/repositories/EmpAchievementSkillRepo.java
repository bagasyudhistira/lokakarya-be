package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.EmpAchievementSkill;

import java.util.List;

public interface EmpAchievementSkillRepo {
    List<EmpAchievementSkill> getEmpAchievementSkills();
    EmpAchievementSkill getEmpAchievementSkillById(String id);
    EmpAchievementSkill saveEmpAchievementSkill(EmpAchievementSkill empAchievementSkill);
    EmpAchievementSkill updateEmpAchievementSkill(EmpAchievementSkill empAchievementSkill);
    Boolean deleteEmpAchievementSkill(String id);
}
