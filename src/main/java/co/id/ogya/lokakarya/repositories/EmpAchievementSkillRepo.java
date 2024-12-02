package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.EmpAchievementSkill;

import java.util.List;
import java.util.Map;

public interface EmpAchievementSkillRepo {
    List<EmpAchievementSkill> getEmpAchievementSkills();

    List<Map<String,Object>> getEmpAchievementSkillGets();

    EmpAchievementSkill getEmpAchievementSkillById(String id);

    Map<String, Object> getEmpAchievementSkillGetById(String id);

    List<Map<String, Object>> getEmpAchievementSkillGetsByUserId(String id);

    EmpAchievementSkill saveEmpAchievementSkill(EmpAchievementSkill empAchievementSkill);

    EmpAchievementSkill updateEmpAchievementSkill(EmpAchievementSkill empAchievementSkill);

    Boolean deleteEmpAchievementSkill(String id);
}
