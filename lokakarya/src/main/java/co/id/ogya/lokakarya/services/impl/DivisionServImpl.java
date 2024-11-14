package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.division.*;
import co.id.ogya.lokakarya.services.DivisionServ;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class DivisionServImpl implements DivisionServ {
    @Autowired
    private DivisionRepo divisionRepo;

    @Override
    public List<DivisionDto> getAllDivision() {
        List<Division> listData = divisionRepo.getDivisions();
        List<DivisionDto> listResult = new ArrayList<>();
        for(Division data : listData){
            DivisionDto result = convertToDto(data);
            listResult.add(result);
        }
        return listResult;
    }

    @Override
    public DivisionDto getDivisionById(String id) {
        Division data = divisionRepo.getDivisionById(id);
        DivisionDto result = convertToDto(data);
        return result;
    }

    @Override
    public DivisionDto createDivision(DivisionCreateDto divisionCreateDto) {
        Division data = convertToEntityCreate(divisionCreateDto);
        Division result = divisionRepo.saveDivision(data);
        return convertToDto(result);
    }

    @Override
    public DivisionDto updateDivision(DivisionUpdateDto divisionUpdateDto) {
        Division data = convertToEntityUpdate(divisionUpdateDto);
        Division result = divisionRepo.updateDivision(data);
        return convertToDto(result);
    }

    @Override
    public boolean deleteDivision(String id) {
        return divisionRepo.deleteDivision(id);
    }

    private Division convertToEntity(DivisionDto convertObject) {
        Division result = Division.builder()
                .id(convertObject.getId())
                .divisionName(convertObject.getDivisionName())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private Division convertToEntityCreate(DivisionCreateDto convertObject) {
        Division result = Division.builder()
                .id(convertObject.getId())
                .divisionName(convertObject.getDivisionName())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private Division convertToEntityUpdate(DivisionUpdateDto convertObject) {
        Division result = Division.builder()
                .id(convertObject.getId())
                .divisionName(convertObject.getDivisionName())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private DivisionDto convertToDto(Division convertObject) {
        DivisionDto result = DivisionDto.builder()
                .id(convertObject.getId())
                .divisionName(convertObject.getDivisionName())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }
}
