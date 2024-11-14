package co.id.ogya.lokakarya.services.impl;

import co.id.ogya.lokakarya.dto.approlemenu.AppRoleMenuCreateDto;
import co.id.ogya.lokakarya.dto.approlemenu.AppRoleMenuDto;
import co.id.ogya.lokakarya.dto.approlemenu.AppRoleMenuUpdateDto;
import co.id.ogya.lokakarya.services.AppRoleMenuServ;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class AppRoleMenuServImpl implements AppRoleMenuServ {
    @Autowired
    private AppRoleMenuRepo appRoleMenuRepo;

    @Override
    public List<AppRoleMenuDto> getAllAppRoleMenu() {
        List<AppRoleMenu> listData = appRoleMenuRepo.getAppRoleMenus();
        List<AppRoleMenuDto> listResult = new ArrayList<>();
        for(AppRoleMenu data : listData){
            AppRoleMenuDto result = convertToDto(data);
            listResult.add(result);
        }
        return listResult;
    }

    @Override
    public AppRoleMenuDto getAppRoleMenuById(String id) {
        AppRoleMenu data = appRoleMenuRepo.getAppRoleMenuById(id);
        AppRoleMenuDto result = convertToDto(data);
        return result;
    }

    @Override
    public AppRoleMenuDto createAppRoleMenu(AppRoleMenuCreateDto appRoleMenuCreateDto) {
        AppRoleMenu data = convertToEntityCreate(appRoleMenuCreateDto);
        AppRoleMenu result = appRoleMenuRepo.saveAppRoleMenu(data);
        return convertToDto(result);
    }

    @Override
    public AppRoleMenuDto updateAppRoleMenu(AppRoleMenuUpdateDto appRoleMenuUpdateDto) {
        AppRoleMenu data = convertToEntityUpdate(appRoleMenuUpdateDto);
        AppRoleMenu result = appRoleMenuRepo.updateAppRoleMenu(data);
        return convertToDto(result);
    }

    @Override
    public boolean deleteAppRoleMenu(String id) {
        return appRoleMenuRepo.deleteAppRoleMenu(id);
    }

    private AppRoleMenu convertToEntity(AppRoleMenuDto convertObject) {
        AppRoleMenu result = AppRoleMenu.builder()
                .id(convertObject.getId())
                .appRoleMenu(convertObject.getRoleId())
                .groupId(convertObject.getMenuId())
                .build();
        return result;
    }

    private AppRoleMenu convertToEntityCreate(AppRoleMenuCreateDto convertObject) {
        AppRoleMenu result = AppRoleMenu.builder()
                .id(convertObject.getId())
                .appRoleMenu(convertObject.getRoleId())
                .groupId(convertObject.getMenuId())
                .build();
        return result;
    }

    private AppRoleMenu convertToEntityUpdate(AppRoleMenuUpdateDto convertObject) {
        AppRoleMenu result = AppRoleMenu.builder()
                .id(convertObject.getId())
                .appRoleMenu(convertObject.getRoleId())
                .groupId(convertObject.getMenuId())
                .build();
        return result;
    }

    private AppRoleMenuDto convertToDto(AppRoleMenu convertObject) {
        AppRoleMenuDto result = AppRoleMenuDto.builder()
                .id(convertObject.getId())
                .appRoleMenu(convertObject.getRoleId())
                .groupId(convertObject.getMenuId())
                .build();
        return result;
    }
}
