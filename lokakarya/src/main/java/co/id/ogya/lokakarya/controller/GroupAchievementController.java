package co.id.ogya.lokakarya.controller;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.groupachievement.*;
import co.id.ogya.lokakarya.services.GroupAchievementServ;
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
@RequestMapping("/groupachievement")
public class GroupAchievementController extends ServerResponseList {
    @Autowired
    GroupAchievementServ groupAchievementServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllGroupAchievements() {
        long startTime = System.currentTimeMillis();

        List<GroupAchievementDto> result = groupAchievementServ.getAllGroupAchievement();

        ManagerDto<List<GroupAchievementDto>> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(result.size());

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupAchievementById(@PathVariable String id) {
        long startTime = System.currentTimeMillis();

        GroupAchievementDto result = groupAchievementServ.getGroupAchievementById(id);

        ManagerDto<GroupAchievementDto> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGroupAchievement(@RequestBody GroupAchievementCreateDto groupAchievementCreateDto) {
        long startTime = System.currentTimeMillis();

        GroupAchievement result = groupAchievementServ.createGroupAchievement(groupAchievementCreateDto);

        ManagerDto<GroupAchievement> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateGroupAchievement(@RequestBody GroupAchievementUpdateDto groupAchievementUpdateDto) {
        long startTime = System.currentTimeMillis();

        GroupAchievement result = groupAchievementServ.updateGroupAchievement(groupAchievementUpdateDto);

        ManagerDto<GroupAchievement> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroupAchievement(@PathVariable String id) {
        long startTime = System.currentTimeMillis();

        boolean result = groupAchievementServ.deleteGroupAchievement(id);

        ManagerDto<String> response = new ManagerDto<>();
        response.setContent(id + " deleted: " + result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
