package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.EmpSuggestion;

import java.util.List;
import java.util.Map;

public interface EmpSuggestionRepo {
    List<EmpSuggestion> getEmpSuggestions();

    EmpSuggestion getEmpSuggestionById(String id);

    EmpSuggestion saveEmpSuggestion(EmpSuggestion empSuggestion);

    EmpSuggestion updateEmpSuggestion(EmpSuggestion empSuggestion);

    Boolean deleteEmpSuggestion(String id);

    List<Map<String, Object>> getEmpSuggestionGets();

    List<Map<String, Object>> getEmpSuggestionGetsPerPage(int page, int pageSize);

    List<Map<String, Object>> getEmpSuggestionGetByUserId(String userId);

    List<Map<String, Object>> getEmpSuggestionGetByCreatedBy(String userId);

    Map<String, Object> ifAnyEmpSuggestionExist(String userId, int assessmentYear);

    Long countEmpSuggestions();

    List<Map<String, Object>> sortEmpSuggestionGetsOrderBy(String column, String order, int page, int pageSize);

    List<Map<String, Object>> sorchEmpSuggestionGets(String keyword, String column, String order, int page, int pageSize);
}
