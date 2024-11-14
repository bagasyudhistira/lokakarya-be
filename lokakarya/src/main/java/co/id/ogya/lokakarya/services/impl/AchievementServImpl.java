package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.achievement.*;
import co.id.ogya.lokakarya.services.AchievementServ;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class AchievementServImpl implements AchievementServ {
    @Autowired
    private AchievementRepo achievementRepo;

    @Override
    public List<AchievementDto> getAllAchievement() {
        List<Achievement> listData = achievementRepo.getAchievements();
        List<AchievementDto> listResult = new ArrayList<>();
        for(Achievement data : listData){
            AchievementDto result = convertToDto(data);
            listResult.add(result);
        }
        return listResult;
    }

    @Override
    public AchievementDto getAchievementById(String id) {
        Achievement data = achievementRepo.getAchievementById(id);
        AchievementDto result = convertToDto(data);
        return result;
    }

    @Override
    public AchievementDto createAchievement(AchievementCreateDto achievementCreateDto) {
        Achievement data = convertToEntityCreate(achievementCreateDto);
        Achievement result = achievementRepo.saveAchievement(data);
        return convertToDto(result);
    }

    @Override
    public AchievementDto updateAchievement(AchievementUpdateDto achievementUpdateDto) {
        Achievement data = convertToEntityUpdate(achievementUpdateDto);
        Achievement result = achievementRepo.updateAchievement(data);
        return convertToDto(result);
    }

    @Override
    public boolean deleteAchievement(String id) {
        return achievementRepo.deleteAchievement(id);
    }

    private Achievement convertToEntity(AchievementDto convertObject) {
        Achievement result = Achievement.builder()
                .id(convertObject.getId())
                .achievement(convertObject.getAchievement())
                .groupId(convertObject.getGroupId())
                .enabled(convertObject.isEnabled())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private Achievement convertToEntityCreate(AchievementCreateDto convertObject) {
        Achievement result = Achievement.builder()
                .id(convertObject.getId())
                .achievement(convertObject.getAchievement())
                .groupId(convertObject.getGroupId())
                .enabled(convertObject.isEnabled())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private Achievement convertToEntityUpdate(AchievementUpdateDto convertObject) {
        Achievement result = Achievement.builder()
                .id(convertObject.getId())
                .achievement(convertObject.getAchievement())
                .groupId(convertObject.getGroupId())
                .enabled(convertObject.isEnabled())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private AchievementDto convertToDto(Achievement convertObject) {
        AchievementDto result = AchievementDto.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .divisionId(convertObject.getDivisionId())
                .build();
        return result;
    }
}
