package co.id.ogya.lokakarya.controller;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.empsuggestion.*;
import co.id.ogya.lokakarya.services.EmpSuggestionServ;
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
@RequestMapping("/empsuggestion")
public class EmpSuggestionController extends ServerResponseList {
    @Autowired
    EmpSuggestionServ empSuggestionServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllEmpSuggestions() {
        long startTime = System.currentTimeMillis();

        List<EmpSuggestionDto> result = empSuggestionServ.getAllEmpSuggestion();

        ManagerDto<List<EmpSuggestionDto>> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(result.size());

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmpSuggestionById(@PathVariable String id) {
        long startTime = System.currentTimeMillis();

        EmpSuggestionDto result = empSuggestionServ.getEmpSuggestionById(id);

        ManagerDto<EmpSuggestionDto> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmpSuggestion(@RequestBody EmpSuggestionCreateDto empSuggestionCreateDto) {
        long startTime = System.currentTimeMillis();

        EmpSuggestion result = empSuggestionServ.createEmpSuggestion(empSuggestionCreateDto);

        ManagerDto<EmpSuggestion> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmpSuggestion(@RequestBody EmpSuggestionUpdateDto empSuggestionUpdateDto) {
        long startTime = System.currentTimeMillis();

        EmpSuggestion result = empSuggestionServ.updateEmpSuggestion(empSuggestionUpdateDto);

        ManagerDto<EmpSuggestion> response = new ManagerDto<>();
        response.setContent(result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpSuggestion(@PathVariable String id) {
        long startTime = System.currentTimeMillis();

        boolean result = empSuggestionServ.deleteEmpSuggestion(id);

        ManagerDto<String> response = new ManagerDto<>();
        response.setContent(id + " deleted: " + result);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        response.setInfo(getInfoOk("Time", totalTime));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
