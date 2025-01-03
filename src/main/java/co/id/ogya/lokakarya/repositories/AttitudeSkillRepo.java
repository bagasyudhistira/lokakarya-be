package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.AttitudeSkill;

import java.util.List;
import java.util.Map;

public interface AttitudeSkillRepo {
    List<AttitudeSkill> getAttitudeSkills();

    AttitudeSkill getAttitudeSkillById(String id);

    List<Map<String, Object>> getAttitudeSkillGets();

    List<Map<String, Object>> getAttitudeSkillGetsPerPage(int page, int pageSize);

    Map<String, Object> getAttitudeSkillGetById(String id);

    AttitudeSkill saveAttitudeSkill(AttitudeSkill attitudeSkill);

    AttitudeSkill updateAttitudeSkill(AttitudeSkill attitudeSkill);

    Boolean deleteAttitudeSkill(String id);

    AttitudeSkill getAttitudeSkillByName(String attitudeSkillName);

    Long countAttitudeSkills(String keyword);

    List<Map<String, Object>> sortAttitudeSkillGetsOrderBy(String column, String order, int page, int pageSize);

    List<Map<String, Object>> sorchAttitudeSkillGets(String keyword, String column, String order, int page, int pageSize);
}
