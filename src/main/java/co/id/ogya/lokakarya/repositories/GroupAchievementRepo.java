package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.GroupAchievement;

import java.util.List;

public interface GroupAchievementRepo {
    List<GroupAchievement> getGroupAchievements();

    List<GroupAchievement> getGroupAchievementsPerPage(int page, int pageSize);

    GroupAchievement getGroupAchievementById(String id);

    GroupAchievement saveGroupAchievement(GroupAchievement groupAchievement);

    GroupAchievement updateGroupAchievement(GroupAchievement groupAchievement);

    Boolean deleteGroupAchievement(String id);

    GroupAchievement getGroupAchievementByGroupName(String groupName);

    Long countGroupAchievements();

    List<GroupAchievement> sortGroupAchievements(String order, int page, int pageSize);

    List<GroupAchievement> sorchGroupAchievements(String keyword, String column, String order, int page, int pageSize);
}
