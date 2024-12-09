package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.empsuggestion.*;

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

    EmpSuggestionOneDto ifAnyEmpSuggestionExist(String userId, int assessmentYear);
}
