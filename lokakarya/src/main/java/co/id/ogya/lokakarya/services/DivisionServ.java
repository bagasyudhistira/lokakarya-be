package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.division.DivisionCreateDto;
import co.id.ogya.lokakarya.dto.division.DivisionGetDto;
import co.id.ogya.lokakarya.dto.division.DivisionUpdateDto;

import java.util.List;

public interface DivisionServ {
    List<DivisionGetDto> getAll();
    DivisionGetDto getById(String id);
    DivisionCreateDto create(DivisionCreateDto divisionCreateDto);
    DivisionUpdateDto update(DivisionUpdateDto divisionUpdateDto);
    boolean delete(String id);

}
