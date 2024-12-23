package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.GroupAttitudeSkill;

import java.util.List;

public interface GroupAttitudeSkillRepo {
    List<GroupAttitudeSkill> getGroupAttitudeSkills();

    List<GroupAttitudeSkill> getGroupAttitudeSkillsPerPage(int page, int pageSize);

    GroupAttitudeSkill getGroupAttitudeSkillById(String id);

    GroupAttitudeSkill saveGroupAttitudeSkill(GroupAttitudeSkill groupAttitudeSkill);

    GroupAttitudeSkill updateGroupAttitudeSkill(GroupAttitudeSkill groupAttitudeSkill);

    Boolean deleteGroupAttitudeSkill(String id);

    GroupAttitudeSkill getGroupAttitudeSkillByGroupName(String groupName);

    Long countGroupAttitudeSkills();

    List<GroupAttitudeSkill> sortGroupAttitudeSkills(String order, int page, int pageSize);

    List<GroupAttitudeSkill> sorchGroupAttitudeSkills(String keyword, String column, String order, int page, int pageSize);
}
