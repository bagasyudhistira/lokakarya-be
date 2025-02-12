package co.id.ogya.lokakarya.controllers;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.appuser.*;
import co.id.ogya.lokakarya.services.AppUserServ;
import co.id.ogya.lokakarya.utils.ServerResponseList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    PasswordEncoder passwordEncoder;

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

    @GetMapping("/div/{divisionId}")
    public ResponseEntity<?> getAllAppUsers(@PathVariable String divisionId) {
        log.info("Fetching all AppUsers for Division ID: {}", divisionId);
        long startTime = System.currentTimeMillis();

        try {
            List<AppUserDto> result = appUserServ.getAppUserByDivisionId(divisionId);
            ManagerDto<List<AppUserDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all AppUsers for Division ID: {} in {} ms", divisionId, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all AppUsers for Division ID: {} : {}", divisionId, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppUsers", HttpStatus.INTERNAL_SERVER_ERROR);
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

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getAppUserByUsername(@PathVariable String username) {
        log.info("Fetching AppUser with username: {}", username);
        long startTime = System.currentTimeMillis();

        try {
            AppUserGetDto result = appUserServ.getAppUserByUsername(username);
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

    @GetMapping("/name/{fullName}")
    public ResponseEntity<?> getAppUserByFullName(@PathVariable String fullName) {
        log.info("Fetching AppUser with full name: {}", fullName);
        long startTime = System.currentTimeMillis();

        try {
            AppUserGetDto result = appUserServ.getAppUserByUsername(fullName);
            ManagerDto<AppUserGetDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched AppUser with full name: {} in {} ms", fullName, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching AppUser by full name {}: {}", fullName, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppUser with full name: " + fullName, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getAppUserByEmail(@PathVariable String email) {
        log.info("Fetching AppUser with Email: {}", email);
        long startTime = System.currentTimeMillis();

        try {
            AppUserGetDto result = appUserServ.getAppUserByEmail(email);
            ManagerDto<AppUserGetDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched AppUser with full Email: {} in {} ms", email, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching AppUser by full name {}: {}", email, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppUser with Email: " + email, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAppUser(@RequestBody AppUserCreateDto appUserCreateDto) {
        log.info("Creating new AppUser with data: {}", appUserCreateDto);
        long startTime = System.currentTimeMillis();

        try {
//            appUserCreateDto.setPassword(passwordEncoder.encode(appUserCreateDto.getPassword()));

            AppUserCreateDto result = appUserServ.createAppUser(appUserCreateDto);
            ManagerDto<AppUserCreateDto> response = new ManagerDto<>();
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
//            appUserUpdateDto.setPassword(passwordEncoder.encode(appUserUpdateDto.getPassword()));
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

    @GetMapping("/get/common/all")
    public ResponseEntity<?> getAllAppUserCommons() {
        log.info("Fetching all AppUserCommons");
        long startTime = System.currentTimeMillis();

        try {
            List<AppUserCommonDto> result = appUserServ.getAllAppUserCommons();
            ManagerDto<List<AppUserCommonDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all AppUserCommons in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all AppUserCommons: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppUserCommons", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/all/{page}/{pageSize}")
    public ResponseEntity<?> getAllAppUserGetsPerPage(@PathVariable int page, @PathVariable int pageSize) {
        log.info("Fetching all AppUsers");
        long startTime = System.currentTimeMillis();

        try {
            List<AppUserGetDto> result = appUserServ.getAllAppUserGetPerPage(page, pageSize);
            Long total = appUserServ.countAllAppUser("hehe");
            ManagerDto<List<AppUserGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());
            response.setTotalData(total);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched {} AppUsers in {} ms", result.size(), endTime - startTime);
            log.info("Total Users: {}", total);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all AppUsers: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppUsers", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sort/{column}/{order}/{page}/{pageSize}")
    public ResponseEntity<?> sortAllAppUserGetsOrderBy(@PathVariable String column, @PathVariable String order, @PathVariable int page, @PathVariable int pageSize) {
        log.info("Sorting all AppUsers");
        long startTime = System.currentTimeMillis();

        try {
            List<AppUserGetDto> result = appUserServ.sortAllAppUserGetOrderBy(column, order, page, pageSize);
            Long total = appUserServ.countAllAppUser("hehe");
            ManagerDto<List<AppUserGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());
            response.setTotalData(total);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched {} AppUsers in {} ms", result.size(), endTime - startTime);
            log.info("Total Users: {}", total);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all AppUsers: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppUsers", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorch")
    public ResponseEntity<?> sorchAllAppUserGets(@RequestParam(required = false) String keyword, @RequestParam String column, @RequestParam String order, @RequestParam int page, @RequestParam int pageSize) {
        log.info("Sorting all AppUsers");
        long startTime = System.currentTimeMillis();

        try {
            List<AppUserGetDto> result = appUserServ.sorchAllAppUserGet(keyword, column, order, page, pageSize);
            Long total = appUserServ.countAllAppUser(keyword);
            ManagerDto<List<AppUserGetDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());
            response.setTotalData(total);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched {} AppUsers in {} ms", result.size(), endTime - startTime);
            log.info("Total Users: {}", total);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all AppUsers: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppUsers", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
