package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.AssessmentSummary;

import java.util.List;

public interface AssessmentSummaryRepo {
    List<AssessmentSummary> getAssessmentSummarys();
    AssessmentSummary getAssessmentSummaryById(String id);
    AssessmentSummary saveAssessmentSummary(AssessmentSummary assessmentSummary);
    AssessmentSummary updateAssessmentSummary(AssessmentSummary assessmentSummary);
    Boolean deleteAssessmentSummary(String id);
}
