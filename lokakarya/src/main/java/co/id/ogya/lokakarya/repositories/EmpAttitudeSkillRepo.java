package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.EmpAttitudeSkill;

import java.util.List;

public interface EmpAttitudeSkillRepo {
    List<EmpAttitudeSkill> getEmpAttitudeSkills();
    EmpAttitudeSkill getEmpAttitudeSkillById(String id);
    EmpAttitudeSkill saveEmpAttitudeSkill(EmpAttitudeSkill empAttitudeSkill);
    EmpAttitudeSkill updateEmpAttitudeSkill(EmpAttitudeSkill empAttitudeSkill);
    Boolean deleteEmpAttitudeSkill(String id);
}
