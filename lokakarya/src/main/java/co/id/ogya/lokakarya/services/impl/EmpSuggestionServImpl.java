package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.empsuggestion.*;
import co.id.ogya.lokakarya.services.EmpSuggestionServ;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class EmpSuggestionServImpl implements EmpSuggestionServ {
    @Autowired
    private EmpSuggestionRepo empSuggestionRepo;

    @Override
    public List<EmpSuggestionDto> getAllEmpSuggestion() {
        List<EmpSuggestion> listData = empSuggestionRepo.getEmpSuggestions();
        List<EmpSuggestionDto> listResult = new ArrayList<>();
        for(EmpSuggestion data : listData){
            EmpSuggestionDto result = convertToDto(data);
            listResult.add(result);
        }
        return listResult;
    }

    @Override
    public EmpSuggestionDto getEmpSuggestionById(String id) {
        EmpSuggestion data = empSuggestionRepo.getEmpSuggestionById(id);
        EmpSuggestionDto result = convertToDto(data);
        return result;
    }

    @Override
    public EmpSuggestionDto createEmpSuggestion(EmpSuggestionCreateDto empSuggestionCreateDto) {
        EmpSuggestion data = convertToEntityCreate(empSuggestionCreateDto);
        EmpSuggestion result = empSuggestionRepo.saveEmpSuggestion(data);
        return convertToDto(result);
    }

    @Override
    public EmpSuggestionDto updateEmpSuggestion(EmpSuggestionUpdateDto empSuggestionUpdateDto) {
        EmpSuggestion data = convertToEntityUpdate(empSuggestionUpdateDto);
        EmpSuggestion result = empSuggestionRepo.updateEmpSuggestion(data);
        return convertToDto(result);
    }

    @Override
    public boolean deleteEmpSuggestion(String id) {
        return empSuggestionRepo.deleteEmpSuggestion(id);
    }

    private EmpSuggestion convertToEntity(EmpSuggestionDto convertObject) {
        EmpSuggestion result = EmpSuggestion.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .suggestion(convertObject.getSuggestion())
                .assessmentYear(convertObject.getAssessmentYear())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private EmpSuggestion convertToEntityCreate(EmpSuggestionCreateDto convertObject) {
        EmpSuggestion result = EmpSuggestion.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .suggestion(convertObject.getSuggestion())
                .assessmentYear(convertObject.getAssessmentYear())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private EmpSuggestion convertToEntityUpdate(EmpSuggestionUpdateDto convertObject) {
        EmpSuggestion result = EmpSuggestion.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .suggestion(convertObject.getSuggestion())
                .assessmentYear(convertObject.getAssessmentYear())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private EmpSuggestionDto convertToDto(EmpSuggestion convertObject) {
        EmpSuggestionDto result = EmpSuggestionDto.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .suggestion(convertObject.getSuggestion())
                .assessmentYear(convertObject.getAssessmentYear())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }
}
