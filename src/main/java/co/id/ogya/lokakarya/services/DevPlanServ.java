package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.devplan.DevPlanCreateDto;
import co.id.ogya.lokakarya.dto.devplan.DevPlanDto;
import co.id.ogya.lokakarya.dto.devplan.DevPlanUpdateDto;

import java.util.List;

public interface DevPlanServ {
    List<DevPlanDto> getAllDevPlan();

    DevPlanDto getDevPlanById(String id);

    DevPlanDto createDevPlan(DevPlanCreateDto devPlanCreateDto);

    DevPlanDto updateDevPlan(DevPlanUpdateDto devPlanUpdateDto);

    boolean deleteDevPlan(String id);

    DevPlanDto getDevPlanByName(String planName);
}
