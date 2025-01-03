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
        log.info("Fetching all employee Achievement Skills");
        long startTime = System.currentTimeMillis();

        try {
            List<EmpAchievementSkillDto> result = empAchievementSkillServ.getAllEmpAchievementSkill();
            ManagerDto<List<EmpAchievementSkillDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all employee Achievement Skills in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all employee Achievement Skills: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch employee Achievement Skills", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmpAchievementSkillById(@PathVariable String id) {
        log.info("Fetching employee Achievement Skill with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            EmpAchievementSkillDto result = empAchievementSkillServ.getEmpAchievementSkillById(id);
            ManagerDto<EmpAchievementSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched employee Achievement Skill with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching employee Achievement Skill by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch employee Achievement Skill with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get/all")
    public ResponseEntity<?> getAllEmpAchievementSkillGets() {
        log.info("Fetching all employee Achievement Skills");
        long startTime = System.currentTimeMillis();

        try {
            List<EmpAchievementSkillGetDto> result = empAchievementSkillServ.getAllEmpAchievementSkillGet();
            ManagerDto<List<EmpAchievementSkillGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all employee Achievement Skills in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all employee Achievement Skills: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch employee Achievement Skills", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getEmpAchievementSkillGetById(@PathVariable String id) {
        log.info("Fetching employee Achievement Skill with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            EmpAchievementSkillGetDto result = empAchievementSkillServ.getEmpAchievementSkillGetById(id);
            ManagerDto<EmpAchievementSkillGetDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched employee Achievement Skill with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching employee Achievement Skill by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch employee Achievement Skill with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getEmpAchievementSkillGetsByUserId(@PathVariable String userId) {
        log.info("Fetching employee Achievement Skills by User ID: {}", userId);
        long startTime = System.currentTimeMillis();
        try {
            List<EmpAchievementSkillGetDto> result = empAchievementSkillServ.getAllEmpAchievementSkillGetByUserId(userId);
            ManagerDto<List<EmpAchievementSkillGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all employee Achievement Skills by User ID: {} in {} ms", userId, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all Achievement Skills by User ID: {} : {}", userId, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch employee Achievement Skills", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmpAchievementSkill(@RequestBody EmpAchievementSkillCreateDto empAchievementSkillCreateDto) {
        log.info("Creating new employee Achievement Skill with data: {}", empAchievementSkillCreateDto);
        long startTime = System.currentTimeMillis();

        try {
            EmpAchievementSkillDto result = empAchievementSkillServ.createEmpAchievementSkill(empAchievementSkillCreateDto);
            ManagerDto<EmpAchievementSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Created new employee Achievement Skill in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating employee Achievement Skill: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create employee Achievement Skill", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmpAchievementSkill(@RequestBody EmpAchievementSkillUpdateDto empAchievementSkillUpdateDto) {
        log.info("Updating employee Achievement Skill with data: {}", empAchievementSkillUpdateDto);
        long startTime = System.currentTimeMillis();

        try {
            EmpAchievementSkillDto result = empAchievementSkillServ.updateEmpAchievementSkill(empAchievementSkillUpdateDto);
            ManagerDto<EmpAchievementSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Updated employee Achievement Skill in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating employee Achievement Skill: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update employee Achievement Skill", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpAchievementSkill(@PathVariable String id) {
        log.info("Deleting employee Achievement Skill with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            boolean result = empAchievementSkillServ.deleteEmpAchievementSkill(id);
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(id + " deleted: " + result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Deleted employee Achievement Skill with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting employee Achievement Skill with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete employee Achievement Skill with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}/{achievementId}/{assessmentYear}")
    public ResponseEntity<?> ifAnyEmpAchievementSkillExist(@PathVariable String userId, @PathVariable String achievementId, @PathVariable int assessmentYear) {
        log.info("Looking for EmpAchievementSkill with User ID: {}, Achievement ID: {}, and Assessment Year: {}", userId, achievementId, assessmentYear);
        long startTime = System.currentTimeMillis();

        try {
            Boolean result = empAchievementSkillServ.ifAnyEmpAchievementSkillExist(userId, achievementId, assessmentYear);
            ManagerDto<Boolean> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Looking for EmpAchievementSkill with User ID: {}, Achievement ID: {}, and Assessment Year: {} success in {} ms", achievementId, userId, assessmentYear, endTime - startTime);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error looking for EmpAchievementSkill with User ID: {}, Achievement ID: {}, and Assessment Year: {}. Error: {}", userId, achievementId, assessmentYear, e.getMessage(), e);
            return new ResponseEntity<>("Failed to look for EmpAchievementSkill with User ID: " + userId + " Achievement ID: " + achievementId + ", and Assessment Year: " + assessmentYear, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{userId}/{assessmentYear}")
    public ResponseEntity<?> getEmpAchievementSkillGetsByUserIdAssessmentYear(@PathVariable String userId, @PathVariable int assessmentYear) {
        log.info("Fetching Employee Achievement Skills by User ID: {} and Assessment Year: {}", userId, assessmentYear);
        long startTime = System.currentTimeMillis();
        try {
            List<EmpAchievementSkillGetUIDYearDto> result = empAchievementSkillServ.getAllEmpAchievementSkillGetByUserIdAssessmentYear(userId, assessmentYear);
            ManagerDto<List<EmpAchievementSkillGetUIDYearDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all employee Achievement Skills by User ID: {} and Assessment Year: {} in {} ms", userId, assessmentYear, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all Achievement Skills by User ID: {} and Assessment Year: {} : {}", userId, assessmentYear, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch employee Achievement Skills", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
