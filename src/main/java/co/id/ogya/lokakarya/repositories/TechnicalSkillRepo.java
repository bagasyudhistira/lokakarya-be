package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.TechnicalSkill;

import java.util.List;

public interface TechnicalSkillRepo {
    List<TechnicalSkill> getTechnicalSkills();

    List<TechnicalSkill> getTechnicalSkillsPerPage(int page, int pageSize);

    TechnicalSkill getTechnicalSkillById(String id);

    TechnicalSkill saveTechnicalSkill(TechnicalSkill technicalSkill);

    TechnicalSkill updateTechnicalSkill(TechnicalSkill technicalSkill);

    Boolean deleteTechnicalSkill(String id);

    TechnicalSkill getTechnicalSkillByName(String technicalSkill);

    Long countTechnicalSkills(String keyword);

    List<TechnicalSkill> sortTechnicalSkills(String order, int page, int pageSize);

    List<TechnicalSkill> sorchTechnicalSkills(String keyword, String column, String order, int page, int pageSize);
}
