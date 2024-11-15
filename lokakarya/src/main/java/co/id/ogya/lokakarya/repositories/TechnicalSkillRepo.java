package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.TechnicalSkill;

import java.util.List;

public interface TechnicalSkillRepo {
    List<TechnicalSkill> getTechnicalSkills();
    TechnicalSkill getTechnicalSkillById(String id);
    TechnicalSkill saveTechnicalSkill(TechnicalSkill technicalSkill);
    TechnicalSkill updateTechnicalSkill(TechnicalSkill technicalSkill);
    Boolean deleteTechnicalSkill(String id);
}
