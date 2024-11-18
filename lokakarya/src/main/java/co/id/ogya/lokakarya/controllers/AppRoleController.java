package co.id.ogya.lokakarya.controllers;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.approle.AppRoleCreateDto;
import co.id.ogya.lokakarya.dto.approle.AppRoleDto;
import co.id.ogya.lokakarya.dto.approle.AppRoleUpdateDto;
import co.id.ogya.lokakarya.services.AppRoleServ;
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
@RequestMapping("/approle")
public class AppRoleController extends ServerResponseList {
    @Autowired
    AppRoleServ appRoleServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllAppRoles() {
        log.info("Fetching all AppRoles");
        long startTime = System.currentTimeMillis();

        try {
            List<AppRoleDto> result = appRoleServ.getAllAppRole();
            ManagerDto<List<AppRoleDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all AppRoles in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all AppRoles: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppRoles", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppRoleById(@PathVariable String id) {
        log.info("Fetching AppRole with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            AppRoleDto result = appRoleServ.getAppRoleById(id);
            ManagerDto<AppRoleDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched AppRole with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching AppRole by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppRole with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAppRole(@RequestBody AppRoleCreateDto appRoleCreateDto) {
        log.info("Creating new AppRole with data: {}", appRoleCreateDto);
        long startTime = System.currentTimeMillis();

        try {
            AppRoleDto result = appRoleServ.createAppRole(appRoleCreateDto);
            ManagerDto<AppRoleDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Created new AppRole in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error creating AppRole: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create AppRole", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAppRole(@RequestBody AppRoleUpdateDto appRoleUpdateDto) {
        log.info("Updating AppRole with data: {}", appRoleUpdateDto);
        long startTime = System.currentTimeMillis();

        try {
            AppRoleDto result = appRoleServ.updateAppRole(appRoleUpdateDto);
            ManagerDto<AppRoleDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Updated AppRole in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating AppRole: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update AppRole", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppRole(@PathVariable String id) {
        log.info("Deleting AppRole with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            boolean result = appRoleServ.deleteAppRole(id);
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(id + " deleted: " + result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Deleted AppRole with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting AppRole with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete AppRole with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
