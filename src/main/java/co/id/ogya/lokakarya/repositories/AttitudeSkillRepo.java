package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.Achievement;
import co.id.ogya.lokakarya.entities.AttitudeSkill;

import java.util.List;
import java.util.Map;

public interface AttitudeSkillRepo {
    List<AttitudeSkill> getAttitudeSkills();

    AttitudeSkill getAttitudeSkillById(String id);

    List<Map<String,Object>> getAttitudeSkillGets();

    Map<String, Object> getAttitudeSkillGetById(String id);

    AttitudeSkill saveAttitudeSkill(AttitudeSkill attitudeSkill);

    AttitudeSkill updateAttitudeSkill(AttitudeSkill attitudeSkill);

    Boolean deleteAttitudeSkill(String id);

    AttitudeSkill getAttitudeSkillByName(String attitudeSkillName);
}
