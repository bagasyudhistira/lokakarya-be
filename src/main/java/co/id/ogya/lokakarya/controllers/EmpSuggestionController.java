package co.id.ogya.lokakarya.controllers;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.empsuggestion.*;
import co.id.ogya.lokakarya.services.EmpSuggestionServ;
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
@RequestMapping("/empsuggestion")
public class EmpSuggestionController extends ServerResponseList {

    @Autowired
    private EmpSuggestionServ empSuggestionServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllEmpSuggestions() {
        log.info("Fetching all EmpSuggestions");
        long startTime = System.currentTimeMillis();

        try {
            List<EmpSuggestionDto> result = empSuggestionServ.getAllEmpSuggestion();
            ManagerDto<List<EmpSuggestionDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all EmpSuggestions in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all EmpSuggestions: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch EmpSuggestions", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmpSuggestionById(@PathVariable String id) {
        log.info("Fetching EmpSuggestion by ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            EmpSuggestionDto result = empSuggestionServ.getEmpSuggestionById(id);
            ManagerDto<EmpSuggestionDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched EmpSuggestion with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching EmpSuggestion by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch EmpSuggestion with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmpSuggestion(@RequestBody EmpSuggestionCreateDto empSuggestionCreateDto) {
        log.info("Creating EmpSuggestion with data: {}", empSuggestionCreateDto);
        long startTime = System.currentTimeMillis();

        try {
            EmpSuggestionDto result = empSuggestionServ.createEmpSuggestion(empSuggestionCreateDto);
            ManagerDto<EmpSuggestionDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Created EmpSuggestion in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating EmpSuggestion: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create EmpSuggestion", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmpSuggestion(@RequestBody EmpSuggestionUpdateDto empSuggestionUpdateDto) {
        log.info("Updating EmpSuggestion with data: {}", empSuggestionUpdateDto);
        long startTime = System.currentTimeMillis();

        try {
            EmpSuggestionDto result = empSuggestionServ.updateEmpSuggestion(empSuggestionUpdateDto);
            ManagerDto<EmpSuggestionDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Updated EmpSuggestion in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating EmpSuggestion: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update EmpSuggestion", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpSuggestion(@PathVariable String id) {
        log.info("Deleting EmpSuggestion with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            boolean result = empSuggestionServ.deleteEmpSuggestion(id);
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(id + " deleted: " + result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Deleted EmpSuggestion with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting EmpSuggestion with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete EmpSuggestion with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllEmpSuggestionGets() {
        log.info("Fetching all EmpSuggestions");
        long startTime = System.currentTimeMillis();

        try {
            List<EmpSuggestionGetDto> result = empSuggestionServ.getAllEmpSuggestionGets();
            ManagerDto<List<EmpSuggestionGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all EmpSuggestions in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all EmpSuggestions: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch EmpSuggestions", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getEmpSuggestionGetById(@PathVariable String userId) {
        log.info("Fetching EmpSuggestion by ID: {}", userId);
        long startTime = System.currentTimeMillis();

        try {
            List<EmpSuggestionGetDto> result = empSuggestionServ.getEmpSuggestionGetByUserId(userId);
            ManagerDto<List<EmpSuggestionGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched EmpSuggestion with ID: {} in {} ms", userId, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching EmpSuggestion by ID {}: {}", userId, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch EmpSuggestion with ID: " + userId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/by/{userId}")
    public ResponseEntity<?> getEmpSuggestionGetByCreatedBy(@PathVariable String userId) {
        log.info("Fetching EmpSuggestion by ID: {}", userId);
        long startTime = System.currentTimeMillis();

        try {
            List<EmpSuggestionGetDto> result = empSuggestionServ.getEmpSuggestionGetByCreatedBy(userId);
            ManagerDto<List<EmpSuggestionGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched EmpSuggestion by ID: {} in {} ms", userId, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching EmpSuggestion by ID {}: {}", userId, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch EmpSuggestion by ID: " + userId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
