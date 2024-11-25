package co.id.ogya.lokakarya.controllers;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.appuserrole.AppUserRoleCreateDto;
import co.id.ogya.lokakarya.dto.appuserrole.AppUserRoleDto;
import co.id.ogya.lokakarya.dto.appuserrole.AppUserRoleGetDto;
import co.id.ogya.lokakarya.dto.appuserrole.AppUserRoleUpdateDto;
import co.id.ogya.lokakarya.services.AppUserRoleServ;
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
@RequestMapping("/appuserrole")
public class AppUserRoleController extends ServerResponseList {
    @Autowired
    AppUserRoleServ appUserRoleServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllAppUserRoles() {
        log.info("Fetching all AppUserRoles");
        long startTime = System.currentTimeMillis();

        try {
            List<AppUserRoleDto> result = appUserRoleServ.getAllAppUserRole();
            ManagerDto<List<AppUserRoleDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all AppUserRoles in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all AppUserRoles: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppUserRoles", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppUserRoleById(@PathVariable String id) {
        log.info("Fetching AppUserRole with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            AppUserRoleDto result = appUserRoleServ.getAppUserRoleById(id);
            ManagerDto<AppUserRoleDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched AppUserRole with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching AppUserRole by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppUserRole with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAppUserRole(@RequestBody AppUserRoleCreateDto appUserRoleCreateDto) {
        log.info("Creating new AppUserRole with data: {}", appUserRoleCreateDto);
        long startTime = System.currentTimeMillis();

        try {
            AppUserRoleDto result = appUserRoleServ.createAppUserRole(appUserRoleCreateDto);
            ManagerDto<AppUserRoleDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Created new AppUserRole in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error creating AppUserRole: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create AppUserRole", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAppUserRole(@RequestBody AppUserRoleUpdateDto appUserRoleUpdateDto) {
        log.info("Updating AppUserRole with data: {}", appUserRoleUpdateDto);
        long startTime = System.currentTimeMillis();

        try {
            AppUserRoleDto result = appUserRoleServ.updateAppUserRole(appUserRoleUpdateDto);
            ManagerDto<AppUserRoleDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Updated AppUserRole in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating AppUserRole: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update AppUserRole", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppUserRole(@PathVariable String id) {
        log.info("Deleting AppUserRole with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            boolean result = appUserRoleServ.deleteAppUserRole(id);
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(id + " deleted: " + result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Deleted AppUserRole with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting AppUserRole with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete AppUserRole with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllAppUserRoleGets() {
        log.info("Fetching all AppUserRoles");
        long startTime = System.currentTimeMillis();

        try {
            List<AppUserRoleGetDto> result = appUserRoleServ.getAllAppUserRoleGets();
            ManagerDto<List<AppUserRoleGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all AppUserRoles in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all AppUserRoles: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppUserRoles", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getAllAppUserRoleGet(@PathVariable String userId) {
        log.info("Fetching all AppUserRoles");
        long startTime = System.currentTimeMillis();

        try {
            List<AppUserRoleGetDto> result = appUserRoleServ.getAppUserRoleGetById(userId);
            ManagerDto<List<AppUserRoleGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched AppUserRoles with User ID: {} in {} ms", userId, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching AppUserRoles: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppUserRoles", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get2/{userId}")
    public ResponseEntity<?> getAllAppUserRoleByUserId(@PathVariable String userId) {
        log.info("Fetching AppUserRoles for User ID: {}", userId);
        long startTime = System.currentTimeMillis();

        try {
            List<AppUserRoleDto> result = appUserRoleServ.getAppUserRoleDtoByUserId(userId);
            ManagerDto<List<AppUserRoleDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched AppUserRoles for User ID {} in {} ms", userId, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching AppUserRoles for User ID {} : {}", userId, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppUserRoles", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}/{roleId}")
    public ResponseEntity<?> getAllAppUserRoleByRoleId(@PathVariable String userId, @PathVariable String roleId) {
        log.info("Fetching AppUserRoles for User ID: {} and Role ID: {}", userId, roleId);
        long startTime = System.currentTimeMillis();

        try {
            String result = appUserRoleServ.getAppUserRoleByUserIdRoleId(userId, roleId);
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);
            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched AppUserRole for User ID: {} and Role ID: {} in {} ms", userId, roleId, endTime - startTime);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error fetching AppUserRole for User ID: {} and Role ID: {} : {}", userId, roleId, e.getMessage(), e);
            throw e;
        }
    }
}
