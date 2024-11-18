package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.EmpAttitudeSkill;

import java.util.List;
import java.util.Map;

public interface EmpAttitudeSkillRepo {
    List<EmpAttitudeSkill> getEmpAttitudeSkills();
    EmpAttitudeSkill getEmpAttitudeSkillById(String id);

    List<Map<String,Object>> getEmpAttitudeSkillGets();

    Map<String, Object> getEmpAttitudeSkillGetById(String id);

    EmpAttitudeSkill saveEmpAttitudeSkill(EmpAttitudeSkill empAttitudeSkill);
    EmpAttitudeSkill updateEmpAttitudeSkill(EmpAttitudeSkill empAttitudeSkill);
    Boolean deleteEmpAttitudeSkill(String id);
}
