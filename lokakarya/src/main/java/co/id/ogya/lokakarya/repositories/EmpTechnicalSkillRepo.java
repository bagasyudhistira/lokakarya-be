package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.EmpTechnicalSkill;

import java.util.List;

public interface EmpTechnicalSkillRepo {
    List<EmpTechnicalSkill> getEmpTechnicalSkills();
    EmpTechnicalSkill getEmpTechnicalSkillById(String id);
    EmpTechnicalSkill saveEmpTechnicalSkill(EmpTechnicalSkill empTechnicalSkill);
    EmpTechnicalSkill updateEmpTechnicalSkill(EmpTechnicalSkill empTechnicalSkill);
    Boolean deleteEmpTechnicalSkill(String id);
}
