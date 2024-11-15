package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.groupattitudeskill.GroupAttitudeSkillCreateDto;
import co.id.ogya.lokakarya.dto.groupattitudeskill.GroupAttitudeSkillDto;
import co.id.ogya.lokakarya.dto.groupattitudeskill.GroupAttitudeSkillUpdateDto;

import java.util.List;

public interface GroupAttitudeSkillServ {
    List<GroupAttitudeSkillDto> getAllGroupAttitudeSkill();
    GroupAttitudeSkillDto getGroupAttitudeSkillById(String id);
    GroupAttitudeSkillDto createGroupAttitudeSkill(GroupAttitudeSkillCreateDto groupAttitudeSkillCreateDto);
    GroupAttitudeSkillDto updateGroupAttitudeSkill(GroupAttitudeSkillUpdateDto groupAttitudeSkillUpdateDto);
    boolean deleteGroupAttitudeSkill(String id);

}
