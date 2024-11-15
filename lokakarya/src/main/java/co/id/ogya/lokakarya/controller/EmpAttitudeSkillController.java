package co.id.ogya.lokakarya.controller;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.empattitudeskill.*;
import co.id.ogya.lokakarya.services.EmpAttitudeSkillServ;
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
@RequestMapping("/empattitudeskill")
public class EmpAttitudeSkillController extends ServerResponseList {
    @Autowired
    EmpAttitudeSkillServ empAttitudeSkillServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllEmpAttitudeSkills() {
        long startTime = System.currentTimeMillis();

        List<EmpAttitudeSkillDto> result = empAttitudeSkillServ.getAllEmpAttitudeSkill();

        ManagerDto<List<EmpAttitudeSkillDto>> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(result.size());

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmpAttitudeSkillById(@PathVariable String id) {
        long startTime = System.currentTimeMillis();

        EmpAttitudeSkillDto result = empAttitudeSkillServ.getEmpAttitudeSkillById(id);

        ManagerDto<EmpAttitudeSkillDto> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmpAttitudeSkill(@RequestBody EmpAttitudeSkillCreateDto empAttitudeSkillCreateDto) {
        long startTime = System.currentTimeMillis();

        EmpAttitudeSkill result = empAttitudeSkillServ.createEmpAttitudeSkill(empAttitudeSkillCreateDto);

        ManagerDto<EmpAttitudeSkill> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmpAttitudeSkill(@RequestBody EmpAttitudeSkillUpdateDto empAttitudeSkillUpdateDto) {
        long startTime = System.currentTimeMillis();

        EmpAttitudeSkill result = empAttitudeSkillServ.updateEmpAttitudeSkill(empAttitudeSkillUpdateDto);

        ManagerDto<EmpAttitudeSkill> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpAttitudeSkill(@PathVariable String id) {
        long startTime = System.currentTimeMillis();

        boolean result = empAttitudeSkillServ.deleteEmpAttitudeSkill(id);

        ManagerDto<String> response = new ManagerDto<>();
        response.setContent(id + " deleted: " + result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
