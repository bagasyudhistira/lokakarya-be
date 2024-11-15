package co.id.ogya.lokakarya.controller;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.emptechnicalskill.*;
import co.id.ogya.lokakarya.services.EmpTechnicalSkillServ;
import co.id.ogya.lokakarya.util.ServerResponseList;
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
@RequestMapping("/emptechnicalskill")
public class EmpTechnicalSkillController extends ServerResponseList {

    @Autowired
    private EmpTechnicalSkillServ empTechnicalSkillServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllEmpTechnicalSkills() {
        log.info("Fetching all EmpTechnicalSkills");
        long startTime = System.currentTimeMillis();

        try {
            List<EmpTechnicalSkillDto> result = empTechnicalSkillServ.getAllEmpTechnicalSkill();
            ManagerDto<List<EmpTechnicalSkillDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all EmpTechnicalSkills in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all EmpTechnicalSkills: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch EmpTechnicalSkills", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmpTechnicalSkillById(@PathVariable String id) {
        log.info("Fetching EmpTechnicalSkill by ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            EmpTechnicalSkillDto result = empTechnicalSkillServ.getEmpTechnicalSkillById(id);
            ManagerDto<EmpTechnicalSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched EmpTechnicalSkill with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching EmpTechnicalSkill by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch EmpTechnicalSkill with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmpTechnicalSkill(@RequestBody EmpTechnicalSkillCreateDto empTechnicalSkillCreateDto) {
        log.info("Creating EmpTechnicalSkill with data: {}", empTechnicalSkillCreateDto);
        long startTime = System.currentTimeMillis();

        try {
            EmpTechnicalSkillDto result = empTechnicalSkillServ.createEmpTechnicalSkill(empTechnicalSkillCreateDto);
            ManagerDto<EmpTechnicalSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Created EmpTechnicalSkill in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating EmpTechnicalSkill: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create EmpTechnicalSkill", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmpTechnicalSkill(@RequestBody EmpTechnicalSkillUpdateDto empTechnicalSkillUpdateDto) {
        log.info("Updating EmpTechnicalSkill with data: {}", empTechnicalSkillUpdateDto);
        long startTime = System.currentTimeMillis();

        try {
            EmpTechnicalSkillDto result = empTechnicalSkillServ.updateEmpTechnicalSkill(empTechnicalSkillUpdateDto);
            ManagerDto<EmpTechnicalSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Updated EmpTechnicalSkill in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating EmpTechnicalSkill: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update EmpTechnicalSkill", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpTechnicalSkill(@PathVariable String id) {
        log.info("Deleting EmpTechnicalSkill with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            boolean result = empTechnicalSkillServ.deleteEmpTechnicalSkill(id);
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(id + " deleted: " + result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Deleted EmpTechnicalSkill with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting EmpTechnicalSkill with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete EmpTechnicalSkill with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
