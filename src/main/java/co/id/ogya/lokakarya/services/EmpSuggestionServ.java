package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.empsuggestion.EmpSuggestionCreateDto;
import co.id.ogya.lokakarya.dto.empsuggestion.EmpSuggestionDto;
import co.id.ogya.lokakarya.dto.empsuggestion.EmpSuggestionGetDto;
import co.id.ogya.lokakarya.dto.empsuggestion.EmpSuggestionUpdateDto;

import java.util.List;
import java.util.Map;

public interface EmpSuggestionServ {
    List<EmpSuggestionDto> getAllEmpSuggestion();

    EmpSuggestionDto getEmpSuggestionById(String id);

    EmpSuggestionDto createEmpSuggestion(EmpSuggestionCreateDto empSuggestionCreateDto);

    EmpSuggestionDto updateEmpSuggestion(EmpSuggestionUpdateDto empSuggestionUpdateDto);

    boolean deleteEmpSuggestion(String id);

    List<EmpSuggestionGetDto> getAllEmpSuggestionGets();

    List<EmpSuggestionGetDto> getEmpSuggestionGetByUserId(String userId);

    List<EmpSuggestionGetDto> getEmpSuggestionGetByCreatedBy(String userId);

    Boolean ifAnyEmpSuggestionExist(String userId, int assessmentYear);
}
