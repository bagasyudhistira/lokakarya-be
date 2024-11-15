package co.id.ogya.lokakarya.controller;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.assessmentsummary.AssessmentSummaryCreateDto;
import co.id.ogya.lokakarya.dto.assessmentsummary.AssessmentSummaryDto;
import co.id.ogya.lokakarya.dto.assessmentsummary.AssessmentSummaryUpdateDto;
import co.id.ogya.lokakarya.services.AssessmentSummaryServ;
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

    @PostMapping("/create")
    public ResponseEntity<?> createAssessmentSummary(@RequestBody AssessmentSummaryCreateDto assessmentSummaryCreateDto) {
        log.info("Creating new AssessmentSummary with data: {}", assessmentSummaryCreateDto);
        long startTime = System.currentTimeMillis();

        try {
            AssessmentSummary result = assessmentSummaryServ.createAssessmentSummary(assessmentSummaryCreateDto);
            ManagerDto<AssessmentSummary> response = new ManagerDto<>();
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
            AssessmentSummary result = assessmentSummaryServ.updateAssessmentSummary(assessmentSummaryUpdateDto);
            ManagerDto<AssessmentSummary> response = new ManagerDto<>();
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
}
