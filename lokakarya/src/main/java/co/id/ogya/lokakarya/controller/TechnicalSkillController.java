package co.id.ogya.lokakarya.controller;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.technicalskill.*;
import co.id.ogya.lokakarya.services.TechnicalSkillServ;
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
@RequestMapping("/technicalskill")
public class TechnicalSkillController extends ServerResponseList {
    @Autowired
    TechnicalSkillServ technicalSkillServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllTechnicalSkills() {
        long startTime = System.currentTimeMillis();

        List<TechnicalSkillDto> result = technicalSkillServ.getAllTechnicalSkill();

        ManagerDto<List<TechnicalSkillDto>> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(result.size());

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTechnicalSkillById(@PathVariable String id) {
        long startTime = System.currentTimeMillis();

        TechnicalSkillDto result = technicalSkillServ.getTechnicalSkillById(id);

        ManagerDto<TechnicalSkillDto> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTechnicalSkill(@RequestBody TechnicalSkillCreateDto technicalSkillCreateDto) {
        long startTime = System.currentTimeMillis();

        TechnicalSkill result = technicalSkillServ.createTechnicalSkill(technicalSkillCreateDto);

        ManagerDto<TechnicalSkill> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTechnicalSkill(@RequestBody TechnicalSkillUpdateDto technicalSkillUpdateDto) {
        long startTime = System.currentTimeMillis();

        TechnicalSkill result = technicalSkillServ.updateTechnicalSkill(technicalSkillUpdateDto);

        ManagerDto<TechnicalSkill> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTechnicalSkill(@PathVariable String id) {
        long startTime = System.currentTimeMillis();

        boolean result = technicalSkillServ.deleteTechnicalSkill(id);

        ManagerDto<String> response = new ManagerDto<>();
        response.setContent(id + " deleted: " + result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
