package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.technicalskill.TechnicalSkillCreateDto;
import co.id.ogya.lokakarya.dto.technicalskill.TechnicalSkillDto;
import co.id.ogya.lokakarya.dto.technicalskill.TechnicalSkillUpdateDto;

import java.util.List;

public interface TechnicalSkillServ {
    List<TechnicalSkillDto> getAllTechnicalSkill();
    TechnicalSkillDto getTechnicalSkillById(String id);
    TechnicalSkillDto createTechnicalSkill(TechnicalSkillCreateDto technicalSkillCreateDto);
    TechnicalSkillDto updateTechnicalSkill(TechnicalSkillUpdateDto technicalSkillUpdateDto);
    boolean deleteTechnicalSkill(String id);

}
