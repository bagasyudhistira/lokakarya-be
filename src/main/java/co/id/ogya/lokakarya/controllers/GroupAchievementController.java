package co.id.ogya.lokakarya.controllers;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.groupachievement.*;
import co.id.ogya.lokakarya.services.GroupAchievementServ;
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
@RequestMapping("/groupachievement")
public class GroupAchievementController extends ServerResponseList {

    @Autowired
    private GroupAchievementServ groupAchievementServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllGroupAchievements() {
        log.info("Fetching all GroupAchievements");
        long startTime = System.currentTimeMillis();

        try {
            List<GroupAchievementDto> result = groupAchievementServ.getAllGroupAchievement();
            ManagerDto<List<GroupAchievementDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all GroupAchievements in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all GroupAchievements: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch GroupAchievements", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupAchievementById(@PathVariable String id) {
        log.info("Fetching GroupAchievement by ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            GroupAchievementDto result = groupAchievementServ.getGroupAchievementById(id);
            ManagerDto<GroupAchievementDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched GroupAchievement with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching GroupAchievement by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch GroupAchievement with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGroupAchievement(@RequestBody GroupAchievementCreateDto groupAchievementCreateDto) {
        log.info("Creating GroupAchievement with data: {}", groupAchievementCreateDto);
        long startTime = System.currentTimeMillis();

        try {
            GroupAchievementDto result = groupAchievementServ.createGroupAchievement(groupAchievementCreateDto);
            ManagerDto<GroupAchievementDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Created GroupAchievement in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating GroupAchievement: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create GroupAchievement", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateGroupAchievement(@RequestBody GroupAchievementUpdateDto groupAchievementUpdateDto) {
        log.info("Updating GroupAchievement with data: {}", groupAchievementUpdateDto);
        long startTime = System.currentTimeMillis();

        try {
            GroupAchievementDto result = groupAchievementServ.updateGroupAchievement(groupAchievementUpdateDto);
            ManagerDto<GroupAchievementDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Updated GroupAchievement in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating GroupAchievement: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update GroupAchievement", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroupAchievement(@PathVariable String id) {
        log.info("Deleting GroupAchievement with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            boolean result = groupAchievementServ.deleteGroupAchievement(id);
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(id + " deleted: " + result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Deleted GroupAchievement with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting GroupAchievement with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete GroupAchievement with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
