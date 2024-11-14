package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.groupattitudeskill.*;
import co.id.ogya.lokakarya.services.GroupAttitudeSkillServ;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class GroupAttitudeSkillServImpl implements GroupAttitudeSkillServ {
    @Autowired
    private GroupAttitudeSkillRepo groupAttitudeSkillRepo;

    @Override
    public List<GroupAttitudeSkillDto> getAllGroupAttitudeSkill() {
        List<GroupAttitudeSkill> listData = groupAttitudeSkillRepo.getGroupAttitudeSkills();
        List<GroupAttitudeSkillDto> listResult = new ArrayList<>();
        for(GroupAttitudeSkill data : listData){
            GroupAttitudeSkillDto result = convertToDto(data);
            listResult.add(result);
        }
        return listResult;
    }

    @Override
    public GroupAttitudeSkillDto getGroupAttitudeSkillById(String id) {
        GroupAttitudeSkill data = groupAttitudeSkillRepo.getGroupAttitudeSkillById(id);
        GroupAttitudeSkillDto result = convertToDto(data);
        return result;
    }

    @Override
    public GroupAttitudeSkillDto createGroupAttitudeSkill(GroupAttitudeSkillCreateDto groupAttitudeSkillCreateDto) {
        GroupAttitudeSkill data = convertToEntityCreate(groupAttitudeSkillCreateDto);
        GroupAttitudeSkill result = groupAttitudeSkillRepo.saveGroupAttitudeSkill(data);
        return convertToDto(result);
    }

    @Override
    public GroupAttitudeSkillDto updateGroupAttitudeSkill(GroupAttitudeSkillUpdateDto groupAttitudeSkillUpdateDto) {
        GroupAttitudeSkill data = convertToEntityUpdate(groupAttitudeSkillUpdateDto);
        GroupAttitudeSkill result = groupAttitudeSkillRepo.updateGroupAttitudeSkill(data);
        return convertToDto(result);
    }

    @Override
    public boolean deleteGroupAttitudeSkill(String id) {
        return groupAttitudeSkillRepo.deleteGroupAttitudeSkill(id);
    }

    private GroupAttitudeSkill convertToEntity(GroupAttitudeSkillDto convertObject) {
        GroupAttitudeSkill result = GroupAttitudeSkill.builder()
                .id(convertObject.getId())
                .groupName(convertObject.getGroupName())
                .percentage(convertObject.getPercentage())
                .enabled(convertObject.isEnabled())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private GroupAttitudeSkill convertToEntityCreate(GroupAttitudeSkillCreateDto convertObject) {
        GroupAttitudeSkill result = GroupAttitudeSkill.builder()
                .id(convertObject.getId())
                .groupName(convertObject.getGroupName())
                .percentage(convertObject.getPercentage())
                .enabled(convertObject.isEnabled())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private GroupAttitudeSkill convertToEntityUpdate(GroupAttitudeSkillUpdateDto convertObject) {
        GroupAttitudeSkill result = GroupAttitudeSkill.builder()
                .id(convertObject.getId())
                .groupName(convertObject.getGroupName())
                .percentage(convertObject.getPercentage())
                .enabled(convertObject.isEnabled())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private GroupAttitudeSkillDto convertToDto(GroupAttitudeSkill convertObject) {
        GroupAttitudeSkillDto result = GroupAttitudeSkillDto.builder()
                .id(convertObject.getId())
                .groupName(convertObject.getGroupName())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }
}
