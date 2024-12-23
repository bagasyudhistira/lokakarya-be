package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.devplan.DevPlanCreateDto;
import co.id.ogya.lokakarya.dto.devplan.DevPlanDto;
import co.id.ogya.lokakarya.dto.devplan.DevPlanUpdateDto;

import java.util.List;

public interface DevPlanServ {
    List<DevPlanDto> getAllDevPlan();

    List<DevPlanDto> getAllDevPlanPerPage(int page, int pageSize);

    DevPlanDto getDevPlanById(String id);

    DevPlanDto createDevPlan(DevPlanCreateDto devPlanCreateDto);

    DevPlanDto updateDevPlan(DevPlanUpdateDto devPlanUpdateDto);

    boolean deleteDevPlan(String id);

    DevPlanDto getDevPlanByName(String planName);

    Long countAllDevPlan();

    List<DevPlanDto> sortAllDevPlan(String order, int page, int pageSize);

    List<DevPlanDto> sorchAllDevPlan(String keyword, String column, String order, int page, int pageSize);
}
