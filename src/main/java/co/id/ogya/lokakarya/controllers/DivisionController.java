package co.id.ogya.lokakarya.controllers;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.appuser.AppUserGetDto;
import co.id.ogya.lokakarya.dto.division.DivisionCreateDto;
import co.id.ogya.lokakarya.dto.division.DivisionDto;
import co.id.ogya.lokakarya.dto.division.DivisionUpdateDto;
import co.id.ogya.lokakarya.services.DivisionServ;
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
@RequestMapping("/division")
public class DivisionController extends ServerResponseList {

    @Autowired
    DivisionServ divisionServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllDivisions() {
        log.info("Fetching all divisions");
        long startTime = System.currentTimeMillis();

        try {
            List<DivisionDto> result = divisionServ.getAllDivision();
            ManagerDto<List<DivisionDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all divisions in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all divisions: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch divisions", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDivisionById(@PathVariable String id) {
        log.info("Fetching division with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            DivisionDto result = divisionServ.getDivisionById(id);
            ManagerDto<DivisionDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched division with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching division by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch division with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDivision(@RequestBody DivisionCreateDto divisionCreateDto) {
        log.info("Creating new division with data: {}", divisionCreateDto);
        long startTime = System.currentTimeMillis();

        try {
            DivisionDto result = divisionServ.createDivision(divisionCreateDto);
            ManagerDto<DivisionDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Created new division in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating division: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create division", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDivision(@RequestBody DivisionUpdateDto divisionUpdateDto) {
        log.info("Updating division with data: {}", divisionUpdateDto);
        long startTime = System.currentTimeMillis();

        try {
            DivisionDto result = divisionServ.updateDivision(divisionUpdateDto);
            ManagerDto<DivisionDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Updated division in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating division: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update division", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDivision(@PathVariable String id) {
        log.info("Deleting division with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            boolean result = divisionServ.deleteDivision(id);
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(id + " deleted: " + result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Deleted division with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting division with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete division with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/name/{divisionName}")
    public ResponseEntity<?> getDivisionByName(@PathVariable String divisionName) {
        log.info("Fetching division with name: {}", divisionName);
        long startTime = System.currentTimeMillis();

        try {
            DivisionDto result = divisionServ.getDivisionByName(divisionName);
            ManagerDto<DivisionDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched division with name: {} in {} ms", divisionName, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching division by name {}: {}", divisionName, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch division with name: " + divisionName, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all/{page}/{pageSize}")
    public ResponseEntity<?> getAllDivisionsPerPage(@PathVariable int page, @PathVariable int pageSize) {
        log.info("Fetching all Divisions");
        long startTime = System.currentTimeMillis();

        try {
            List<DivisionDto> result = divisionServ.getAllDivisionPerPage(page, pageSize);
//            Long total = divisionServ.countAllDivision();
            ManagerDto<List<DivisionDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());
//            response.setTotalData(total);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched {} Divisions in {} ms", result.size(), endTime - startTime);
//            log.info("Total Divisions: {}", total);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all Divisions: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch Divisions", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sort/{order}/{page}/{pageSize}")
    public ResponseEntity<?> sortAllDivisionsOrderBy(@PathVariable String order, @PathVariable int page, @PathVariable int pageSize) {
        log.info("Sorting all Divisions");
        long startTime = System.currentTimeMillis();

        try {
            List<DivisionDto> result = divisionServ.sortAllDivision(order, page, pageSize);
//            Long total = divisionServ.countAllDivision();
            ManagerDto<List<DivisionDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());
//            response.setTotalData(total);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched {} Divisions in {} ms", result.size(), endTime - startTime);
//            log.info("Total Divisions: {}", total);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all Divisions: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch Divisions", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorch")
    public ResponseEntity<?> sorchAllDivisions(@RequestParam(required = false) String keyword, @RequestParam String column, @RequestParam String order, @RequestParam int page, @RequestParam int pageSize) {
        log.info("Sorching all Divisions");
        long startTime = System.currentTimeMillis();

        try {
            List<DivisionDto> result = divisionServ.sorchAllDivision(keyword, column, order, page, pageSize);
            Long total = divisionServ.countAllDivision(keyword);
            ManagerDto<List<DivisionDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());
            response.setTotalData(total);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched {} Divisions in {} ms", result.size(), endTime - startTime);
            log.info("Total Divisions: {}", total);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all Divisions: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch Divisions", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
