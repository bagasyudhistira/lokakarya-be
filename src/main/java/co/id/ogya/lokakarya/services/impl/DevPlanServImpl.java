package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.devplan.DevPlanCreateDto;
import co.id.ogya.lokakarya.dto.devplan.DevPlanDto;
import co.id.ogya.lokakarya.dto.devplan.DevPlanUpdateDto;
import co.id.ogya.lokakarya.entities.DevPlan;
import co.id.ogya.lokakarya.repositories.DevPlanRepo;
import co.id.ogya.lokakarya.services.DevPlanServ;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class DevPlanServImpl implements DevPlanServ {
    @Autowired
    private DevPlanRepo devPlanRepo;

    @Override
    public List<DevPlanDto> getAllDevPlan() {
        log.info("Attempting to fetch all DevPlans");
        List<DevPlanDto> listResult = new ArrayList<>();
        try {
            List<DevPlan> listData = devPlanRepo.getDevPlans();
            log.debug("Fetched {} DevPlans from repository", listData.size());
            for (DevPlan data : listData) {
                DevPlanDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all DevPlans: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public List<DevPlanDto> getAllDevPlanPerPage(int page, int pageSize) {
        log.info("Attempting to fetch all DevPlans per page");
        List<DevPlanDto> listResult = new ArrayList<>();
        try {
            List<DevPlan> listData = devPlanRepo.getDevPlansPerPage(page, pageSize);
            log.debug("Fetched {} DevPlans", listData.size());
            for (DevPlan data : listData) {
                DevPlanDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all DevPlans: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public DevPlanDto getDevPlanById(String id) {
        log.info("Attempting to fetch DevPlan by ID: {}", id);
        DevPlanDto result = null;
        try {
            DevPlan data = devPlanRepo.getDevPlanById(id);
            result = convertToDto(data);
            log.debug("Fetched DevPlan: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching DevPlan by ID {}: {}", id, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public DevPlanDto createDevPlan(DevPlanCreateDto devPlanCreateDto) {
        log.info("Attempting to create a new DevPlan with data: {}", devPlanCreateDto);
        DevPlanDto result = null;
        try {
            DevPlan data = convertToEntityCreate(devPlanCreateDto);
            DevPlan savedData = devPlanRepo.saveDevPlan(data);
            result = convertToDto(savedData);
            log.info("Successfully created DevPlan: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while creating DevPlan: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public DevPlanDto updateDevPlan(DevPlanUpdateDto devPlanUpdateDto) {
        log.info("Attempting to update DevPlan with data: {}", devPlanUpdateDto);
        DevPlanDto result = null;
        try {
            DevPlan data = convertToEntityUpdate(devPlanUpdateDto);
            DevPlan updatedData = devPlanRepo.updateDevPlan(data);
            result = convertToDto(updatedData);
            log.info("Successfully updated DevPlan: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while updating DevPlan: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean deleteDevPlan(String id) {
        log.info("Attempting to delete DevPlan with ID: {}", id);
        boolean isDeleted = false;
        try {
            isDeleted = devPlanRepo.deleteDevPlan(id);
            if (isDeleted) {
                log.info("Successfully deleted DevPlan with ID: {}", id);
            } else {
                log.warn("Failed to delete DevPlan with ID: {}. It might not exist.", id);
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting DevPlan with ID {}: {}", id, e.getMessage(), e);
        }
        return isDeleted;
    }

    @Override
    public DevPlanDto getDevPlanByName(String planName) {
        log.info("Attempting to fetch DevPlan by Plan Name: {}", planName);
        DevPlanDto result = null;
        try {
            DevPlan data = devPlanRepo.getDevPlanByName(planName);
            result = convertToDto(data);
            log.debug("Fetched DevPlan: {}", result);
        } catch (Exception e) {
            log.error("Error occurred while fetching DevPlan by Plan Name {}: {}", planName, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Long countAllDevPlan(String keyword) {
        try {
            log.info("Fetching total count of all DevPlans from repository");
            Long total = devPlanRepo.countDevPlans(keyword);
            log.info("Successfully fetched total DevPlans count: {}", total);
            return total;
        } catch (Exception e) {
            log.error("Error occurred while fetching total DevPlans count: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch DevPlans count", e);
        }
    }

    @Override
    public List<DevPlanDto> sortAllDevPlan(String order, int page, int pageSize) {
        log.info("Attempting to sort all DevPlans");
        List<DevPlanDto> listResult = new ArrayList<>();
        try {
            List<DevPlan> listData = devPlanRepo.sortDevPlans(order, page, pageSize);
            log.debug("Sorted {} DevPlans from repository", listData.size());
            for (DevPlan data : listData) {
                DevPlanDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while sorting all DevPlans: {}", e.getMessage(), e);
        }
        return listResult;
    }

    @Override
    public List<DevPlanDto> sorchAllDevPlan(String keyword, String column, String order, int page, int pageSize) {
        log.info("Attempting to sorch all DevPlans");
        List<DevPlanDto> listResult = new ArrayList<>();
        try {
            List<DevPlan> listData = devPlanRepo.sorchDevPlans(keyword, column, order, page, pageSize);
            log.debug("Sorched {} DevPlans from repository", listData.size());
            for (DevPlan data : listData) {
                DevPlanDto result = convertToDto(data);
                listResult.add(result);
            }
        } catch (Exception e) {
            log.error("Error occurred while sorching all DevPlans: {}", e.getMessage(), e);
        }
        return listResult;
    }

    private DevPlan convertToEntityCreate(DevPlanCreateDto convertObject) {
        log.debug("Converting DevPlanCreateDto to entity: {}", convertObject);
        DevPlan result = DevPlan.builder()
                .plan(convertObject.getPlan())
                .enabled(convertObject.isEnabled())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private DevPlan convertToEntityUpdate(DevPlanUpdateDto convertObject) {
        log.debug("Converting DevPlanUpdateDto to entity: {}", convertObject);
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
        log.debug("Converting DevPlan entity to DTO: {}", convertObject);
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
