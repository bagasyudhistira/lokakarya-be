package co.id.ogya.lokakarya.controller;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.emptechnicalskill.*;
import co.id.ogya.lokakarya.services.EmpTechnicalSkillServ;
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
@RequestMapping("/emptechnicalskill")
public class EmpTechnicalSkillController extends ServerResponseList {
    @Autowired
    EmpTechnicalSkillServ empTechnicalSkillServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllEmpTechnicalSkills() {
        long startTime = System.currentTimeMillis();

        List<EmpTechnicalSkillDto> result = empTechnicalSkillServ.getAllEmpTechnicalSkill();

        ManagerDto<List<EmpTechnicalSkillDto>> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(result.size());

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmpTechnicalSkillById(@PathVariable String id) {
        long startTime = System.currentTimeMillis();

        EmpTechnicalSkillDto result = empTechnicalSkillServ.getEmpTechnicalSkillById(id);

        ManagerDto<EmpTechnicalSkillDto> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmpTechnicalSkill(@RequestBody EmpTechnicalSkillCreateDto empTechnicalSkillCreateDto) {
        long startTime = System.currentTimeMillis();

        EmpTechnicalSkill result = empTechnicalSkillServ.createEmpTechnicalSkill(empTechnicalSkillCreateDto);

        ManagerDto<EmpTechnicalSkill> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmpTechnicalSkill(@RequestBody EmpTechnicalSkillUpdateDto empTechnicalSkillUpdateDto) {
        long startTime = System.currentTimeMillis();

        EmpTechnicalSkill result = empTechnicalSkillServ.updateEmpTechnicalSkill(empTechnicalSkillUpdateDto);

        ManagerDto<EmpTechnicalSkill> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpTechnicalSkill(@PathVariable String id) {
        long startTime = System.currentTimeMillis();

        boolean result = empTechnicalSkillServ.deleteEmpTechnicalSkill(id);

        ManagerDto<String> response = new ManagerDto<>();
        response.setContent(id + " deleted: " + result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
