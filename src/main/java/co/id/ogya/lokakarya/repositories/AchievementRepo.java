package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.Achievement;

import java.util.List;
import java.util.Map;

public interface AchievementRepo {
    List<Achievement> getAchievements();

    Achievement getAchievementById(String id);

    List<Map<String,Object>> getAchievementGets();

    List<Map<String, Object>> getAchievementGetsPerPage(int page, int pageSize);

    Map<String, Object> getAchievementGetById(String id);

    Achievement saveAchievement(Achievement achievement);

    Achievement updateAchievement(Achievement achievement);

    Boolean deleteAchievement(String id);

    Achievement getAchievementByName(String achievementName);

    Long countAchievement();

    List<Map<String, Object>> sortAchievementGetsOrderBy(String column, String order, int page, int pageSize);

    List<Map<String, Object>> sorchAchievementGets(String keyword, String column, String order, int page, int pageSize);
}
