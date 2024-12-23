package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.Division;

import java.util.List;

public interface DivisionRepo {
    List<Division> getDivisions();

    List<Division> getDivisionsPerPage(int page, int pageSize);

    Division getDivisionById(String id);

    Division saveDivision(Division division);

    Division updateDivision(Division division);

    Boolean deleteDivision(String id);

    Division getDivisionByName(String divisionName);

    Long countDivisions(String keyword);

    List<Division> sortDivisions(String order, int page, int pageSize);

    List<Division> sorchDivisions(String keyword, String column, String order, int page, int pageSize);
}
