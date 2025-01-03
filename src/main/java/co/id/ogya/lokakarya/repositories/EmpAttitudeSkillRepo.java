package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.EmpAttitudeSkill;

import java.util.List;
import java.util.Map;

public interface EmpAttitudeSkillRepo {
    List<EmpAttitudeSkill> getEmpAttitudeSkills();

    EmpAttitudeSkill getEmpAttitudeSkillById(String id);

    List<Map<String, Object>> getEmpAttitudeSkillGets();

    Map<String, Object> getEmpAttitudeSkillGetById(String id);

    List<Map<String, Object>> getEmpAttitudeSkillGetsByUserId(String userId);

    EmpAttitudeSkill saveEmpAttitudeSkill(EmpAttitudeSkill empAttitudeSkill);

    EmpAttitudeSkill updateEmpAttitudeSkill(EmpAttitudeSkill empAttitudeSkill);

    Boolean deleteEmpAttitudeSkill(String id);

    Boolean ifAnyEmpAttitudeSkillExist(String userId, String attitudeSkillId, int assessmentYear);

    List<Map<String, Object>> getEmpAttitudeSkillGetsByUserIdAssessmentYear(String userId, int assessmentYear);
}
