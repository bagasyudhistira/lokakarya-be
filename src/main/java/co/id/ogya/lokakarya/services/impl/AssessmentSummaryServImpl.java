package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.appuser.AppUserGetDto;
import co.id.ogya.lokakarya.dto.assessmentsummary.*;
import co.id.ogya.lokakarya.entities.AssessmentSummary;
import co.id.ogya.lokakarya.repositories.AssessmentSummaryRepo;
import co.id.ogya.lokakarya.services.AssessmentSummaryServ;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public List<AssessmentSummaryGetDto> getAllAssessmentSummaryGet() {
        log.info("Attempting to fetch all AssessmentSummaries");
        List<AssessmentSummaryGetDto> listResult = new ArrayList<>();
        try {
            List<Map<String,Object>> listData = assessmentSummaryRepo.getAssessmentSummaryGets();
            log.debug("Fetched {} AssessmentSummaries from repository", listData.size());
            for (Map<String,Object> data : listData) {
                AssessmentSummaryGetDto result =  AssessmentSummaryGetDto.mapToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all AssessmentSummaries: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public List<AssessmentSummaryGetDto> getAllAssessmentSummaryGetPerPage(int page, int pageSize) {
        log.info("Attempting to fetch all AssessmentSummaries");
        List<AssessmentSummaryGetDto> listResult = new ArrayList<>();
        try {
            List<Map<String, Object>> listData = assessmentSummaryRepo.getAssessmentSummaryGetsPerPage(page, pageSize);
            log.debug("Fetched {} AssessmentSummaries from repository", listData.size());
            for (Map<String, Object> data : listData) {
                AssessmentSummaryGetDto result =  AssessmentSummaryGetDto.mapToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all AssessmentSummaries: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public AssessmentSummaryGetDto getAssessmentSummaryGetById(String id) {
        log.info("Attempting to fetch AssessmentSummary by ID: {}", id);
        AssessmentSummaryGetDto result = null;
        try {
            Map<String,Object> data = assessmentSummaryRepo.getAssessmentSummaryGetById(id);
            result =  AssessmentSummaryGetDto.mapToDto(data);
            log.debug("Fetched AssessmentSummary: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching AssessmentSummary by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public AssessmentSummaryGetDto getAssessmentSummaryGetByUserIdAndAssessmentYear(String userId, int year) {
        log.info("Attempting to fetch AssessmentSummary by ID: {} year: {}", userId, year);
        AssessmentSummaryGetDto result = null;
        try {
            Map<String,Object> data = assessmentSummaryRepo.getAssessmentSummaryGetByUserIdAndAssessmentYear(userId, year);
            result =  AssessmentSummaryGetDto.mapToDto(data);
            log.debug("Fetched AssessmentSummary: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching AssessmentSummary by ID {} year {}: {}", userId, year, e.getMessage(), e);
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

    @Override
    public List<AchievementSummaryGetDto> getAchievementSummaryByUserIdAssessmentYear(String userId, int assessmentYear) {
        log.info("Attempting to fetch all AchievementSummary for User ID: {} and Assessment Year: {}", userId, assessmentYear);
        List<AchievementSummaryGetDto> listResult = new ArrayList<>();
        try {
            List<Map<String, Object>> listData = assessmentSummaryRepo.getAchievementSummaryByUserIdAssessmentYear(userId, assessmentYear);
            log.debug("Fetched {} AchievementSummary from repository for User ID: {} and Assessment Year: {}", listData.size(), userId, assessmentYear);
            for (Map<String,Object> data : listData) {
                AchievementSummaryGetDto result = AchievementSummaryGetDto.mapToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all AchievementSummary for User ID: {} and Assessment Year: {} : {}", userId, assessmentYear, e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public List<AttitudeSkillSummaryGetDto> getAttitudeSkillSummaryByUserIdAssessmentYear(String userId, int assessmentYear) {
        log.info("Attempting to fetch all AttitudeSkillSummary for User ID: {} and Assessment Year: {}", userId, assessmentYear);
        List<AttitudeSkillSummaryGetDto> listResult = new ArrayList<>();
        try {
            List<Map<String, Object>> listData = assessmentSummaryRepo.getAttitudeSkillSummaryByUserIdAssessmentYear(userId, assessmentYear);
            log.debug("Fetched {} AttitudeSkillSummary from repository for User ID: {} and Assessment Year: {}", listData.size(), userId, assessmentYear);
            for (Map<String,Object> data : listData) {
                AttitudeSkillSummaryGetDto result = AttitudeSkillSummaryGetDto.mapToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all AttitudeSkillSummary for User ID: {} and Assessment Year: {} : {}", userId, assessmentYear, e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public List<AssessmentSummaryJointGetDto> getAssessmentSummariesByDivisionIdAssessmentYear(String divisionId, int assessmentYear) {
        log.info("Attempting to fetch all AssessmentSummary for Division ID: {} and Assessment Year: {}", divisionId, assessmentYear);
        List<AssessmentSummaryJointGetDto> listResult = new ArrayList<>();
        try {
            List<Map<String, Object>> listData = assessmentSummaryRepo.getAssessmentSummariesByDivisionIdAssessmentYear(divisionId, assessmentYear);
            log.debug("Fetched {} AssessmentSummary from repository for Division ID: {} and Assessment Year: {}", listData.size(), divisionId, assessmentYear);
            for (Map<String,Object> data : listData) {
                AssessmentSummaryJointGetDto result = AssessmentSummaryJointGetDto.mapToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all AssessmentSummary for Division ID: {} and Assessment Year: {} : {}", divisionId, assessmentYear, e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public List<AssessmentSummaryJointGetDto> getAssessmentSummariesByAssessmentYear(int assessmentYear) {
        log.info("Attempting to fetch all AssessmentSummary for Assessment Year: {}", assessmentYear);
        List<AssessmentSummaryJointGetDto> listResult = new ArrayList<>();
        try {
            List<Map<String, Object>> listData = assessmentSummaryRepo.getAssessmentSummariesByAssessmentYear(assessmentYear);
            log.debug("Fetched {} AssessmentSummary from repository for Assessment Year: {}", listData.size(), assessmentYear);
            for (Map<String,Object> data : listData) {
                AssessmentSummaryJointGetDto result = AssessmentSummaryJointGetDto.mapToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all AssessmentSummary for Assessment Year: {} : {}", assessmentYear, e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public Long countAllAssessmentSummary() {
        try {
            log.info("Fetching total count of all AssessmentSummaries from repository");
            Long total = assessmentSummaryRepo.countAssessmentSummarys();
            log.info("Successfully fetched total AssessmentSummaries count: {}", total);
            return total;
        } catch (Exception e) {
            log.error("Error occurred while fetching total AssessmentSummaries count: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch AssessmentSummaries count", e);
        }
    }

    @Override
    public List<AssessmentSummaryGetDto> sortAllAssessmentSummaryGetOrderBy(String column, String order, int page, int pageSize) {
        log.info("Attempting to sort all AssessmentSummaries order by {} {}", column, order);
        List<AssessmentSummaryGetDto> listResult = new ArrayList<>();
        try {
            List<Map<String, Object>> listData = assessmentSummaryRepo.sortAssessmentSummaryGetsOrderBy(column, order, page, pageSize);
            log.debug("Sorted {} AssessmentSummaries from repository order by {}", listData.size(), column);
            for (Map<String, Object> data : listData) {
                AssessmentSummaryGetDto result =  AssessmentSummaryGetDto.mapToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while sorting all AssessmentSummaries: {}", e.getMessage(), e);
        }
        return listResult;
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
