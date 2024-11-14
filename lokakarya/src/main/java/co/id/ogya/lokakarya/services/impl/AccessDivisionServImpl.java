package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.accessdivision.AccessDivisionCreateDto;
import co.id.ogya.lokakarya.dto.accessdivision.AccessDivisionDto;
import co.id.ogya.lokakarya.dto.accessdivision.AccessDivisionUpdateDto;
import co.id.ogya.lokakarya.services.AccessDivisionServ;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class AccessDivisionServImpl implements AccessDivisionServ {
    @Autowired
    private AccessDivisionRepo accessDivisionRepo;

    @Override
    public List<AccessDivisionDto> getAllAccessDivision() {
        List<AccessDivision> listData = accessDivisionRepo.getAccessDivisions();
        List<AccessDivisionDto> listResult = new ArrayList<>();
        for(AccessDivision data : listData){
            AccessDivisionDto result = convertToDto(data);
            listResult.add(result);
        }
        return listResult;
    }

    @Override
    public AccessDivisionDto getAccessDivisionById(String id) {
        AccessDivision data = accessDivisionRepo.getAccessDivisionById(id);
        AccessDivisionDto result = convertToDto(data);
        return result;
    }

    @Override
    public AccessDivisionDto createAccessDivision(AccessDivisionCreateDto accessDivisionCreateDto) {
        AccessDivision data = convertToEntity(accessDivisionCreateDto);
        AccessDivision result = accessDivisionRepo.saveAccessDivision(data);
        return convertToDto(result);
    }

    @Override
    public AccessDivisionDto updateAccessDivision(AccessDivisionUpdateDto accessDivisionUpdateDto) {
        AccessDivision data = convertToEntity(accessDivisionUpdateDto);
        AccessDivision result = accessDivisionRepo.updateAccessDivision(data);
        return convertToDto(result);
    }

    @Override
    public boolean deleteAccessDivision(String id) {
        return accessDivisionRepo.deleteAccessDivision(id);
    }

    private AccessDivision convertToEntity(AccessDivisionDto accessDivisionDto) {
        AccessDivision result = AccessDivision.builder()
                .id(accessDivisionDto.getId())
                .userId(accessDivisionDto.getUserId())
                .divisionId(accessDivisionDto.getDivisionId())
                .build();
        return result;
    }

    private AccessDivision convertToEntityCreate(AccessDivisionCreateDto convertObject) {
        AccessDivision result = AccessDivision.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .divisionId(convertObject.getDivisionId())
                .build();
        return result;
    }

    private AccessDivision convertToEntityUpdate(AccessDivisionUpdateDto convertObject) {
        AccessDivision result = AccessDivision.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .divisionId(convertObject.getDivisionId())
                .build();
        return result;
    }

    private AccessDivisionDto convertToDto(AccessDivision convertObject) {
        AccessDivisionDto result = AccessDivisionDto.builder()
                .id(convertObject.getId())
                .userId(convertObject.getUserId())
                .divisionId(convertObject.getDivisionId())
                .build();
        return result;
    }
}
