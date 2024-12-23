package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.achievement.AchievementCreateDto;
import co.id.ogya.lokakarya.dto.achievement.AchievementDto;
import co.id.ogya.lokakarya.dto.achievement.AchievementGetDto;
import co.id.ogya.lokakarya.dto.achievement.AchievementUpdateDto;

import java.util.List;

public interface AchievementServ {
    List<AchievementDto> getAllAchievement();

    AchievementDto getAchievementById(String id);

    List<AchievementGetDto> getAllAchievementGet();

    List<AchievementGetDto> getAllAchievementGetPerPage(int page, int pageSize);

    AchievementGetDto getAchievementGetById(String id);

    AchievementDto createAchievement(AchievementCreateDto achievementCreateDto);

    AchievementDto updateAchievement(AchievementUpdateDto achievementUpdateDto);

    boolean deleteAchievement(String id);

    AchievementDto getAchievementByName(String achievementName);

    Long countAllAchievement(String keyword);

    List<AchievementGetDto> sortAllAchievementGetOrderBy(String column, String order, int page, int pageSize);

    List<AchievementGetDto> sorchAllAchievementGet(String keyword, String column, String order, int page, int pageSize);
}
