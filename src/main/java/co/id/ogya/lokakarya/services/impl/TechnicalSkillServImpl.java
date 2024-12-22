package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.devplan.DevPlanDto;
import co.id.ogya.lokakarya.dto.technicalskill.TechnicalSkillCreateDto;
import co.id.ogya.lokakarya.dto.technicalskill.TechnicalSkillDto;
import co.id.ogya.lokakarya.dto.technicalskill.TechnicalSkillUpdateDto;
import co.id.ogya.lokakarya.entities.DevPlan;
import co.id.ogya.lokakarya.entities.TechnicalSkill;
import co.id.ogya.lokakarya.repositories.TechnicalSkillRepo;
import co.id.ogya.lokakarya.services.TechnicalSkillServ;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class TechnicalSkillServImpl implements TechnicalSkillServ {
    @Autowired
    private TechnicalSkillRepo technicalSkillRepo;

    @Override
    public List<TechnicalSkillDto> getAllTechnicalSkill() {
        log.info("Attempting to fetch all TechnicalSkills");
        List<TechnicalSkillDto> listResult = new ArrayList<>();
        try {
            List<TechnicalSkill> listData = technicalSkillRepo.getTechnicalSkills();
            log.debug("Fetched {} TechnicalSkills from repository", listData.size());
            for (TechnicalSkill data : listData) {
                TechnicalSkillDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all TechnicalSkills: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public List<TechnicalSkillDto> getAllTechnicalSkillPerPage(int page, int pageSize) {
        log.info("Attempting to fetch all TechnicalSkills per page");
        List<TechnicalSkillDto> listResult = new ArrayList<>();
        try {
            List<TechnicalSkill> listData = technicalSkillRepo.getTechnicalSkillsPerPage(page, pageSize);
            log.debug("Fetched {} TechnicalSkills", listData.size());
            for (TechnicalSkill data : listData) {
                TechnicalSkillDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all TechnicalSkills: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public TechnicalSkillDto getTechnicalSkillById(String id) {
        log.info("Attempting to fetch TechnicalSkill by ID: {}", id);
        TechnicalSkillDto result = null;
        try {
            TechnicalSkill data = technicalSkillRepo.getTechnicalSkillById(id);
            result = convertToDto(data);
            log.debug("Fetched TechnicalSkill: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching TechnicalSkill by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public TechnicalSkillDto createTechnicalSkill(TechnicalSkillCreateDto technicalSkillCreateDto) {
        log.info("Attempting to create a new TechnicalSkill with data: {}", technicalSkillCreateDto);
        TechnicalSkillDto result = null;
        try {
            TechnicalSkill data = convertToEntityCreate(technicalSkillCreateDto);
            TechnicalSkill savedData = technicalSkillRepo.saveTechnicalSkill(data);
            result = convertToDto(savedData);
            log.info("Successfully created TechnicalSkill: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while creating TechnicalSkill: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public TechnicalSkillDto updateTechnicalSkill(TechnicalSkillUpdateDto technicalSkillUpdateDto) {
        log.info("Attempting to update TechnicalSkill with data: {}", technicalSkillUpdateDto);
        TechnicalSkillDto result = null;
        try {
            TechnicalSkill data = convertToEntityUpdate(technicalSkillUpdateDto);
            TechnicalSkill updatedData = technicalSkillRepo.updateTechnicalSkill(data);
            result = convertToDto(updatedData);
            log.info("Successfully updated TechnicalSkill: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while updating TechnicalSkill: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean deleteTechnicalSkill(String id) {
        log.info("Attempting to delete TechnicalSkill with ID: {}", id);
        boolean isDeleted = false;
        try {
            isDeleted = technicalSkillRepo.deleteTechnicalSkill(id);
            if (isDeleted) {
                log.info("Successfully deleted TechnicalSkill with ID: {}", id);
            } else {
                log.warn("Failed to delete TechnicalSkill with ID: {}. It might not exist.", id);
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting TechnicalSkill with ID {}: {}", id, e.getMessage(), e);
        }
        return isDeleted;
    }

    @Override
    public TechnicalSkillDto getTechnicalSkillByName(String technicalSkill) {
        log.info("Attempting to fetch TechnicalSkill by Technical Skill : {}", technicalSkill);
        TechnicalSkillDto result = null;
        try {
            TechnicalSkill data = technicalSkillRepo.getTechnicalSkillByName(technicalSkill);
            result = convertToDto(data);
            log.debug("Fetched TechnicalSkill: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching TechnicalSkill by Technical Skill {}: {}", technicalSkill, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Long countAllTechnicalSkill() {
        try {
            log.info("Fetching total count of all TechnicalSkills from repository");
            Long total = technicalSkillRepo.countTechnicalSkills();
            log.info("Successfully fetched total TechnicalSkills count: {}", total);
            return total;
        } catch (Exception e) {
            log.error("Error occurred while fetching total TechnicalSkills count: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch TechnicalSkills count", e);
        }
    }

    @Override
    public List<TechnicalSkillDto> sortAllTechnicalSkill(String order, int page, int pageSize) {
        log.info("Attempting to sort all TechnicalSkills");
        List<TechnicalSkillDto> listResult = new ArrayList<>();
        try {
            List<TechnicalSkill> listData = technicalSkillRepo.sortTechnicalSkills(order, page, pageSize);
            log.debug("Sorted {} TechnicalSkills from repository", listData.size());
            for (TechnicalSkill data : listData) {
                TechnicalSkillDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while sorting all TechnicalSkills: {}", e.getMessage(), e);
        }
        return listResult;
    }

    private TechnicalSkill convertToEntityCreate(TechnicalSkillCreateDto convertObject) {
        log.debug("Converting TechnicalSkillCreateDto to entity: {}", convertObject);
        return TechnicalSkill.builder()
                .technicalSkill(convertObject.getTechnicalSkill())
                .enabled(convertObject.isEnabled())
                .createdBy(convertObject.getCreatedBy())
                .build();
    }

    private TechnicalSkill convertToEntityUpdate(TechnicalSkillUpdateDto convertObject) {
        log.debug("Converting TechnicalSkillUpdateDto to entity: {}", convertObject);
        return TechnicalSkill.builder()
                .id(convertObject.getId())
                .technicalSkill(convertObject.getTechnicalSkill())
                .enabled(convertObject.isEnabled())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
    }

    private TechnicalSkillDto convertToDto(TechnicalSkill convertObject) {
        log.debug("Converting TechnicalSkill entity to DTO: {}", convertObject);
        return TechnicalSkillDto.builder()
                .id(convertObject.getId())
                .technicalSkill(convertObject.getTechnicalSkill())
                .enabled(convertObject.isEnabled())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
    }
}
