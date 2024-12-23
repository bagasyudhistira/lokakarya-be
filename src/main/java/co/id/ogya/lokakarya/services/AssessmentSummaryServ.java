package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.assessmentsummary.*;

import java.util.List;

public interface AssessmentSummaryServ {
    List<AssessmentSummaryDto> getAllAssessmentSummary();

    List<AssessmentSummaryGetDto> getAllAssessmentSummaryGet();

    List<AssessmentSummaryGetDto> getAllAssessmentSummaryGetPerPage(int page, int pageSize);

    AssessmentSummaryDto getAssessmentSummaryById(String id);

    AssessmentSummaryGetDto getAssessmentSummaryGetById(String id);

    AssessmentSummaryGetDto getAssessmentSummaryGetByUserIdAndAssessmentYear(String userId, int year);

    AssessmentSummaryDto createAssessmentSummary(AssessmentSummaryCreateDto assessmentSummaryCreateDto);

    AssessmentSummaryDto updateAssessmentSummary(AssessmentSummaryUpdateDto assessmentSummaryUpdateDto);

    boolean deleteAssessmentSummary(String id);

    List<AchievementSummaryGetDto> getAchievementSummaryByUserIdAssessmentYear(String userId, int assessmentYear);

    List<AttitudeSkillSummaryGetDto> getAttitudeSkillSummaryByUserIdAssessmentYear(String userId, int assessmentYear);

    List<AssessmentSummaryJointGetDto> getAssessmentSummariesByDivisionIdAssessmentYear(String divisionId, int assessmentYear);

    List<AssessmentSummaryJointGetDto> getAssessmentSummariesByAssessmentYear(int assessmentYear);

    Long countAllAssessmentSummary();

    List<AssessmentSummaryGetDto> sortAllAssessmentSummaryGetOrderBy(String column, String order, int page, int pageSize);

    List<AssessmentSummaryGetDto> sorchAllAssessmentSummaryGet(String keyword, String column, String order, int page, int pageSize);
}
