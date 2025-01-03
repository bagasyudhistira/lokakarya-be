package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.empsuggestion.*;

import java.util.List;

public interface EmpSuggestionServ {
    List<EmpSuggestionDto> getAllEmpSuggestion();

    EmpSuggestionDto getEmpSuggestionById(String id);

    EmpSuggestionDto createEmpSuggestion(EmpSuggestionCreateDto empSuggestionCreateDto);

    EmpSuggestionDto updateEmpSuggestion(EmpSuggestionUpdateDto empSuggestionUpdateDto);

    boolean deleteEmpSuggestion(String id);

    List<EmpSuggestionGetDto> getAllEmpSuggestionGets();

    List<EmpSuggestionGetDto> getAllEmpSuggestionGetPerPage(int page, int pageSize);

    List<EmpSuggestionGetDto> getEmpSuggestionGetByUserId(String userId);

    List<EmpSuggestionGetDto> getEmpSuggestionGetByCreatedBy(String userId);

    EmpSuggestionOneDto ifAnyEmpSuggestionExist(String userId, int assessmentYear);

    Long countAllEmpSuggestion(String keyword);

    List<EmpSuggestionGetDto> sortAllEmpSuggestionGetOrderBy(String column, String order, int page, int pageSize);

    List<EmpSuggestionGetDto> sorchAllEmpSuggestionGet(String keyword, String userId, String column, String order, int page, int pageSize);
}
