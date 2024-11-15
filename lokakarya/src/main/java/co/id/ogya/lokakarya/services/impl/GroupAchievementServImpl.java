package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.groupachievement.GroupAchievementCreateDto;
import co.id.ogya.lokakarya.dto.groupachievement.GroupAchievementDto;
import co.id.ogya.lokakarya.dto.groupachievement.GroupAchievementUpdateDto;
import co.id.ogya.lokakarya.entities.GroupAchievement;
import co.id.ogya.lokakarya.repositories.GroupAchievementRepo;
import co.id.ogya.lokakarya.services.GroupAchievementServ;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class GroupAchievementServImpl implements GroupAchievementServ {
    @Autowired
    private GroupAchievementRepo groupAchievementRepo;

    @Override
    public List<GroupAchievementDto> getAllGroupAchievement() {
        log.info("Attempting to fetch all GroupAchievements");
        List<GroupAchievementDto> listResult = new ArrayList<>();
        try {
            List<GroupAchievement> listData = groupAchievementRepo.getGroupAchievements();
            log.debug("Fetched {} GroupAchievements from repository", listData.size());
            for (GroupAchievement data : listData) {
                GroupAchievementDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all GroupAchievements: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public GroupAchievementDto getGroupAchievementById(String id) {
        log.info("Attempting to fetch GroupAchievement by ID: {}", id);
        GroupAchievementDto result = null;
        try {
            GroupAchievement data = groupAchievementRepo.getGroupAchievementById(id);
            result = convertToDto(data);
            log.debug("Fetched GroupAchievement: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching GroupAchievement by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public GroupAchievementDto createGroupAchievement(GroupAchievementCreateDto groupAchievementCreateDto) {
        log.info("Attempting to create a new GroupAchievement with data: {}", groupAchievementCreateDto);
        GroupAchievementDto result = null;
        try {
            GroupAchievement data = convertToEntityCreate(groupAchievementCreateDto);
            GroupAchievement savedData = groupAchievementRepo.saveGroupAchievement(data);
            result = convertToDto(savedData);
            log.info("Successfully created GroupAchievement: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while creating GroupAchievement: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public GroupAchievementDto updateGroupAchievement(GroupAchievementUpdateDto groupAchievementUpdateDto) {
        log.info("Attempting to update GroupAchievement with data: {}", groupAchievementUpdateDto);
        GroupAchievementDto result = null;
        try {
            GroupAchievement data = convertToEntityUpdate(groupAchievementUpdateDto);
            GroupAchievement updatedData = groupAchievementRepo.updateGroupAchievement(data);
            result = convertToDto(updatedData);
            log.info("Successfully updated GroupAchievement: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while updating GroupAchievement: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean deleteGroupAchievement(String id) {
        log.info("Attempting to delete GroupAchievement with ID: {}", id);
        boolean isDeleted = false;
        try {
            isDeleted = groupAchievementRepo.deleteGroupAchievement(id);
            if (isDeleted) {
                log.info("Successfully deleted GroupAchievement with ID: {}", id);
            } else {
                log.warn("Failed to delete GroupAchievement with ID: {}. It might not exist.", id);
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting GroupAchievement with ID {}: {}", id, e.getMessage(), e);
        }
        return isDeleted;
    }

    private GroupAchievement convertToEntity(GroupAchievementDto convertObject) {
        log.debug("Converting GroupAchievementDto to entity: {}", convertObject);
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
        log.debug("Converting GroupAchievementCreateDto to entity: {}", convertObject);
        GroupAchievement result = GroupAchievement.builder()
                .groupName(convertObject.getGroupName())
                .percentage(convertObject.getPercentage())
                .enabled(convertObject.isEnabled())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private GroupAchievement convertToEntityUpdate(GroupAchievementUpdateDto convertObject) {
        log.debug("Converting GroupAchievementUpdateDto to entity: {}", convertObject);
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
        log.debug("Converting GroupAchievement entity to DTO: {}", convertObject);
        GroupAchievementDto result = GroupAchievementDto.builder()
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
}
