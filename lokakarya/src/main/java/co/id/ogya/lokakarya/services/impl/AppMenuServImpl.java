package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.appmenu.AppMenuCreateDto;
import co.id.ogya.lokakarya.dto.appmenu.AppMenuDto;
import co.id.ogya.lokakarya.dto.appmenu.AppMenuUpdateDto;
import co.id.ogya.lokakarya.services.AppMenuServ;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class AppMenuServImpl implements AppMenuServ {
    @Autowired
    private AppMenuRepo appMenuRepo;

    @Override
    public List<AppMenuDto> getAllAppMenu() {
        List<AppMenu> listData = appMenuRepo.getAppMenus();
        List<AppMenuDto> listResult = new ArrayList<>();
        for(AppMenu data : listData){
            AppMenuDto result = convertToDto(data);
            listResult.add(result);
        }
        return listResult;
    }

    @Override
    public AppMenuDto getAppMenuById(String id) {
        AppMenu data = appMenuRepo.getAppMenuById(id);
        AppMenuDto result = convertToDto(data);
        return result;
    }

    @Override
    public AppMenuDto createAppMenu(AppMenuCreateDto appMenuCreateDto) {
        AppMenu data = convertToEntityCreate(appMenuCreateDto);
        AppMenu result = appMenuRepo.saveAppMenu(data);
        return convertToDto(result);
    }

    @Override
    public AppMenuDto updateAppMenu(AppMenuUpdateDto appMenuUpdateDto) {
        AppMenu data = convertToEntityUpdate(appMenuUpdateDto);
        AppMenu result = appMenuRepo.updateAppMenu(data);
        return convertToDto(result);
    }

    @Override
    public boolean deleteAppMenu(String id) {
        return appMenuRepo.deleteAppMenu(id);
    }

    private AppMenu convertToEntity(AppMenuDto convertObject) {
        AppMenu result = AppMenu.builder()
                .id(convertObject.getId())
                .menuName(convertObject.getMenuName())
                .createdAt(convertObject.getCreatedAt())
                .createdBy(convertObject.getCreatedBy())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private AppMenu convertToEntityCreate(AppMenuCreateDto convertObject) {
        AppMenu result = AppMenu.builder()
                .id(convertObject.getId())
                .menuName(convertObject.getMenuName())
                .createdBy(convertObject.getCreatedBy())
                .build();
        return result;
    }

    private AppMenu convertToEntityUpdate(AppMenuUpdateDto convertObject) {
        AppMenu result = AppMenu.builder()
                .id(convertObject.getId())
                .menuName(convertObject.getMenuName())
                .updatedAt(convertObject.getUpdatedAt())
                .updatedBy(convertObject.getUpdatedBy())
                .build();
        return result;
    }

    private AppMenuDto convertToDto(AppMenu convertObject) {
        AppMenuDto result = AppMenuDto.builder()
                .id(convertObject.getId())
                .menuName(convertObject.getMenuName())
                .build();
        return result;
    }
}
