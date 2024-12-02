package co.id.ogya.lokakarya.controllers;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.empattitudeskill.*;
import co.id.ogya.lokakarya.services.EmpAttitudeSkillServ;
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
@RequestMapping("/empattitudeskill")
public class EmpAttitudeSkillController extends ServerResponseList {

    @Autowired
    EmpAttitudeSkillServ empAttitudeSkillServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllEmpAttitudeSkills() {
        log.info("Fetching all EmpAttitudeSkills");
        long startTime = System.currentTimeMillis();

        try {
            List<EmpAttitudeSkillDto> result = empAttitudeSkillServ.getAllEmpAttitudeSkill();
            ManagerDto<List<EmpAttitudeSkillDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all EmpAttitudeSkills in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all EmpAttitudeSkills: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch EmpAttitudeSkills", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmpAttitudeSkillById(@PathVariable String id) {
        log.info("Fetching EmpAttitudeSkill by ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            EmpAttitudeSkillDto result = empAttitudeSkillServ.getEmpAttitudeSkillById(id);
            ManagerDto<EmpAttitudeSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched EmpAttitudeSkill with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching EmpAttitudeSkill by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch EmpAttitudeSkill with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get/all")
    public ResponseEntity<?> getAllEmpAttitudeSkillGets() {
        log.info("Fetching all EmpAttitudeSkills");
        long startTime = System.currentTimeMillis();

        try {
            List<EmpAttitudeSkillGetDto> result = empAttitudeSkillServ.getAllEmpAttitudeSkillGet();
            ManagerDto<List<EmpAttitudeSkillGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all EmpAttitudeSkills in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all EmpAttitudeSkills: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch EmpAttitudeSkills", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getEmpAttitudeSkillGetById(@PathVariable String id) {
        log.info("Fetching EmpAttitudeSkill by ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            EmpAttitudeSkillGetDto result = empAttitudeSkillServ.getEmpAttitudeSkillGetById(id);
            ManagerDto<EmpAttitudeSkillGetDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched EmpAttitudeSkill with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching EmpAttitudeSkill by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch EmpAttitudeSkill with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getEmpAttitudeSkillGetByUserId(@PathVariable String userId) {
        log.info("Fetching EmpAttitudeSkill by ID: {}", userId);
        long startTime = System.currentTimeMillis();

        try {
            List<EmpAttitudeSkillGetDto> result = empAttitudeSkillServ.getAllEmpAttitudeSkillGetByUserId(userId);
            ManagerDto<List<EmpAttitudeSkillGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all EmpAttitudeSkills for User ID: {} in {} ms", userId, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all EmpAttitudeSkills for User ID: {} : {}", userId, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch EmpAttitudeSkills", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmpAttitudeSkill(@RequestBody EmpAttitudeSkillCreateDto empAttitudeSkillCreateDto) {
        log.info("Creating EmpAttitudeSkill with data: {}", empAttitudeSkillCreateDto);
        long startTime = System.currentTimeMillis();

        try {
            EmpAttitudeSkillDto result = empAttitudeSkillServ.createEmpAttitudeSkill(empAttitudeSkillCreateDto);
            ManagerDto<EmpAttitudeSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Created EmpAttitudeSkill in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating EmpAttitudeSkill: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create EmpAttitudeSkill", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmpAttitudeSkill(@RequestBody EmpAttitudeSkillUpdateDto empAttitudeSkillUpdateDto) {
        log.info("Updating EmpAttitudeSkill with data: {}", empAttitudeSkillUpdateDto);
        long startTime = System.currentTimeMillis();

        try {
            EmpAttitudeSkillDto result = empAttitudeSkillServ.updateEmpAttitudeSkill(empAttitudeSkillUpdateDto);
            ManagerDto<EmpAttitudeSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Updated EmpAttitudeSkill in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating EmpAttitudeSkill: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update EmpAttitudeSkill", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpAttitudeSkill(@PathVariable String id) {
        log.info("Deleting EmpAttitudeSkill with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            boolean result = empAttitudeSkillServ.deleteEmpAttitudeSkill(id);
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(id + " deleted: " + result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Deleted EmpAttitudeSkill with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting EmpAttitudeSkill with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete EmpAttitudeSkill with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
