package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.EmpSuggestion;

import java.util.List;

public interface EmpSuggestionRepo {
    List<EmpSuggestion> getEmpSuggestions();
    EmpSuggestion getEmpSuggestionById(String id);
    EmpSuggestion saveEmpSuggestion(EmpSuggestion empSuggestion);
    EmpSuggestion updateEmpSuggestion(EmpSuggestion empSuggestion);
    Boolean deleteEmpSuggestion(String id);
}
