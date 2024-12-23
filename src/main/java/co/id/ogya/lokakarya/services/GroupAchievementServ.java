package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.groupachievement.GroupAchievementCreateDto;
import co.id.ogya.lokakarya.dto.groupachievement.GroupAchievementDto;
import co.id.ogya.lokakarya.dto.groupachievement.GroupAchievementUpdateDto;

import java.util.List;

public interface GroupAchievementServ {
    List<GroupAchievementDto> getAllGroupAchievement();

    List<GroupAchievementDto> getAllGroupAchievementPerPage(int page, int pageSize);

    GroupAchievementDto getGroupAchievementById(String id);

    GroupAchievementDto createGroupAchievement(GroupAchievementCreateDto groupAchievementCreateDto);

    GroupAchievementDto updateGroupAchievement(GroupAchievementUpdateDto groupAchievementUpdateDto);

    boolean deleteGroupAchievement(String id);

    GroupAchievementDto getGroupAchievementByGroupName(String groupName);

    Long countAllGroupAchievement();

    List<GroupAchievementDto> sortAllGroupAchievement(String order, int page, int pageSize);

    List<GroupAchievementDto> searchAllGroupAchievement(String keyword, int page, int pageSize);
}
