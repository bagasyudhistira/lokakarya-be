package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.EmpTechnicalSkill;

import java.util.List;
import java.util.Map;

public interface EmpTechnicalSkillRepo {
    List<EmpTechnicalSkill> getEmpTechnicalSkills();
    EmpTechnicalSkill getEmpTechnicalSkillById(String id);
    EmpTechnicalSkill saveEmpTechnicalSkill(EmpTechnicalSkill empTechnicalSkill);
    EmpTechnicalSkill updateEmpTechnicalSkill(EmpTechnicalSkill empTechnicalSkill);
    Boolean deleteEmpTechnicalSkill(String id);
    List<Map<String, Object>> getEmpTechnicalSkillGets();
    List<Map<String, Object>> getEmpTechnicalSkillGetByUserId(String userId);

}
