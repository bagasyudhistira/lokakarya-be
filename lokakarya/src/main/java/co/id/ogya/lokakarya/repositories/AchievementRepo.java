package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.Achievement;

import java.util.List;
import java.util.Map;

public interface AchievementRepo {
    List<Achievement> getAchievements();
    Achievement getAchievementById(String id);

    List<Map<String,Object>> getAchievementGets();

    Map<String, Object> getAchievementGetById(String id);

    Achievement saveAchievement(Achievement achievement);
    Achievement updateAchievement(Achievement achievement);
    Boolean deleteAchievement(String id);
}
