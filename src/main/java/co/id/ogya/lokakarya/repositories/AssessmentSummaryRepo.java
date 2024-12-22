package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.AssessmentSummary;

import java.util.List;
import java.util.Map;

public interface AssessmentSummaryRepo {
    List<AssessmentSummary> getAssessmentSummarys();

    AssessmentSummary getAssessmentSummaryById(String id);

    List<Map<String,Object>> getAssessmentSummaryGets();

    List<Map<String, Object>> getAssessmentSummaryGetsPerPage(int page, int pageSize);

    Map<String, Object> getAssessmentSummaryGetById(String id);

    Map<String, Object> getAssessmentSummaryGetByUserIdAndAssessmentYear(String userId, int year);

    List<Map<String,Object>> getAssessmentSummaryGetByUserId(String id);

    AssessmentSummary saveAssessmentSummary(AssessmentSummary assessmentSummary);

    AssessmentSummary updateAssessmentSummary(AssessmentSummary assessmentSummary);

    Boolean deleteAssessmentSummary(String id);

    List<Map<String, Object>> getAchievementSummaryByUserIdAssessmentYear(String userId, int assessmentYear);

    List<Map<String, Object>> getAttitudeSkillSummaryByUserIdAssessmentYear(String userId, int assessmentYear);

    List<Map<String, Object>> getAssessmentSummariesByDivisionIdAssessmentYear(String divisionId, int assessmentYear);

    List<Map<String, Object>> getAssessmentSummariesByAssessmentYear(int assessmentYear);

    Long countAssessmentSummarys();

    List<Map<String, Object>> sortAssessmentSummaryGetsOrderBy(String column, String order, int page, int pageSize);
}
