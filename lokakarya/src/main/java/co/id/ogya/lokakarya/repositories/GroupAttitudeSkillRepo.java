package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.GroupAttitudeSkill;

import java.util.List;

public interface GroupAttitudeSkillRepo {
    List<GroupAttitudeSkill> getGroupAttitudeSkills();
    GroupAttitudeSkill getGroupAttitudeSkillById(String id);
    GroupAttitudeSkill saveGroupAttitudeSkill(GroupAttitudeSkill groupAttitudeSkill);
    GroupAttitudeSkill updateGroupAttitudeSkill(GroupAttitudeSkill groupAttitudeSkill);
    Boolean deleteGroupAttitudeSkill(String id);
}
