package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.assessmentsummary.AssessmentSummaryCreateDto;
import co.id.ogya.lokakarya.dto.assessmentsummary.AssessmentSummaryDto;
import co.id.ogya.lokakarya.dto.assessmentsummary.AssessmentSummaryUpdateDto;

import java.util.List;

public interface AssessmentSummaryServ {
    List<AssessmentSummaryDto> getAllAssessmentSummary();
    AssessmentSummaryDto getAssessmentSummaryById(String id);
    AssessmentSummaryDto createAssessmentSummary(AssessmentSummaryCreateDto assessmentSummaryCreateDto);
    AssessmentSummaryDto updateAssessmentSummary(AssessmentSummaryUpdateDto assessmentSummaryUpdateDto);
    boolean deleteAssessmentSummary(String id);

}
