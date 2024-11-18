package co.id.ogya.lokakarya.controllers;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.attitudeskill.AttitudeSkillCreateDto;
import co.id.ogya.lokakarya.dto.attitudeskill.AttitudeSkillDto;
import co.id.ogya.lokakarya.dto.attitudeskill.AttitudeSkillUpdateDto;
import co.id.ogya.lokakarya.services.AttitudeSkillServ;
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
@RequestMapping("/attitudeskill")
public class AttitudeSkillController extends ServerResponseList {

    @Autowired
    AttitudeSkillServ attitudeSkillServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllAttitudeSkills() {
        log.info("Fetching all AttitudeSkills");
        long startTime = System.currentTimeMillis();

        try {
            List<AttitudeSkillDto> result = attitudeSkillServ.getAllAttitudeSkill();
            ManagerDto<List<AttitudeSkillDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all AttitudeSkills in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all AttitudeSkills: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AttitudeSkills", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAttitudeSkillById(@PathVariable String id) {
        log.info("Fetching AttitudeSkill with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            AttitudeSkillDto result = attitudeSkillServ.getAttitudeSkillById(id);
            ManagerDto<AttitudeSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched AttitudeSkill with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching AttitudeSkill by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AttitudeSkill with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAttitudeSkill(@RequestBody AttitudeSkillCreateDto attitudeSkillCreateDto) {
        log.info("Creating new AttitudeSkill with data: {}", attitudeSkillCreateDto);
        long startTime = System.currentTimeMillis();

        try {
            AttitudeSkillDto result = attitudeSkillServ.createAttitudeSkill(attitudeSkillCreateDto);
            ManagerDto<AttitudeSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Created new AttitudeSkill in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating AttitudeSkill: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create AttitudeSkill", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAttitudeSkill(@RequestBody AttitudeSkillUpdateDto attitudeSkillUpdateDto) {
        log.info("Updating AttitudeSkill with data: {}", attitudeSkillUpdateDto);
        long startTime = System.currentTimeMillis();

        try {
            AttitudeSkillDto result = attitudeSkillServ.updateAttitudeSkill(attitudeSkillUpdateDto);
            ManagerDto<AttitudeSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Updated AttitudeSkill in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating AttitudeSkill: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update AttitudeSkill", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttitudeSkill(@PathVariable String id) {
        log.info("Deleting AttitudeSkill with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            boolean result = attitudeSkillServ.deleteAttitudeSkill(id);
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(id + " deleted: " + result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Deleted AttitudeSkill with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting AttitudeSkill with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete AttitudeSkill with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
