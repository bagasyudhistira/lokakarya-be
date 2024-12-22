package co.id.ogya.lokakarya.controllers;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.appuser.AppUserGetDto;
import co.id.ogya.lokakarya.dto.assessmentsummary.*;
import co.id.ogya.lokakarya.services.AssessmentSummaryServ;
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
@RequestMapping("/assessmentsummary")
public class AssessmentSummaryController extends ServerResponseList {
    @Autowired
    AssessmentSummaryServ assessmentSummaryServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllAssessmentSummaries() {
        log.info("Fetching all AssessmentSummaries");
        long startTime = System.currentTimeMillis();

        try {
            List<AssessmentSummaryDto> result = assessmentSummaryServ.getAllAssessmentSummary();
            ManagerDto<List<AssessmentSummaryDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all AssessmentSummaries in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all AssessmentSummaries: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AssessmentSummaries", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAssessmentSummaryById(@PathVariable String id) {
        log.info("Fetching AssessmentSummary with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            AssessmentSummaryDto result = assessmentSummaryServ.getAssessmentSummaryById(id);
            ManagerDto<AssessmentSummaryDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched AssessmentSummary with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching AssessmentSummary by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AssessmentSummary with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get/all")
    public ResponseEntity<?> getAllAssessmentSummaryGets() {
        log.info("Fetching all AssessmentSummaries");
        long startTime = System.currentTimeMillis();

        try {
            List<AssessmentSummaryGetDto> result = assessmentSummaryServ.getAllAssessmentSummaryGet();
            ManagerDto<List<AssessmentSummaryGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all AssessmentSummaries in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all AssessmentSummaries: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AssessmentSummaries", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAssessmentSummaryGetById(@PathVariable String id) {
        log.info("Fetching AssessmentSummary with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            AssessmentSummaryGetDto result = assessmentSummaryServ.getAssessmentSummaryGetById(id);
            ManagerDto<AssessmentSummaryGetDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched AssessmentSummary with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching AssessmentSummary by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AssessmentSummary with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{userId}/{year}")
    public ResponseEntity<?> getAssessmentSummaryGetByUserIdAndAssessmentYear(@PathVariable String userId, @PathVariable int year) {
        log.info("Fetching AssessmentSummary with ID: {} year {}", userId, year);
        long startTime = System.currentTimeMillis();

        try {
            AssessmentSummaryGetDto result = assessmentSummaryServ.getAssessmentSummaryGetByUserIdAndAssessmentYear(userId,year);
            ManagerDto<AssessmentSummaryGetDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched AssessmentSummary with ID: {} in {} ms", userId, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching AssessmentSummary by ID {}: {}", userId, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AssessmentSummary with ID: " + userId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAssessmentSummary(@RequestBody AssessmentSummaryCreateDto assessmentSummaryCreateDto) {
        log.info("Creating new AssessmentSummary with data: {}", assessmentSummaryCreateDto);
        long startTime = System.currentTimeMillis();

        try {
            AssessmentSummaryDto result = assessmentSummaryServ.createAssessmentSummary(assessmentSummaryCreateDto);
            ManagerDto<AssessmentSummaryDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Created new AssessmentSummary in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error creating AssessmentSummary: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create AssessmentSummary", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAssessmentSummary(@RequestBody AssessmentSummaryUpdateDto assessmentSummaryUpdateDto) {
        log.info("Updating AssessmentSummary with data: {}", assessmentSummaryUpdateDto);
        long startTime = System.currentTimeMillis();

        try {
            AssessmentSummaryDto result = assessmentSummaryServ.updateAssessmentSummary(assessmentSummaryUpdateDto);
            ManagerDto<AssessmentSummaryDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Updated AssessmentSummary in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating AssessmentSummary: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update AssessmentSummary", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAssessmentSummary(@PathVariable String id) {
        log.info("Deleting AssessmentSummary with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            boolean result = assessmentSummaryServ.deleteAssessmentSummary(id);
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(id + " deleted: " + result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Deleted AssessmentSummary with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting AssessmentSummary with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete AssessmentSummary with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/achievementsummary/{userId}/{assessmentYear}")
    public ResponseEntity<?> getAchievementSummaryByUserIdAssessmentYear(@PathVariable String userId, @PathVariable int assessmentYear) {
        log.info("Fetching all AchievementSummary for User ID: {} and Assessment Year: {}", userId, assessmentYear);
        long startTime = System.currentTimeMillis();

        try {
            List<AchievementSummaryGetDto> result = assessmentSummaryServ.getAchievementSummaryByUserIdAssessmentYear(userId, assessmentYear);
            ManagerDto<List<AchievementSummaryGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all AchievementSummary for User ID: {} and Assessment Year: {} in {} ms", userId, assessmentYear, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all AchievementSummaries: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch Achievement Summary", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/attitudeskillsummary/{userId}/{assessmentYear}")
    public ResponseEntity<?> getAttitudeSkillSummaryByUserIdAssessmentYear(@PathVariable String userId, @PathVariable int assessmentYear) {
        log.info("Fetching all AttitudeSkillSummary for User ID: {} and Assessment Year: {}", userId, assessmentYear);
        long startTime = System.currentTimeMillis();

        try {
            List<AttitudeSkillSummaryGetDto> result = assessmentSummaryServ.getAttitudeSkillSummaryByUserIdAssessmentYear(userId, assessmentYear);
            ManagerDto<List<AttitudeSkillSummaryGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all AttitudeSkillSummary for User ID: {} and Assessment Year: {} in {} ms", userId, assessmentYear, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all AttitudeSkillSummaries: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AttitudeSkillSummary", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/divyear/{divisionId}/{assessmentYear}")
    public ResponseEntity<?> getAssessmentSummariesByDivisionIdAssessmentYear(@PathVariable String divisionId, @PathVariable int assessmentYear) {
        log.info("Fetching all AssessmentSummaries for Division ID: {} and Assessment Year: {}", divisionId, assessmentYear);
        long startTime = System.currentTimeMillis();

        try {
            List<AssessmentSummaryJointGetDto> result = assessmentSummaryServ.getAssessmentSummariesByDivisionIdAssessmentYear(divisionId, assessmentYear);
            ManagerDto<List<AssessmentSummaryJointGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all AssessmentSummaries for Division ID: {} and Assessment Year: {} in {} ms", divisionId, assessmentYear, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all AssessmentSummaries: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AssessmentSummaries", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/year/{assessmentYear}")
    public ResponseEntity<?> getAssessmentSummariesByAssessmentYear(@PathVariable int assessmentYear) {
        log.info("Fetching all AssessmentSummaries for Assessment Year: {}", assessmentYear);
        long startTime = System.currentTimeMillis();

        try {
            List<AssessmentSummaryJointGetDto> result = assessmentSummaryServ.getAssessmentSummariesByAssessmentYear(assessmentYear);
            ManagerDto<List<AssessmentSummaryJointGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all AssessmentSummaries for Assessment Year: {} in {} ms", assessmentYear, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all AssessmentSummaries: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AssessmentSummaries", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/all/{page}/{pageSize}")
    public ResponseEntity<?> getAllAssessmentSummaryGetsPerPage(@PathVariable int page, @PathVariable int pageSize) {
        log.info("Fetching all AssessmentSummaries");
        long startTime = System.currentTimeMillis();

        try {
            List<AssessmentSummaryGetDto> result = assessmentSummaryServ.getAllAssessmentSummaryGetPerPage(page, pageSize);
            Long total = assessmentSummaryServ.countAllAssessmentSummary();
            ManagerDto<List<AssessmentSummaryGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());
            response.setTotalData(total);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched {} AssessmentSummaries in {} ms", result.size(), endTime - startTime);
            log.info("Total AssessmentSummaries: {}", total);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all AssessmentSummaries: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AssessmentSummaries", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sort/{column}/{order}/{page}/{pageSize}")
    public ResponseEntity<?> sortAllAssessmentSummaryGetsOrderBy(@PathVariable String column, @PathVariable String order, @PathVariable int page, @PathVariable int pageSize) {
        log.info("Sorting all AssessmentSummaries");
        long startTime = System.currentTimeMillis();

        try {
            List<AssessmentSummaryGetDto> result = assessmentSummaryServ.sortAllAssessmentSummaryGetOrderBy(column, order, page, pageSize);
            Long total = assessmentSummaryServ.countAllAssessmentSummary();
            ManagerDto<List<AssessmentSummaryGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());
            response.setTotalData(total);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched {} AppUsers in {} ms", result.size(), endTime - startTime);
            log.info("Total AssessmentSummaries: {}", total);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all AssessmentSummaries: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AssessmentSummaries", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
