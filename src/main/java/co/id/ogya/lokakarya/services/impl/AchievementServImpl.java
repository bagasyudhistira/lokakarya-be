package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.achievement.AchievementCreateDto;
import co.id.ogya.lokakarya.dto.achievement.AchievementDto;
import co.id.ogya.lokakarya.dto.achievement.AchievementGetDto;
import co.id.ogya.lokakarya.dto.achievement.AchievementUpdateDto;
import co.id.ogya.lokakarya.entities.Achievement;
import co.id.ogya.lokakarya.repositories.AchievementRepo;
import co.id.ogya.lokakarya.services.AchievementServ;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackOn = Exception.class)
@Slf4j
public class AchievementServImpl implements AchievementServ {
    @Autowired
    private AchievementRepo achievementRepo;

    @Override
    public List<AchievementDto> getAllAchievement() {
        log.info("Attempting to fetch all achievements");
        List<AchievementDto> listResult = new ArrayList<>();
        try {
            List<Achievement> listData = achievementRepo.getAchievements();
            log.debug("Fetched {} achievements from repository", listData.size());
            for (Achievement data : listData) {
                AchievementDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all achievements: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public AchievementDto getAchievementById(String id) {
        log.info("Attempting to fetch achievement by ID: {}", id);
        AchievementDto result = null;
        try {
            Achievement data = achievementRepo.getAchievementById(id);
            result = convertToDto(data);
            log.debug("Fetched achievement: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching achievement by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }


    @Override
    public List<AchievementGetDto> getAllAchievementGet() {
        log.info("Attempting to fetch all achievements");
        List<AchievementGetDto> listResult = new ArrayList<>();
        try {
            List<Map<String, Object>> listData = achievementRepo.getAchievementGets();
            log.debug("Fetched {} achievements from repository", listData.size());
            for (Map<String, Object> data : listData) {
                AchievementGetDto result = AchievementGetDto.mapToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all achievements: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public List<AchievementGetDto> getAllAchievementGetPerPage(int page, int pageSize) {
        log.info("Attempting to fetch all Achievements");
        List<AchievementGetDto> listResult = new ArrayList<>();
        try {
            List<Map<String, Object>> listData = achievementRepo.getAchievementGetsPerPage(page, pageSize);
            log.debug("Fetched {} Achievements from repository", listData.size());
            for (Map<String, Object> data : listData) {
                AchievementGetDto result = AchievementGetDto.mapToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all Achievements: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public AchievementGetDto getAchievementGetById(String id) {
        log.info("Attempting to fetch achievement by ID: {}", id);
        AchievementGetDto result = null;
        try {
            Map<String, Object> data = achievementRepo.getAchievementGetById(id);
            result = AchievementGetDto.mapToDto(data);
            log.debug("Fetched achievement: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching achievement by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public AchievementDto createAchievement(AchievementCreateDto achievementCreateDto) {
        log.info("Attempting to create a new achievement with data: {}", achievementCreateDto);
        AchievementDto result = null;
        try {
            Achievement data = convertToEntityCreate(achievementCreateDto);
            Achievement savedData = achievementRepo.saveAchievement(data);
            result = convertToDto(savedData);
            log.info("Successfully created achievement: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while creating achievement: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public AchievementDto updateAchievement(AchievementUpdateDto achievementUpdateDto) {
        log.info("Attempting to update achievement with data: {}", achievementUpdateDto);
        AchievementDto result = null;
        try {
            Achievement data = convertToEntityUpdate(achievementUpdateDto);
            Achievement updatedData = achievementRepo.updateAchievement(data);
            result = convertToDto(updatedData);
            log.info("Successfully updated achievement: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while updating achievement: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean deleteAchievement(String id) {
        log.info("Attempting to delete achievement with ID: {}", id);
        boolean isDeleted = false;
        try {
            isDeleted = achievementRepo.deleteAchievement(id);
            if (isDeleted) {
                log.info("Successfully deleted achievement with ID: {}", id);
            } else {
                log.warn("Failed to delete achievement with ID: {}. Achievement might not exist.", id);
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting achievement with ID {}: {}", id, e.getMessage(), e);
        }
        return isDeleted;
    }

    @Override
    public AchievementDto getAchievementByName(String achievementName) {
        log.info("Attempting to fetch achievement by Achievement Name: {}", achievementName);
        AchievementDto result = null;
        try {
            Achievement data = achievementRepo.getAchievementByName(achievementName);
            result = convertToDto(data);
            log.debug("Fetched achievement: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching achievement by Achievement Name {}: {}", achievementName, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Long countAllAchievement(String keyword) {
        try {
            log.info("Fetching total count of all Achievements from repository");
            Long totalAchievements = achievementRepo.countAchievement(keyword);
            log.info("Successfully fetched total Achievements count: {}", totalAchievements);
            return totalAchievements;
        } catch (Exception e) {
            log.error("Error occurred while fetching total Achievements count: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch Achievements count", e);
        }
    }

    @Override
    public List<AchievementGetDto> sortAllAchievementGetOrderBy(String column, String order, int page, int pageSize) {
        log.info("Attempting to sort all Achievements order by {} {}", column, order);
        List<AchievementGetDto> listResult = new ArrayList<>();
        try {
            List<Map<String, Object>> listData = achievementRepo.sortAchievementGetsOrderBy(column, order, page, pageSize);
            log.debug("Sorted {} Achievements from repository order by {}", listData.size(), column);
            for (Map<String, Object> data : listData) {
                AchievementGetDto result = AchievementGetDto.mapToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while sorting all Achievements: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public List<AchievementGetDto> sorchAllAchievementGet(String keyword, String order, String column, int page, int pageSize) {
        log.info("Attempting to sorch all Achievements using keyword: {}", keyword);
        List<AchievementGetDto> listResult = new ArrayList<>();
        try {
            List<Map<String, Object>> listData = achievementRepo.sorchAchievementGets(keyword, order, column, page, pageSize);
            log.debug("Sorched {} Achievements from repository order using keyword: {}", listData.size(), keyword);
            for (Map<String, Object> data : listData) {
                AchievementGetDto result = AchievementGetDto.mapToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while sorching all Achievements: {}", e.getMessage(), e);
        }
        return listResult;
    }

    private Achievement convertToEntityCreate(AchievementCreateDto convertObject) {
        log.debug("Converting AchievementCreateDto to entity: {}", convertObject);
        Achievement result = Achievement.builder()
                .achievement(convertObject.getAchievement())
                .groupId(convertObject.getGroupId())
                .enabled(convertObject.isEnabled())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private Achievement convertToEntityUpdate(AchievementUpdateDto convertObject) {
        log.debug("Converting AchievementUpdateDto to entity: {}", convertObject);
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
        log.debug("Converting Achievement entity to DTO: {}", convertObject);
        AchievementDto result = AchievementDto.builder()
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
}
