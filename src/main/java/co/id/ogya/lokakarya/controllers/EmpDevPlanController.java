package co.id.ogya.lokakarya.controllers;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.empdevplan.EmpDevPlanCreateDto;
import co.id.ogya.lokakarya.dto.empdevplan.EmpDevPlanDto;
import co.id.ogya.lokakarya.dto.empdevplan.EmpDevPlanGetDto;
import co.id.ogya.lokakarya.dto.empdevplan.EmpDevPlanUpdateDto;
import co.id.ogya.lokakarya.services.EmpDevPlanServ;
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
@RequestMapping("/empdevplan")
public class EmpDevPlanController extends ServerResponseList {

    @Autowired
    private EmpDevPlanServ empDevPlanServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllEmpDevPlans() {
        log.info("Fetching all EmpDevPlans");
        long startTime = System.currentTimeMillis();

        try {
            List<EmpDevPlanDto> result = empDevPlanServ.getAllEmpDevPlan();
            ManagerDto<List<EmpDevPlanDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all EmpDevPlans in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all EmpDevPlans: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch EmpDevPlans", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmpDevPlanById(@PathVariable String id) {
        log.info("Fetching EmpDevPlan by ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            EmpDevPlanDto result = empDevPlanServ.getEmpDevPlanById(id);
            ManagerDto<EmpDevPlanDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched EmpDevPlan with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching EmpDevPlan by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch EmpDevPlan with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmpDevPlan(@RequestBody EmpDevPlanCreateDto empDevPlanCreateDto) {
        log.info("Creating EmpDevPlan with data: {}", empDevPlanCreateDto);
        long startTime = System.currentTimeMillis();

        try {
            EmpDevPlanDto result = empDevPlanServ.createEmpDevPlan(empDevPlanCreateDto);
            ManagerDto<EmpDevPlanDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Created EmpDevPlan in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating EmpDevPlan: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create EmpDevPlan", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmpDevPlan(@RequestBody EmpDevPlanUpdateDto empDevPlanUpdateDto) {
        log.info("Updating EmpDevPlan with data: {}", empDevPlanUpdateDto);
        long startTime = System.currentTimeMillis();

        try {
            EmpDevPlanDto result = empDevPlanServ.updateEmpDevPlan(empDevPlanUpdateDto);
            ManagerDto<EmpDevPlanDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Updated EmpDevPlan in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating EmpDevPlan: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update EmpDevPlan", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpDevPlan(@PathVariable String id) {
        log.info("Deleting EmpDevPlan with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            boolean result = empDevPlanServ.deleteEmpDevPlan(id);
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(id + " deleted: " + result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Deleted EmpDevPlan with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting EmpDevPlan with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete EmpDevPlan with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllEmpDevPlanGets() {
        log.info("Fetching all EmpDevPlans");
        long startTime = System.currentTimeMillis();

        try {
            List<EmpDevPlanGetDto> result = empDevPlanServ.getAllEmpDevPlanGets();
            ManagerDto<List<EmpDevPlanGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all EmpDevPlans in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all EmpDevPlans: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch EmpDevPlans", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getEmpDevPlanGetByUserId(@PathVariable String userId) {
        log.info("Fetching EmpDevPlan by User ID: {}", userId);
        long startTime = System.currentTimeMillis();

        try {
            List<EmpDevPlanGetDto> result = empDevPlanServ.getEmpDevPlanGetByUserId(userId);
            ManagerDto<List<EmpDevPlanGetDto>> response = new ManagerDto<>();
            response.setContent(result);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched EmpDevPlan with User ID: {} in {} ms", userId, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching EmpDevPlan by User ID {}: {}", userId, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch EmpDevPlan with User ID: " + userId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{userId}/{assessmentYear}")
    public ResponseEntity<?> getEmpDevPlanGetByUserIdAssessmentYear(@PathVariable String userId, @PathVariable String assessmentYear) {
        log.info("Fetching EmpDevPlan by User ID: {}  and Assessment Year: {}", userId);
        long startTime = System.currentTimeMillis();

        try {
            List<EmpDevPlanGetDto> result = empDevPlanServ.getEmpDevPlanGetByUserIdAssessmentYear(userId, assessmentYear);
            ManagerDto<List<EmpDevPlanGetDto>> response = new ManagerDto<>();
            response.setContent(result);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched EmpDevPlan with User ID: {} and Assessment Year: {} in {} ms", userId, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching EmpDevPlan by User ID {}: {}", userId, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch EmpDevPlan with User ID: " + userId + " and Assessment Year: " + assessmentYear, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}/{devPlanId}/{assessmentYear}")
    public ResponseEntity<?> ifAnyEmpDevPlanExist(@PathVariable String userId, @PathVariable String devPlanId, @PathVariable int assessmentYear) {
        log.info("Looking for EmpDevPlan with User ID: {}, Dev Plan ID: {}, and Assessment Year: {}", userId, devPlanId, assessmentYear);
        long startTime = System.currentTimeMillis();

        try {
            Boolean result = empDevPlanServ.ifAnyEmpDevPlanExist(userId, devPlanId, assessmentYear);
            ManagerDto<Boolean> response = new ManagerDto<>();
            response.setContent(result);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Looking for EmpDevPlan with User ID: {}, Dev Plan ID: {}, and Assessment Year: {} success in {} ms", userId, devPlanId, assessmentYear, endTime - startTime);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error looking for EmpDevPlan with User ID: {}, Dev Plan ID: {}, and Assessment Year: {}. Error: {}", userId, devPlanId, assessmentYear, e.getMessage(), e);
            return new ResponseEntity<>("Failed to look for EmpDevPlan with User ID: " + userId + " Dev Plan ID: " + devPlanId + ", and Assessment Year: " + assessmentYear, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
