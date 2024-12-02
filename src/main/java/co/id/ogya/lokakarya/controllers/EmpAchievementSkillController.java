package co.id.ogya.lokakarya.controllers;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.empachievementskill.*;
import co.id.ogya.lokakarya.services.EmpAchievementSkillServ;
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
@RequestMapping("/empachievementskill")
public class EmpAchievementSkillController extends ServerResponseList {

    @Autowired
    EmpAchievementSkillServ empAchievementSkillServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllEmpAchievementSkills() {
        log.info("Fetching all employee achievement skills");
        long startTime = System.currentTimeMillis();

        try {
            List<EmpAchievementSkillDto> result = empAchievementSkillServ.getAllEmpAchievementSkill();
            ManagerDto<List<EmpAchievementSkillDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all employee achievement skills in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all employee achievement skills: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch employee achievement skills", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmpAchievementSkillById(@PathVariable String id) {
        log.info("Fetching employee achievement skill with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            EmpAchievementSkillDto result = empAchievementSkillServ.getEmpAchievementSkillById(id);
            ManagerDto<EmpAchievementSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched employee achievement skill with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching employee achievement skill by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch employee achievement skill with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get/all")
    public ResponseEntity<?> getAllEmpAchievementSkillGets() {
        log.info("Fetching all employee achievement skills");
        long startTime = System.currentTimeMillis();

        try {
            List<EmpAchievementSkillGetDto> result = empAchievementSkillServ.getAllEmpAchievementSkillGet();
            ManagerDto<List<EmpAchievementSkillGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all employee achievement skills in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all employee achievement skills: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch employee achievement skills", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getEmpAchievementSkillGetById(@PathVariable String id) {
        log.info("Fetching employee achievement skill with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            EmpAchievementSkillGetDto result = empAchievementSkillServ.getEmpAchievementSkillGetById(id);
            ManagerDto<EmpAchievementSkillGetDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched employee achievement skill with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching employee achievement skill by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch employee achievement skill with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getEmpAchievementSkillGetsByUserId(@PathVariable String userId) {
        log.info("Fetching employee achievement skills by user ID: {}", userId);
        long startTime = System.currentTimeMillis();
        try {
            List<EmpAchievementSkillGetDto> result = empAchievementSkillServ.getAllEmpAchievementSkillGetByUserId(userId);
            ManagerDto<List<EmpAchievementSkillGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all employee achievement skills by user ID: {} in {} ms", userId, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all achievement skills by user ID: {} : {}", userId, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch employee achievement skills", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmpAchievementSkill(@RequestBody EmpAchievementSkillCreateDto empAchievementSkillCreateDto) {
        log.info("Creating new employee achievement skill with data: {}", empAchievementSkillCreateDto);
        long startTime = System.currentTimeMillis();

        try {
            EmpAchievementSkillDto result = empAchievementSkillServ.createEmpAchievementSkill(empAchievementSkillCreateDto);
            ManagerDto<EmpAchievementSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Created new employee achievement skill in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating employee achievement skill: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create employee achievement skill", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmpAchievementSkill(@RequestBody EmpAchievementSkillUpdateDto empAchievementSkillUpdateDto) {
        log.info("Updating employee achievement skill with data: {}", empAchievementSkillUpdateDto);
        long startTime = System.currentTimeMillis();

        try {
            EmpAchievementSkillDto result = empAchievementSkillServ.updateEmpAchievementSkill(empAchievementSkillUpdateDto);
            ManagerDto<EmpAchievementSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Updated employee achievement skill in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating employee achievement skill: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update employee achievement skill", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpAchievementSkill(@PathVariable String id) {
        log.info("Deleting employee achievement skill with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            boolean result = empAchievementSkillServ.deleteEmpAchievementSkill(id);
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(id + " deleted: " + result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Deleted employee achievement skill with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting employee achievement skill with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete employee achievement skill with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
