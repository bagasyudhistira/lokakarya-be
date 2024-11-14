package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.attitudeskill.*;
import co.id.ogya.lokakarya.services.AttitudeSkillServ;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class AttitudeSkillServImpl implements AttitudeSkillServ {
    @Autowired
    private AttitudeSkillRepo attitudeSkillRepo;

    @Override
    public List<AttitudeSkillDto> getAllAttitudeSkill() {
        List<AttitudeSkill> listData = attitudeSkillRepo.getAttitudeSkills();
        List<AttitudeSkillDto> listResult = new ArrayList<>();
        for(AttitudeSkill data : listData){
            AttitudeSkillDto result = convertToDto(data);
            listResult.add(result);
        }
        return listResult;
    }

    @Override
    public AttitudeSkillDto getAttitudeSkillById(String id) {
        AttitudeSkill data = attitudeSkillRepo.getAttitudeSkillById(id);
        AttitudeSkillDto result = convertToDto(data);
        return result;
    }

    @Override
    public AttitudeSkillDto createAttitudeSkill(AttitudeSkillCreateDto attitudeSkillCreateDto) {
        AttitudeSkill data = convertToEntityCreate(attitudeSkillCreateDto);
        AttitudeSkill result = attitudeSkillRepo.saveAttitudeSkill(data);
        return convertToDto(result);
    }

    @Override
    public AttitudeSkillDto updateAttitudeSkill(AttitudeSkillUpdateDto attitudeSkillUpdateDto) {
        AttitudeSkill data = convertToEntityUpdate(attitudeSkillUpdateDto);
        AttitudeSkill result = attitudeSkillRepo.updateAttitudeSkill(data);
        return convertToDto(result);
    }

    @Override
    public boolean deleteAttitudeSkill(String id) {
        return attitudeSkillRepo.deleteAttitudeSkill(id);
    }

    private AttitudeSkill convertToEntity(AttitudeSkillDto convertObject) {
        AttitudeSkill result = AttitudeSkill.builder()
                .id(convertObject.getId())
                .attitudeSkill(convertObject.getAttitudeSkill())
                .groupId(convertObject.getGroupId())
                .enabled(convertObject.isEnabled())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private AttitudeSkill convertToEntityCreate(AttitudeSkillCreateDto convertObject) {
        AttitudeSkill result = AttitudeSkill.builder()
                .id(convertObject.getId())
                .attitudeSkill(convertObject.getAttitudeSkill())
                .groupId(convertObject.getGroupId())
                .enabled(convertObject.isEnabled())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private AttitudeSkill convertToEntityUpdate(AttitudeSkillUpdateDto convertObject) {
        AttitudeSkill result = AttitudeSkill.builder()
                .id(convertObject.getId())
                .attitudeSkill(convertObject.getAttitudeSkill())
                .groupId(convertObject.getGroupId())
                .enabled(convertObject.isEnabled())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private AttitudeSkillDto convertToDto(AttitudeSkill convertObject) {
        AttitudeSkillDto result = AttitudeSkillDto.builder()
                .id(convertObject.getId())
                .attitudeSkill(convertObject.getAttitudeSkill())
                .groupId(convertObject.getGroupId())
                .enabled(convertObject.isEnabled())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }
}
