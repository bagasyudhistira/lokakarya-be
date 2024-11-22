package co.id.ogya.lokakarya.controllers;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.accessdivision.*;
import co.id.ogya.lokakarya.services.AccessDivisionServ;
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
@RequestMapping("/accessdivision")
public class AccessDivisionController extends ServerResponseList {
    @Autowired
    AccessDivisionServ accessDivisionServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllAccessDivisions() {
        log.info("Fetching all access divisions");
        long startTime = System.currentTimeMillis();

        try {
            List<AccessDivisionDto> result = accessDivisionServ.getAllAccessDivision();
            ManagerDto<List<AccessDivisionDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            response.setInfo(getInfoOk("Time", totalTime));
            log.info("Successfully fetched all access divisions in {} ms", totalTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all access divisions: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch access divisions", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccessDivisionById(@PathVariable String id) {
        log.info("Fetching access division with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            AccessDivisionDto result = accessDivisionServ.getAccessDivisionById(id);
            ManagerDto<AccessDivisionDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            response.setInfo(getInfoOk("Time", totalTime));
            log.info("Successfully fetched access division with ID: {} in {} ms", id, totalTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching access division by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch access division with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get/all")
    public ResponseEntity<?> getAllAccessDivisionGets() {
        log.info("Fetching all access divisions");
        long startTime = System.currentTimeMillis();

        try {
            List<AccessDivisionGetDto> result = accessDivisionServ.getAllAccessDivisionGet();
            ManagerDto<List<AccessDivisionGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            response.setInfo(getInfoOk("Time", totalTime));
            log.info("Successfully fetched all access divisions in {} ms", totalTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all access divisions: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch access divisions", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAccessDivisionGetById(@PathVariable String id) {
        log.info("Fetching access division with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            AccessDivisionGetDto result = accessDivisionServ.getAccessDivisionGetById(id);
            ManagerDto<AccessDivisionGetDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            response.setInfo(getInfoOk("Time", totalTime));
            log.info("Successfully fetched access division with ID: {} in {} ms", id, totalTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching access division by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch access division with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccessDivision(@RequestBody AccessDivisionCreateDto accessDivisionCreateDto) {
        log.info("Creating new access division with data: {}", accessDivisionCreateDto);
        long startTime = System.currentTimeMillis();

        try {
            AccessDivisionDto result = accessDivisionServ.createAccessDivision(accessDivisionCreateDto);
            ManagerDto<AccessDivisionDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            response.setInfo(getInfoOk("Time", totalTime));
            log.info("Successfully created new access division in {} ms", totalTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error creating access division: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create access division", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAccessDivision(@RequestBody AccessDivisionUpdateDto accessDivisionUpdateDto) {
        log.info("Updating access division with data: {}", accessDivisionUpdateDto);
        long startTime = System.currentTimeMillis();

        try {
            AccessDivisionDto result = accessDivisionServ.updateAccessDivision(accessDivisionUpdateDto);
            ManagerDto<AccessDivisionDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            response.setInfo(getInfoOk("Time", totalTime));
            log.info("Successfully updated access division in {} ms", totalTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating access division: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update access division", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccessDivision(@PathVariable String id) {
        log.info("Deleting access division with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            boolean result = accessDivisionServ.deleteAccessDivision(id);
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(id + " deleted: " + result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            response.setInfo(getInfoOk("Time", totalTime));
            log.info("Successfully deleted access division with ID: {} in {} ms", id, totalTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting access division with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete access division with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
