package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.GroupAchievement;

import java.util.List;

public interface GroupAchievementRepo {
    List<GroupAchievement> getGroupAchievements();

    GroupAchievement getGroupAchievementById(String id);

    GroupAchievement saveGroupAchievement(GroupAchievement groupAchievement);

    GroupAchievement updateGroupAchievement(GroupAchievement groupAchievement);

    Boolean deleteGroupAchievement(String id);

    GroupAchievement getGroupAchievementByGroupName(String groupName);
}
