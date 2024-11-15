package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.AttitudeSkill;

import java.util.List;

public interface AttitudeSkillRepo {
    List<AttitudeSkill> getAttitudeSkills();
    AttitudeSkill getAttitudeSkillById(String id);
    AttitudeSkill saveAttitudeSkill(AttitudeSkill attitudeSkill);
    AttitudeSkill updateAttitudeSkill(AttitudeSkill attitudeSkill);
    Boolean deleteAttitudeSkill(String id);
}
