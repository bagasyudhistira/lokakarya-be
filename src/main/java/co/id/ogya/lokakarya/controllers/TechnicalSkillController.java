package co.id.ogya.lokakarya.controllers;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.technicalskill.TechnicalSkillCreateDto;
import co.id.ogya.lokakarya.dto.technicalskill.TechnicalSkillDto;
import co.id.ogya.lokakarya.dto.technicalskill.TechnicalSkillUpdateDto;
import co.id.ogya.lokakarya.services.TechnicalSkillServ;
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
@RequestMapping("/technicalskill")
public class TechnicalSkillController extends ServerResponseList {

    @Autowired
    private TechnicalSkillServ technicalSkillServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllTechnicalSkills() {
        log.info("Fetching all TechnicalSkills");
        long startTime = System.currentTimeMillis();

        try {
            List<TechnicalSkillDto> result = technicalSkillServ.getAllTechnicalSkill();
            ManagerDto<List<TechnicalSkillDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all TechnicalSkills in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all TechnicalSkills: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch TechnicalSkills", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTechnicalSkillById(@PathVariable String id) {
        log.info("Fetching TechnicalSkill by ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            TechnicalSkillDto result = technicalSkillServ.getTechnicalSkillById(id);
            ManagerDto<TechnicalSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched TechnicalSkill with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching TechnicalSkill by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch TechnicalSkill with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTechnicalSkill(@RequestBody TechnicalSkillCreateDto technicalSkillCreateDto) {
        log.info("Creating TechnicalSkill with data: {}", technicalSkillCreateDto);
        long startTime = System.currentTimeMillis();

        try {
            TechnicalSkillDto result = technicalSkillServ.createTechnicalSkill(technicalSkillCreateDto);
            ManagerDto<TechnicalSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Created TechnicalSkill in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating TechnicalSkill: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create TechnicalSkill", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTechnicalSkill(@RequestBody TechnicalSkillUpdateDto technicalSkillUpdateDto) {
        log.info("Updating TechnicalSkill with data: {}", technicalSkillUpdateDto);
        long startTime = System.currentTimeMillis();

        try {
            TechnicalSkillDto result = technicalSkillServ.updateTechnicalSkill(technicalSkillUpdateDto);
            ManagerDto<TechnicalSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Updated TechnicalSkill in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating TechnicalSkill: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update TechnicalSkill", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTechnicalSkill(@PathVariable String id) {
        log.info("Deleting TechnicalSkill with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            boolean result = technicalSkillServ.deleteTechnicalSkill(id);
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(id + " deleted: " + result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Deleted TechnicalSkill with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting TechnicalSkill with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete TechnicalSkill with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/name/{technicalSkill}")
    public ResponseEntity<?> getTechnicalSkillByName(@PathVariable String technicalSkill) {
        log.info("Fetching TechnicalSkill by name: {}", technicalSkill);
        long startTime = System.currentTimeMillis();

        try {
            TechnicalSkillDto result = technicalSkillServ.getTechnicalSkillByName(technicalSkill);
            ManagerDto<TechnicalSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched TechnicalSkill with name: {} in {} ms", technicalSkill, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching TechnicalSkill by name {}: {}", technicalSkill, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch TechnicalSkill with name: " + technicalSkill, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all/{page}/{pageSize}")
    public ResponseEntity<?> getAllTechnicalSkillsPerPage(@PathVariable int page, @PathVariable int pageSize) {
        log.info("Fetching all TechnicalSkills");
        long startTime = System.currentTimeMillis();

        try {
            List<TechnicalSkillDto> result = technicalSkillServ.getAllTechnicalSkillPerPage(page, pageSize);
//            Long total = technicalSkillServ.countAllTechnicalSkill();
            ManagerDto<List<TechnicalSkillDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());
//            response.setTotalData(total);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched {} TechnicalSkills in {} ms", result.size(), endTime - startTime);
//            log.info("Total TechnicalSkills: {}", total);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all TechnicalSkills: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch TechnicalSkills", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sort/{order}/{page}/{pageSize}")
    public ResponseEntity<?> sortAllTechnicalSkillsOrderBy(@PathVariable String order, @PathVariable int page, @PathVariable int pageSize) {
        log.info("Sorting all TechnicalSkills");
        long startTime = System.currentTimeMillis();

        try {
            List<TechnicalSkillDto> result = technicalSkillServ.sortAllTechnicalSkill(order, page, pageSize);
//            Long total = technicalSkillServ.countAllTechnicalSkill();
            ManagerDto<List<TechnicalSkillDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());
//            response.setTotalData(total);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched {} TechnicalSkills in {} ms", result.size(), endTime - startTime);
//            log.info("Total TechnicalSkills: {}", total);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all TechnicalSkills: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch TechnicalSkills", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorch")
    public ResponseEntity<?> sorchAllTechnicalSkills(@RequestParam(required = false) String keyword, @RequestParam String column, @RequestParam String order, @RequestParam int page, @RequestParam int pageSize) {
        log.info("Sorching all TechnicalSkills");
        long startTime = System.currentTimeMillis();

        try {
            List<TechnicalSkillDto> result = technicalSkillServ.sorchAllTechnicalSkill(keyword, column, order, page, pageSize);
            Long total = technicalSkillServ.countAllTechnicalSkill(keyword);
            ManagerDto<List<TechnicalSkillDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());
            response.setTotalData(total);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched {} TechnicalSkills in {} ms", result.size(), endTime - startTime);
            log.info("Total TechnicalSkills: {}", total);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all TechnicalSkills: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch TechnicalSkills", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
