package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.assessmentsummary.*;

import java.util.List;

public interface AssessmentSummaryServ {
    List<AssessmentSummaryDto> getAllAssessmentSummary();

    List<AssessmentSummaryGetDto> getAllAssessmentSummaryGet();

    AssessmentSummaryDto getAssessmentSummaryById(String id);

    AssessmentSummaryGetDto getAssessmentSummaryGetById(String id);

    AssessmentSummaryDto createAssessmentSummary(AssessmentSummaryCreateDto assessmentSummaryCreateDto);

    AssessmentSummaryDto updateAssessmentSummary(AssessmentSummaryUpdateDto assessmentSummaryUpdateDto);

    boolean deleteAssessmentSummary(String id);

    List<AchievementSummaryGetDto> getAchievementSummaryByUserIdAssessmentYear(String userId, int assessmentYear);

    List<AttitudeSkillSummaryGetDto> getAttitudeSkillSummaryByUserIdAssessmentYear(String userId, int assessmentYear);

}
