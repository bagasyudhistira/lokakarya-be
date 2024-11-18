package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.AssessmentSummary;

import java.util.List;
import java.util.Map;

public interface AssessmentSummaryRepo {
    List<AssessmentSummary> getAssessmentSummarys();
    AssessmentSummary getAssessmentSummaryById(String id);

    List<Map<String,Object>> getAssessmentSummaryGets();

    Map<String, Object> getAssessmentSummaryGetById(String id);

    List<Map<String,Object>> getAssessmentSummaryGetByUserId(String id);

    AssessmentSummary saveAssessmentSummary(AssessmentSummary assessmentSummary);
    AssessmentSummary updateAssessmentSummary(AssessmentSummary assessmentSummary);
    Boolean deleteAssessmentSummary(String id);
}
