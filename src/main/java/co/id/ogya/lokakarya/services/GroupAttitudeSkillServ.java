package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.groupattitudeskill.GroupAttitudeSkillCreateDto;
import co.id.ogya.lokakarya.dto.groupattitudeskill.GroupAttitudeSkillDto;
import co.id.ogya.lokakarya.dto.groupattitudeskill.GroupAttitudeSkillUpdateDto;

import java.util.List;

public interface GroupAttitudeSkillServ {
    List<GroupAttitudeSkillDto> getAllGroupAttitudeSkill();

    List<GroupAttitudeSkillDto> getAllGroupAttitudeSkillPerPage(int page, int pageSize);

    GroupAttitudeSkillDto getGroupAttitudeSkillById(String id);

    GroupAttitudeSkillDto createGroupAttitudeSkill(GroupAttitudeSkillCreateDto groupAttitudeSkillCreateDto);

    GroupAttitudeSkillDto updateGroupAttitudeSkill(GroupAttitudeSkillUpdateDto groupAttitudeSkillUpdateDto);

    boolean deleteGroupAttitudeSkill(String id);

    GroupAttitudeSkillDto getGroupAttitudeSkillByGroupName(String groupName);

    Long countAllGroupAttitudeSkill(String keyword);

    List<GroupAttitudeSkillDto> sortAllGroupAttitudeSkill(String order, int page, int pageSize);

    List<GroupAttitudeSkillDto> sorchAllGroupAttitudeSkill(String keyword, String column, String order, int page, int pageSize);
}
