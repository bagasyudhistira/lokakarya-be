package co.id.ogya.lokakarya.controllers;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.appuser.AppUserCreateDto;
import co.id.ogya.lokakarya.dto.appuser.AppUserDto;
import co.id.ogya.lokakarya.dto.appuser.AppUserGetDto;
import co.id.ogya.lokakarya.dto.appuser.AppUserUpdateDto;
import co.id.ogya.lokakarya.services.AppUserServ;
import co.id.ogya.lokakarya.utils.ServerResponseList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/appuser")
public class AppUserController extends ServerResponseList {
    @Autowired
    AppUserServ appUserServ;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    public ResponseEntity<?> getAllAppUsers() {
        log.info("Fetching all AppUsers");
        long startTime = System.currentTimeMillis();

        try {
            List<AppUserDto> result = appUserServ.getAllAppUser();
            ManagerDto<List<AppUserDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all AppUsers in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all AppUsers: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppUsers", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppUserById(@PathVariable String id) {
        log.info("Fetching AppUser with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            AppUserDto result = appUserServ.getAppUserById(id);
            ManagerDto<AppUserDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched AppUser with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching AppUser by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppUser with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllAppUserGets() {
        log.info("Fetching all AppUsers");
        long startTime = System.currentTimeMillis();

        try {
            List<AppUserGetDto> result = appUserServ.getAllAppUserGet();
            ManagerDto<List<AppUserGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all AppUsers in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all AppUsers: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppUsers", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAppUserGetById(@PathVariable String id) {
        log.info("Fetching AppUser with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            AppUserGetDto result = appUserServ.getAppUserGetById(id);
            ManagerDto<AppUserGetDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched AppUser with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching AppUser by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppUser with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> getAppUserByUsername(@PathVariable String username) {
        log.info("Fetching AppUser with username: {}", username);
        long startTime = System.currentTimeMillis();

        try {
            AppUserGetDto result = appUserServ.getAppUserGetById(username);
            ManagerDto<AppUserGetDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched AppUser with username: {} in {} ms", username, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching AppUser by username {}: {}", username, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppUser with username: " + username, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAppUser(@RequestBody AppUserCreateDto appUserCreateDto) {
        log.info("Creating new AppUser with data: {}", appUserCreateDto);
        long startTime = System.currentTimeMillis();

        try {
//            appUserCreateDto.setPassword(passwordEncoder.encode(appUserCreateDto.getPassword()));

            AppUserDto result = appUserServ.createAppUser(appUserCreateDto);
            ManagerDto<AppUserDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Created new AppUser in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error creating AppUser: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create AppUser", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAppUser(@RequestBody AppUserUpdateDto appUserUpdateDto) {
        log.info("Updating AppUser with data: {}", appUserUpdateDto);
        long startTime = System.currentTimeMillis();

        try {
            AppUserDto result = appUserServ.updateAppUser(appUserUpdateDto);
            ManagerDto<AppUserDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Updated AppUser in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating AppUser: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update AppUser", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppUser(@PathVariable String id) {
        log.info("Deleting AppUser with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            boolean result = appUserServ.deleteAppUser(id);
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(id + " deleted: " + result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Deleted AppUser with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting AppUser with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete AppUser with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
