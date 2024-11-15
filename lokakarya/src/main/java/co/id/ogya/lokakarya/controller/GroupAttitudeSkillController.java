package co.id.ogya.lokakarya.controller;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.groupAttitudeSkill.GroupAttitudeSkillCreateDto;
import co.id.ogya.lokakarya.dto.groupAttitudeSkill.GroupAttitudeSkillDto;
import co.id.ogya.lokakarya.dto.groupAttitudeSkill.GroupAttitudeSkillUpdateDto;
import co.id.ogya.lokakarya.services.GroupAttitudeSkillServ;
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
@RequestMapping("/groupattitudeskill")
public class GroupAttitudeSkillController extends ServerResponseList {
    @Autowired
    GroupAttitudeSkillServ groupAttitudeSkillServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllGroupAttitudeSkills() {
        long startTime = System.currentTimeMillis();

        List<GroupAttitudeSkillDto> result = groupAttitudeSkillServ.getAllGroupAttitudeSkill();

        ManagerDto<List<GroupAttitudeSkillDto>> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(result.size());

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupAttitudeSkillById(@PathVariable String id) {
        long startTime = System.currentTimeMillis();

        GroupAttitudeSkillDto result = groupAttitudeSkillServ.getGroupAttitudeSkillById(id);

        ManagerDto<GroupAttitudeSkillDto> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGroupAttitudeSkill(@RequestBody GroupAttitudeSkillCreateDto groupAttitudeSkillCreateDto) {
        long startTime = System.currentTimeMillis();

        GroupAttitudeSkill result = groupAttitudeSkillServ.createGroupAttitudeSkill(groupAttitudeSkillCreateDto);

        ManagerDto<GroupAttitudeSkill> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateGroupAttitudeSkill(@RequestBody GroupAttitudeSkillUpdateDto groupAttitudeSkillUpdateDto) {
        long startTime = System.currentTimeMillis();

        GroupAttitudeSkill result = groupAttitudeSkillServ.updateGroupAttitudeSkill(groupAttitudeSkillUpdateDto);

        ManagerDto<GroupAttitudeSkill> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroupAttitudeSkill(@PathVariable String id) {
        long startTime = System.currentTimeMillis();

        boolean result = groupAttitudeSkillServ.deleteGroupAttitudeSkill(id);

        ManagerDto<String> response = new ManagerDto<>();
        response.setContent(id + " deleted: " + result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
