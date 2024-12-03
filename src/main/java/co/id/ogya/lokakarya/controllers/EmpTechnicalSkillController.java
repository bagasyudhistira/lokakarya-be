package co.id.ogya.lokakarya.controllers;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.emptechnicalskill.*;
import co.id.ogya.lokakarya.services.EmpTechnicalSkillServ;
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

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllEmpTechnicalSkillGets() {
        log.info("Fetching all EmpTechnicalSkills");
        long startTime = System.currentTimeMillis();

        try {
            List<EmpTechnicalSkillGetDto> result = empTechnicalSkillServ.getAllEmpTechnicalSkillGets();
            ManagerDto<List<EmpTechnicalSkillGetDto>> response = new ManagerDto<>();
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

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getEmpTechnicalSkillGetByUserId(@PathVariable String userId) {
        log.info("Fetching EmpTechnicalSkill by ID: {}", userId);
        long startTime = System.currentTimeMillis();

        try {
            List<EmpTechnicalSkillGetDto> result = empTechnicalSkillServ.getEmpTechnicalSkillGetByUserId(userId);
            ManagerDto<List<EmpTechnicalSkillGetDto>> response = new ManagerDto<>();
            response.setContent(result);
//            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched EmpTechnicalSkill with ID: {} in {} ms", userId, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching EmpTechnicalSkill by ID {}: {}", userId, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch EmpTechnicalSkill with ID: " + userId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}/{technicalSkillId}/{assessmentYear}")
    public ResponseEntity<?> ifAnyEmpTechnicalSkillExist(@PathVariable String userId, @PathVariable String technicalSkillId, @PathVariable int assessmentYear) {
        log.info("Looking for EmpTechnicalSkill with User ID: {}, Technical Skill ID: {}, and Assessment Year: {}", userId, technicalSkillId, assessmentYear);
        long startTime = System.currentTimeMillis();

        try {
            Boolean result = empTechnicalSkillServ.ifAnyEmpTechnicalSkillExist(userId, technicalSkillId, assessmentYear);
            ManagerDto<Boolean> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Looking for EmpTechnicalSkill with User ID: {}, Technical Skill ID: {}, and Assessment Year: {} success in {} ms", userId, technicalSkillId, assessmentYear, endTime - startTime);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error looking for EmpTechnicalSkill with User ID: {}, Technical Skill ID: {}, and Assessment Year: {}. Error: {}", userId, technicalSkillId, assessmentYear, e.getMessage(), e);
            return new ResponseEntity<>("Failed to look for EmpTechnicalSkill with User ID: " + userId + " Technical Skill ID: " + technicalSkillId + ", and Assessment Year: " + assessmentYear, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{userId}/{assessmentYear}")
    public ResponseEntity<?> getEmpTechnicalSkillGetsByUserIdAssessmentYear(@PathVariable String userId, @PathVariable int assessmentYear) {
        log.info("Fetching Employee Technical Skills by User ID: {} and Assessment Year: {}", userId, assessmentYear);
        long startTime = System.currentTimeMillis();
        try {
            List<EmpTechnicalSkillGetUIDYearDto> result = empTechnicalSkillServ.getAllEmpTechnicalSkillGetByUserIdAssessmentYear(userId, assessmentYear);
            ManagerDto<List<EmpTechnicalSkillGetUIDYearDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all employee Technical Skills by User ID: {} and Assessment Year: {} in {} ms", userId, assessmentYear, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all Technical Skills by User ID: {} and Assessment Year: {} : {}", userId, assessmentYear, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch employee Technical Skills", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
