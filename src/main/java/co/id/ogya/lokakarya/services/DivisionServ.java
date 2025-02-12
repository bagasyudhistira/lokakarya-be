package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.division.DivisionCreateDto;
import co.id.ogya.lokakarya.dto.division.DivisionDto;
import co.id.ogya.lokakarya.dto.division.DivisionUpdateDto;

import java.util.List;

public interface DivisionServ {
    List<DivisionDto> getAllDivision();

    List<DivisionDto> getAllDivisionPerPage(int page, int pageSize);

    DivisionDto getDivisionById(String id);

    DivisionDto createDivision(DivisionCreateDto divisionCreateDto);

    DivisionDto updateDivision(DivisionUpdateDto divisionUpdateDto);

    boolean deleteDivision(String id);

    DivisionDto getDivisionByName(String divisionName);

    Long countAllDivision(String keyword);

    List<DivisionDto> sortAllDivision(String order, int page, int pageSize);

    List<DivisionDto> sorchAllDivision(String keyword, String column, String order, int page, int pageSize);
}
