package co.id.ogya.lokakarya.controllers;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.groupattitudeskill.GroupAttitudeSkillCreateDto;
import co.id.ogya.lokakarya.dto.groupattitudeskill.GroupAttitudeSkillDto;
import co.id.ogya.lokakarya.dto.groupattitudeskill.GroupAttitudeSkillUpdateDto;
import co.id.ogya.lokakarya.services.GroupAttitudeSkillServ;
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
@RequestMapping("/groupattitudeskill")
public class GroupAttitudeSkillController extends ServerResponseList {

    @Autowired
    private GroupAttitudeSkillServ groupAttitudeSkillServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllGroupAttitudeSkills() {
        log.info("Fetching all GroupAttitudeSkills");
        long startTime = System.currentTimeMillis();

        try {
            List<GroupAttitudeSkillDto> result = groupAttitudeSkillServ.getAllGroupAttitudeSkill();
            ManagerDto<List<GroupAttitudeSkillDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all GroupAttitudeSkills in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all GroupAttitudeSkills: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch GroupAttitudeSkills", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupAttitudeSkillById(@PathVariable String id) {
        log.info("Fetching GroupAttitudeSkill by ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            GroupAttitudeSkillDto result = groupAttitudeSkillServ.getGroupAttitudeSkillById(id);
            ManagerDto<GroupAttitudeSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched GroupAttitudeSkill with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching GroupAttitudeSkill by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch GroupAttitudeSkill with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGroupAttitudeSkill(@RequestBody GroupAttitudeSkillCreateDto groupAttitudeSkillCreateDto) {
        log.info("Creating GroupAttitudeSkill with data: {}", groupAttitudeSkillCreateDto);
        long startTime = System.currentTimeMillis();

        try {
            GroupAttitudeSkillDto result = groupAttitudeSkillServ.createGroupAttitudeSkill(groupAttitudeSkillCreateDto);
            ManagerDto<GroupAttitudeSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Created GroupAttitudeSkill in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating GroupAttitudeSkill: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create GroupAttitudeSkill", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateGroupAttitudeSkill(@RequestBody GroupAttitudeSkillUpdateDto groupAttitudeSkillUpdateDto) {
        log.info("Updating GroupAttitudeSkill with data: {}", groupAttitudeSkillUpdateDto);
        long startTime = System.currentTimeMillis();

        try {
            GroupAttitudeSkillDto result = groupAttitudeSkillServ.updateGroupAttitudeSkill(groupAttitudeSkillUpdateDto);
            ManagerDto<GroupAttitudeSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Updated GroupAttitudeSkill in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating GroupAttitudeSkill: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update GroupAttitudeSkill", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroupAttitudeSkill(@PathVariable String id) {
        log.info("Deleting GroupAttitudeSkill with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            boolean result = groupAttitudeSkillServ.deleteGroupAttitudeSkill(id);
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(id + " deleted: " + result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Deleted GroupAttitudeSkill with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting GroupAttitudeSkill with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete GroupAttitudeSkill with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/name/{groupName}")
    public ResponseEntity<?> getGroupAttitudeSkillByGroupName(@PathVariable String groupName) {
        log.info("Fetching GroupAttitudeSkill by name: {}", groupName);
        long startTime = System.currentTimeMillis();

        try {
            GroupAttitudeSkillDto result = groupAttitudeSkillServ.getGroupAttitudeSkillByGroupName(groupName);
            ManagerDto<GroupAttitudeSkillDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched GroupAttitudeSkill with name: {} in {} ms", groupName, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching GroupAttitudeSkill by name {}: {}", groupName, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch GroupAttitudeSkill with name: " + groupName, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all/{page}/{pageSize}")
    public ResponseEntity<?> getAllGroupAttitudeSkillsPerPage(@PathVariable int page, @PathVariable int pageSize) {
        log.info("Fetching all GroupAttitudeSkills");
        long startTime = System.currentTimeMillis();

        try {
            List<GroupAttitudeSkillDto> result = groupAttitudeSkillServ.getAllGroupAttitudeSkillPerPage(page, pageSize);
//            Long total = groupAttitudeSkillServ.countAllGroupAttitudeSkill();
            ManagerDto<List<GroupAttitudeSkillDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());
//            response.setTotalData(total);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched {} GroupAttitudeSkills in {} ms", result.size(), endTime - startTime);
//            log.info("Total GroupAttitudeSkills: {}", total);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all GroupAttitudeSkills: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch GroupAttitudeSkills", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sort/{order}/{page}/{pageSize}")
    public ResponseEntity<?> sortAllGroupAchievementsOrderBy(@PathVariable String order, @PathVariable int page, @PathVariable int pageSize) {
        log.info("Sorting all GroupAttitudeSkills");
        long startTime = System.currentTimeMillis();

        try {
            List<GroupAttitudeSkillDto> result = groupAttitudeSkillServ.sortAllGroupAttitudeSkill(order, page, pageSize);
//            Long total = groupAttitudeSkillServ.countAllGroupAttitudeSkill();
            ManagerDto<List<GroupAttitudeSkillDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());
//            response.setTotalData(total);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched {} GroupAttitudeSkills in {} ms", result.size(), endTime - startTime);
//            log.info("Total GroupAttitudeSkills: {}", total);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all GroupAttitudeSkills: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch GroupAttitudeSkills", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorch")
    public ResponseEntity<?> sorchAllGroupAchievementsOrderBy(@RequestParam(required = false) String keyword, @RequestParam String column, @RequestParam String order, @RequestParam int page, @RequestParam int pageSize) {
        log.info("Sorching all GroupAttitudeSkills");
        long startTime = System.currentTimeMillis();

        try {
            List<GroupAttitudeSkillDto> result = groupAttitudeSkillServ.sorchAllGroupAttitudeSkill(keyword, column, order, page, pageSize);
            Long total = groupAttitudeSkillServ.countAllGroupAttitudeSkill(keyword);
            ManagerDto<List<GroupAttitudeSkillDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());
            response.setTotalData(total);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched {} GroupAttitudeSkills in {} ms", result.size(), endTime - startTime);
            log.info("Total GroupAttitudeSkills: {}", total);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all GroupAttitudeSkills: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch GroupAttitudeSkills", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
