package co.id.ogya.lokakarya.controller;

import co.id.ogya.lokakarya.dto.ManagerDto;
import co.id.ogya.lokakarya.dto.appmenu.AppMenuCreateDto;
import co.id.ogya.lokakarya.dto.appmenu.AppMenuDto;
import co.id.ogya.lokakarya.dto.appmenu.AppMenuUpdateDto;
import co.id.ogya.lokakarya.services.AppMenuServ;
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
@RequestMapping("/appmenu")
public class AppMenuController extends ServerResponseList {
    @Autowired
    AppMenuServ appMenuServ;

    @GetMapping("/all")
    public ResponseEntity<?> getAllAppMenus() {
        log.info("Fetching all AppMenus");
        long startTime = System.currentTimeMillis();

        try {
            List<AppMenuDto> result = appMenuServ.getAllAppMenu();
            ManagerDto<List<AppMenuDto>> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(result.size());

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched all AppMenus in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all AppMenus: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppMenus", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppMenuById(@PathVariable String id) {
        log.info("Fetching AppMenu with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            AppMenuDto result = appMenuServ.getAppMenuById(id);
            ManagerDto<AppMenuDto> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Fetched AppMenu with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching AppMenu by ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to fetch AppMenu with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAppMenu(@RequestBody AppMenuCreateDto appMenuCreateDto) {
        log.info("Creating new AppMenu with data: {}", appMenuCreateDto);
        long startTime = System.currentTimeMillis();

        try {
            AppMenu result = appMenuServ.createAppMenu(appMenuCreateDto);
            ManagerDto<AppMenu> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Created new AppMenu in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error creating AppMenu: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create AppMenu", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAppMenu(@RequestBody AppMenuUpdateDto appMenuUpdateDto) {
        log.info("Updating AppMenu with data: {}", appMenuUpdateDto);
        long startTime = System.currentTimeMillis();

        try {
            AppMenu result = appMenuServ.updateAppMenu(appMenuUpdateDto);
            ManagerDto<AppMenu> response = new ManagerDto<>();
            response.setContent(result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Updated AppMenu in {} ms", endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating AppMenu: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update AppMenu", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppMenu(@PathVariable String id) {
        log.info("Deleting AppMenu with ID: {}", id);
        long startTime = System.currentTimeMillis();

        try {
            boolean result = appMenuServ.deleteAppMenu(id);
            ManagerDto<String> response = new ManagerDto<>();
            response.setContent(id + " deleted: " + result);
            response.setTotalRows(1);

            long endTime = System.currentTimeMillis();
            response.setInfo(getInfoOk("Time", endTime - startTime));
            log.info("Deleted AppMenu with ID: {} in {} ms", id, endTime - startTime);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting AppMenu with ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete AppMenu with ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
