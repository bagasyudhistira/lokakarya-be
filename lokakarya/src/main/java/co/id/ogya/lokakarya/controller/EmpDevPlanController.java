package co.id.ogya.lokakarya.controller;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.empdevplan.*;
import co.id.ogya.lokakarya.services.EmpDevPlanServ;
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
@RequestMapping("/empdevplan")
public class EmpDevPlanController extends ServerResponseList {
    @Autowired
    EmpDevPlanServ empDevPlanServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllEmpDevPlans() {
        long startTime = System.currentTimeMillis();

        List<EmpDevPlanDto> result = empDevPlanServ.getAllEmpDevPlan();

        ManagerDto<List<EmpDevPlanDto>> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(result.size());

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmpDevPlanById(@PathVariable String id) {
        long startTime = System.currentTimeMillis();

        EmpDevPlanDto result = empDevPlanServ.getEmpDevPlanById(id);

        ManagerDto<EmpDevPlanDto> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmpDevPlan(@RequestBody EmpDevPlanCreateDto empDevPlanCreateDto) {
        long startTime = System.currentTimeMillis();

        EmpDevPlan result = empDevPlanServ.createEmpDevPlan(empDevPlanCreateDto);

        ManagerDto<EmpDevPlan> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmpDevPlan(@RequestBody EmpDevPlanUpdateDto empDevPlanUpdateDto) {
        long startTime = System.currentTimeMillis();

        EmpDevPlan result = empDevPlanServ.updateEmpDevPlan(empDevPlanUpdateDto);

        ManagerDto<EmpDevPlan> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpDevPlan(@PathVariable String id) {
        long startTime = System.currentTimeMillis();

        boolean result = empDevPlanServ.deleteEmpDevPlan(id);

        ManagerDto<String> response = new ManagerDto<>();
        response.setContent(id + " deleted: " + result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
