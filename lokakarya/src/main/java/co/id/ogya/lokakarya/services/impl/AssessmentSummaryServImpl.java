package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.assessmentsummary.AssessmentSummaryCreateDto;
import co.id.ogya.lokakarya.dto.assessmentsummary.AssessmentSummaryDto;
import co.id.ogya.lokakarya.dto.assessmentsummary.AssessmentSummaryUpdateDto;
import co.id.ogya.lokakarya.entities.AssessmentSummary;
import co.id.ogya.lokakarya.repositories.AssessmentSummaryRepo;
import co.id.ogya.lokakarya.services.AssessmentSummaryServ;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class AssessmentSummaryServImpl implements AssessmentSummaryServ {
    @Autowired
    private AssessmentSummaryRepo assessmentSummaryRepo;

    @Override
    public List<AssessmentSummaryDto> getAllAssessmentSummary() {
        log.info("Attempting to fetch all AssessmentSummaries");
        List<AssessmentSummaryDto> listResult = new ArrayList<>();
        try {
            List<AssessmentSummary> listData = assessmentSummaryRepo.getAssessmentSummarys();
            log.debug("Fetched {} AssessmentSummaries from repository", listData.size());
            for (AssessmentSummary data : listData) {
                AssessmentSummaryDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all AssessmentSummaries: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public AssessmentSummaryDto getAssessmentSummaryById(String id) {
        log.info("Attempting to fetch AssessmentSummary by ID: {}", id);
        AssessmentSummaryDto result = null;
        try {
            AssessmentSummary data = assessmentSummaryRepo.getAssessmentSummaryById(id);
            result = convertToDto(data);
            log.debug("Fetched AssessmentSummary: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching AssessmentSummary by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public AssessmentSummaryDto createAssessmentSummary(AssessmentSummaryCreateDto assessmentSummaryCreateDto) {
        log.info("Attempting to create a new AssessmentSummary with data: {}", assessmentSummaryCreateDto);
        AssessmentSummaryDto result = null;
        try {
            AssessmentSummary data = convertToEntityCreate(assessmentSummaryCreateDto);
            AssessmentSummary savedData = assessmentSummaryRepo.saveAssessmentSummary(data);
            result = convertToDto(savedData);
            log.info("Successfully created AssessmentSummary: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while creating AssessmentSummary: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public AssessmentSummaryDto updateAssessmentSummary(AssessmentSummaryUpdateDto assessmentSummaryUpdateDto) {
        log.info("Attempting to update AssessmentSummary with data: {}", assessmentSummaryUpdateDto);
        AssessmentSummaryDto result = null;
        try {
            AssessmentSummary data = convertToEntityUpdate(assessmentSummaryUpdateDto);
            AssessmentSummary updatedData = assessmentSummaryRepo.updateAssessmentSummary(data);
            result = convertToDto(updatedData);
            log.info("Successfully updated AssessmentSummary: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while updating AssessmentSummary: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean deleteAssessmentSummary(String id) {
        log.info("Attempting to delete AssessmentSummary with ID: {}", id);
        boolean isDeleted = false;
        try {
            isDeleted = assessmentSummaryRepo.deleteAssessmentSummary(id);
            if (isDeleted) {
                log.info("Successfully deleted AssessmentSummary with ID: {}", id);
            } else {
                log.warn("Failed to delete AssessmentSummary with ID: {}. It might not exist.", id);
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting AssessmentSummary with ID {}: {}", id, e.getMessage(), e);
        }
        return isDeleted;
    }

    private AssessmentSummary convertToEntity(AssessmentSummaryDto convertObject) {
        log.debug("Converting AssessmentSummaryDto to entity: {}", convertObject);
        AssessmentSummary result = AssessmentSummary.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .year(convertObject.getYear())
                .score(convertObject.getScore())
                .status(convertObject.getStatus())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private AssessmentSummary convertToEntityCreate(AssessmentSummaryCreateDto convertObject) {
        log.debug("Converting AssessmentSummaryCreateDto to entity: {}", convertObject);
        AssessmentSummary result = AssessmentSummary.builder()
                .userId(convertObject.getUserId())
                .year(convertObject.getYear())
                .score(convertObject.getScore())
                .status(convertObject.getStatus())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private AssessmentSummary convertToEntityUpdate(AssessmentSummaryUpdateDto convertObject) {
        log.debug("Converting AssessmentSummaryUpdateDto to entity: {}", convertObject);
        AssessmentSummary result = AssessmentSummary.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .year(convertObject.getYear())
                .score(convertObject.getScore())
                .status(convertObject.getStatus())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private AssessmentSummaryDto convertToDto(AssessmentSummary convertObject) {
        log.debug("Converting AssessmentSummary entity to DTO: {}", convertObject);
        AssessmentSummaryDto result = AssessmentSummaryDto.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .year(convertObject.getYear())
                .score(convertObject.getScore())
                .status(convertObject.getStatus())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }
}
