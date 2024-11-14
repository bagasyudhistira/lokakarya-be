package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.assessmentsummary.*;
import co.id.ogya.lokakarya.services.AssessmentSummaryServ;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class AssessmentSummaryServImpl implements AssessmentSummaryServ {
    @Autowired
    private AssessmentSummaryRepo assessmentSummaryRepo;

    @Override
    public List<AssessmentSummaryDto> getAllAssessmentSummary() {
        List<AssessmentSummary> listData = assessmentSummaryRepo.getAssessmentSummarys();
        List<AssessmentSummaryDto> listResult = new ArrayList<>();
        for(AssessmentSummary data : listData){
            AssessmentSummaryDto result = convertToDto(data);
            listResult.add(result);
        }
        return listResult;
    }

    @Override
    public AssessmentSummaryDto getAssessmentSummaryById(String id) {
        AssessmentSummary data = assessmentSummaryRepo.getAssessmentSummaryById(id);
        AssessmentSummaryDto result = convertToDto(data);
        return result;
    }

    @Override
    public AssessmentSummaryDto createAssessmentSummary(AssessmentSummaryCreateDto assessmentSummaryCreateDto) {
        AssessmentSummary data = convertToEntityCreate(assessmentSummaryCreateDto);
        AssessmentSummary result = assessmentSummaryRepo.saveAssessmentSummary(data);
        return convertToDto(result);
    }

    @Override
    public AssessmentSummaryDto updateAssessmentSummary(AssessmentSummaryUpdateDto assessmentSummaryUpdateDto) {
        AssessmentSummary data = convertToEntityUpdate(assessmentSummaryUpdateDto);
        AssessmentSummary result = assessmentSummaryRepo.updateAssessmentSummary(data);
        return convertToDto(result);
    }

    @Override
    public boolean deleteAssessmentSummary(String id) {
        return assessmentSummaryRepo.deleteAssessmentSummary(id);
    }

    private AssessmentSummary convertToEntity(AssessmentSummaryDto convertObject) {
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
        AssessmentSummary result = AssessmentSummary.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .year(convertObject.getYear())
                .score(convertObject.getScore())
                .status(convertObject.getStatus())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private AssessmentSummary convertToEntityUpdate(AssessmentSummaryUpdateDto convertObject) {
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
