package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.devplan.*;
import co.id.ogya.lokakarya.services.DevPlanServ;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class DevPlanServImpl implements DevPlanServ {
    @Autowired
    private DevPlanRepo devPlanRepo;

    @Override
    public List<DevPlanDto> getAllDevPlan() {
        List<DevPlan> listData = devPlanRepo.getDevPlans();
        List<DevPlanDto> listResult = new ArrayList<>();
        for(DevPlan data : listData){
            DevPlanDto result = convertToDto(data);
            listResult.add(result);
        }
        return listResult;
    }

    @Override
    public DevPlanDto getDevPlanById(String id) {
        DevPlan data = devPlanRepo.getDevPlanById(id);
        DevPlanDto result = convertToDto(data);
        return result;
    }

    @Override
    public DevPlanDto createDevPlan(DevPlanCreateDto devPlanCreateDto) {
        DevPlan data = convertToEntityCreate(devPlanCreateDto);
        DevPlan result = devPlanRepo.saveDevPlan(data);
        return convertToDto(result);
    }

    @Override
    public DevPlanDto updateDevPlan(DevPlanUpdateDto devPlanUpdateDto) {
        DevPlan data = convertToEntityUpdate(devPlanUpdateDto);
        DevPlan result = devPlanRepo.updateDevPlan(data);
        return convertToDto(result);
    }

    @Override
    public boolean deleteDevPlan(String id) {
        return devPlanRepo.deleteDevPlan(id);
    }

    private DevPlan convertToEntity(DevPlanDto convertObject) {
        DevPlan result = DevPlan.builder()
                .id(convertObject.getId())
                .plan(convertObject.getPlan())
                .enabled(convertObject.isEnabled())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private DevPlan convertToEntityCreate(DevPlanCreateDto convertObject) {
        DevPlan result = DevPlan.builder()
                .id(convertObject.getId())
                .plan(convertObject.getPlan())
                .enabled(convertObject.isEnabled())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private DevPlan convertToEntityUpdate(DevPlanUpdateDto convertObject) {
        DevPlan result = DevPlan.builder()
                .id(convertObject.getId())
                .plan(convertObject.getPlan())
                .enabled(convertObject.isEnabled())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private DevPlanDto convertToDto(DevPlan convertObject) {
        DevPlanDto result = DevPlanDto.builder()
                .id(convertObject.getId())
                .plan(convertObject.getPlan())
                .enabled(convertObject.isEnabled())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }
}
