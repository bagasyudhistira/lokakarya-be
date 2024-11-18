package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.assessmentsummary.AssessmentSummaryCreateDto;
import co.id.ogya.lokakarya.dto.assessmentsummary.AssessmentSummaryDto;
import co.id.ogya.lokakarya.dto.assessmentsummary.AssessmentSummaryGetDto;
import co.id.ogya.lokakarya.dto.assessmentsummary.AssessmentSummaryUpdateDto;

import java.util.List;

public interface AssessmentSummaryServ {
    List<AssessmentSummaryDto> getAllAssessmentSummary();

    List<AssessmentSummaryGetDto> getAllAssessmentSummaryGet();

    AssessmentSummaryDto getAssessmentSummaryById(String id);

    AssessmentSummaryGetDto getAssessmentSummaryGetById(String id);

    AssessmentSummaryDto createAssessmentSummary(AssessmentSummaryCreateDto assessmentSummaryCreateDto);
    AssessmentSummaryDto updateAssessmentSummary(AssessmentSummaryUpdateDto assessmentSummaryUpdateDto);
    boolean deleteAssessmentSummary(String id);

}
