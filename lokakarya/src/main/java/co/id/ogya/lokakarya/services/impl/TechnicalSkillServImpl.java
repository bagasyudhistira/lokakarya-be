package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.technicalskill.*;
import co.id.ogya.lokakarya.services.TechnicalSkillServ;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class TechnicalSkillServImpl implements TechnicalSkillServ {
    @Autowired
    private TechnicalSkillRepo technicalSkillRepo;

    @Override
    public List<TechnicalSkillDto> getAllTechnicalSkill() {
        List<TechnicalSkill> listData = technicalSkillRepo.getTechnicalSkills();
        List<TechnicalSkillDto> listResult = new ArrayList<>();
        for(TechnicalSkill data : listData){
            TechnicalSkillDto result = convertToDto(data);
            listResult.add(result);
        }
        return listResult;
    }

    @Override
    public TechnicalSkillDto getTechnicalSkillById(String id) {
        TechnicalSkill data = technicalSkillRepo.getTechnicalSkillById(id);
        TechnicalSkillDto result = convertToDto(data);
        return result;
    }

    @Override
    public TechnicalSkillDto createTechnicalSkill(TechnicalSkillCreateDto technicalSkillCreateDto) {
        TechnicalSkill data = convertToEntityCreate(technicalSkillCreateDto);
        TechnicalSkill result = technicalSkillRepo.saveTechnicalSkill(data);
        return convertToDto(result);
    }

    @Override
    public TechnicalSkillDto updateTechnicalSkill(TechnicalSkillUpdateDto technicalSkillUpdateDto) {
        TechnicalSkill data = convertToEntityUpdate(technicalSkillUpdateDto);
        TechnicalSkill result = technicalSkillRepo.updateTechnicalSkill(data);
        return convertToDto(result);
    }

    @Override
    public boolean deleteTechnicalSkill(String id) {
        return technicalSkillRepo.deleteTechnicalSkill(id);
    }

    private TechnicalSkill convertToEntity(TechnicalSkillDto convertObject) {
        TechnicalSkill result = TechnicalSkill.builder()
                .id(convertObject.getId())
                .technicalSkill(convertObject.getTechnicalSkill())
                .enabled(convertObject.isEnabled())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private TechnicalSkill convertToEntityCreate(TechnicalSkillCreateDto convertObject) {
        TechnicalSkill result = TechnicalSkill.builder()
                .id(convertObject.getId())
                .technicalSkill(convertObject.getTechnicalSkill())
                .enabled(convertObject.isEnabled())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private TechnicalSkill convertToEntityUpdate(TechnicalSkillUpdateDto convertObject) {
        TechnicalSkill result = TechnicalSkill.builder()
                .id(convertObject.getId())
                .technicalSkill(convertObject.getTechnicalSkill())
                .enabled(convertObject.isEnabled())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private TechnicalSkillDto convertToDto(TechnicalSkill convertObject) {
        TechnicalSkillDto result = TechnicalSkillDto.builder()
                .id(convertObject.getId())
                .technicalSkill(convertObject.getTechnicalSkill())
                .enabled(convertObject.isEnabled())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }
}
