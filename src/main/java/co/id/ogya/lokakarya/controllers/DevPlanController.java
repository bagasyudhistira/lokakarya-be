package co.id.ogya.lokakarya.controllers;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.devplan.DevPlanCreateDto;
import co.id.ogya.lokakarya.dto.devplan.DevPlanDto;
import co.id.ogya.lokakarya.dto.devplan.DevPlanUpdateDto;
import co.id.ogya.lokakarya.services.DevPlanServ;
import co.id.ogya.lokakarya.utils.ServerResponseList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/devplan")
public class DevPlanController extends ServerResponseList {

    @Autowired
    DevPlanServ devPlanServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllDevPlans() {
        log.info("Fetching all DevPlans");
        long startTime = System.currentTimeMillis();

        try {
            List<DevPlanDto> result = devPlanServ.getAllDevPlan();
            ManagerDto<List<DevPlanDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all DevPlans in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all DevPlans: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch DevPlans", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDevPlanById(@PathVariable String id) {
        log.info("Fetching DevPlan with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            DevPlanDto result = devPlanServ.getDevPlanById(id);
            ManagerDto<DevPlanDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched DevPlan with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching DevPlan by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch DevPlan with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDevPlan(@RequestBody DevPlanCreateDto devPlanCreateDto) {
        log.info("Creating new DevPlan with data: {}", devPlanCreateDto);
        long startTime = System.currentTimeMillis();

        try {
            DevPlanDto result = devPlanServ.createDevPlan(devPlanCreateDto);
            ManagerDto<DevPlanDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Created new DevPlan in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating DevPlan: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create DevPlan", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDevPlan(@RequestBody DevPlanUpdateDto devPlanUpdateDto) {
        log.info("Updating DevPlan with data: {}", devPlanUpdateDto);
        long startTime = System.currentTimeMillis();

        try {
            DevPlanDto result = devPlanServ.updateDevPlan(devPlanUpdateDto);
            ManagerDto<DevPlanDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Updated DevPlan in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating DevPlan: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update DevPlan", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDevPlan(@PathVariable String id) {
        log.info("Deleting DevPlan with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            boolean result = devPlanServ.deleteDevPlan(id);
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(id + " deleted: " + result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Deleted DevPlan with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting DevPlan with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete DevPlan with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/name/{planName}")
    public ResponseEntity<?> getDevPlanByName(@PathVariable String planName) {
        log.info("Fetching DevPlan with name: {}", planName);
        long startTime = System.currentTimeMillis();

        try {
            DevPlanDto result = devPlanServ.getDevPlanByName(planName);
            ManagerDto<DevPlanDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched DevPlan with name: {} in {} ms", planName, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching DevPlan by name {}: {}", planName, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch DevPlan with name: " + planName, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all/{page}/{pageSize}")
    public ResponseEntity<?> getAllDevPlansPerPage(@PathVariable int page, @PathVariable int pageSize) {
        log.info("Fetching all DevPlans");
        long startTime = System.currentTimeMillis();

        try {
            List<DevPlanDto> result = devPlanServ.getAllDevPlanPerPage(page, pageSize);
//            Long total = devPlanServ.countAllDevPlan();
            ManagerDto<List<DevPlanDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());
//            response.setTotalData(total);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched {} DevPlans in {} ms", result.size(), endTime - startTime);
//            log.info("Total DevPlans: {}", total);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all DevPlans: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch DevPlans", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorch")
    public ResponseEntity<?> sorchAllDevPlansOrderBy(@RequestParam(required = false) String keyword, @RequestParam String column, @RequestParam String order, @RequestParam int page, @RequestParam int pageSize) {
        log.info("Sorching all DevPlans");
        long startTime = System.currentTimeMillis();

        try {
            List<DevPlanDto> result = devPlanServ.sorchAllDevPlan(keyword, column, order, page, pageSize);
            Long total = devPlanServ.countAllDevPlan(keyword);
            ManagerDto<List<DevPlanDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());
            response.setTotalData(total);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched {} DevPlans in {} ms", result.size(), endTime - startTime);
            log.info("Total DevPlans: {}", total);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all DevPlans: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch DevPlans", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
