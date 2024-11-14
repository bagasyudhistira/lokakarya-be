package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.empsuggestion.EmpSuggestionCreateDto;
import co.id.ogya.lokakarya.dto.empsuggestion.EmpSuggestionDto;
import co.id.ogya.lokakarya.dto.empsuggestion.EmpSuggestionUpdateDto;

import java.util.List;

public interface EmpSuggestionServ {
    List<EmpSuggestionDto> getAllEmpSuggestion();
    EmpSuggestionDto getEmpSuggestionById(String id);
    EmpSuggestionDto createEmpSuggestion(EmpSuggestionCreateDto empSuggestionCreateDto);
    EmpSuggestionDto updateEmpSuggestion(EmpSuggestionUpdateDto empSuggestionUpdateDto);
    boolean deleteEmpSuggestion(String id);

}
