package co.id.ogya.lokakarya.controllers;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.approlemenu.AppRoleMenuCreateDto;
import co.id.ogya.lokakarya.dto.approlemenu.AppRoleMenuDto;
import co.id.ogya.lokakarya.dto.approlemenu.AppRoleMenuUpdateDto;
import co.id.ogya.lokakarya.services.AppRoleMenuServ;
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
@RequestMapping("/approlemenu")
public class AppRoleMenuController extends ServerResponseList {
    @Autowired
    AppRoleMenuServ appRoleMenuServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllAppRoleMenus() {
        log.info("Fetching all AppRoleMenus");
        long startTime = System.currentTimeMillis();

        try {
            List<AppRoleMenuDto> result = appRoleMenuServ.getAllAppRoleMenu();
            ManagerDto<List<AppRoleMenuDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all AppRoleMenus in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all AppRoleMenus: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppRoleMenus", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppRoleMenuById(@PathVariable String id) {
        log.info("Fetching AppRoleMenu with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            AppRoleMenuDto result = appRoleMenuServ.getAppRoleMenuById(id);
            ManagerDto<AppRoleMenuDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched AppRoleMenu with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching AppRoleMenu by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppRoleMenu with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAppRoleMenu(@RequestBody AppRoleMenuCreateDto appRoleMenuCreateDto) {
        log.info("Creating new AppRoleMenu with data: {}", appRoleMenuCreateDto);
        long startTime = System.currentTimeMillis();

        try {
            AppRoleMenuDto result = appRoleMenuServ.createAppRoleMenu(appRoleMenuCreateDto);
            ManagerDto<AppRoleMenuDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Created new AppRoleMenu in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error creating AppRoleMenu: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create AppRoleMenu", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAppRoleMenu(@RequestBody AppRoleMenuUpdateDto appRoleMenuUpdateDto) {
        log.info("Updating AppRoleMenu with data: {}", appRoleMenuUpdateDto);
        long startTime = System.currentTimeMillis();

        try {
            AppRoleMenuDto result = appRoleMenuServ.updateAppRoleMenu(appRoleMenuUpdateDto);
            ManagerDto<AppRoleMenuDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Updated AppRoleMenu in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating AppRoleMenu: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update AppRoleMenu", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppRoleMenu(@PathVariable String id) {
        log.info("Deleting AppRoleMenu with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            boolean result = appRoleMenuServ.deleteAppRoleMenu(id);
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(id + " deleted: " + result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Deleted AppRoleMenu with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting AppRoleMenu with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete AppRoleMenu with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
