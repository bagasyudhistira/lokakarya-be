package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.groupachievement.*;
import co.id.ogya.lokakarya.services.GroupAchievementServ;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class GroupAchievementServImpl implements GroupAchievementServ {
    @Autowired
    private GroupAchievementRepo groupAchievementRepo;

    @Override
    public List<GroupAchievementDto> getAllGroupAchievement() {
        List<GroupAchievement> listData = groupAchievementRepo.getGroupAchievements();
        List<GroupAchievementDto> listResult = new ArrayList<>();
        for(GroupAchievement data : listData){
            GroupAchievementDto result = convertToDto(data);
            listResult.add(result);
        }
        return listResult;
    }

    @Override
    public GroupAchievementDto getGroupAchievementById(String id) {
        GroupAchievement data = groupAchievementRepo.getGroupAchievementById(id);
        GroupAchievementDto result = convertToDto(data);
        return result;
    }

    @Override
    public GroupAchievementDto createGroupAchievement(GroupAchievementCreateDto groupAchievementCreateDto) {
        GroupAchievement data = convertToEntityCreate(groupAchievementCreateDto);
        GroupAchievement result = groupAchievementRepo.saveGroupAchievement(data);
        return convertToDto(result);
    }

    @Override
    public GroupAchievementDto updateGroupAchievement(GroupAchievementUpdateDto groupAchievementUpdateDto) {
        GroupAchievement data = convertToEntityUpdate(groupAchievementUpdateDto);
        GroupAchievement result = groupAchievementRepo.updateGroupAchievement(data);
        return convertToDto(result);
    }

    @Override
    public boolean deleteGroupAchievement(String id) {
        return groupAchievementRepo.deleteGroupAchievement(id);
    }

    private GroupAchievement convertToEntity(GroupAchievementDto convertObject) {
        GroupAchievement result = GroupAchievement.builder()
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

    private GroupAchievement convertToEntityCreate(GroupAchievementCreateDto convertObject) {
        GroupAchievement result = GroupAchievement.builder()
                .id(convertObject.getId())
                .groupName(convertObject.getGroupName())
                .percentage(convertObject.getPercentage())
                .enabled(convertObject.isEnabled())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private GroupAchievement convertToEntityUpdate(GroupAchievementUpdateDto convertObject) {
        GroupAchievement result = GroupAchievement.builder()
                .id(convertObject.getId())
                .groupName(convertObject.getGroupName())
                .percentage(convertObject.getPercentage())
                .enabled(convertObject.isEnabled())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private GroupAchievementDto convertToDto(GroupAchievement convertObject) {
        GroupAchievementDto result = GroupAchievementDto.builder()
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
