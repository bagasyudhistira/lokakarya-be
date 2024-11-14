package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.empdevplan.EmpDevPlanCreateDto;
import co.id.ogya.lokakarya.dto.empdevplan.EmpDevPlanDto;
import co.id.ogya.lokakarya.dto.empdevplan.EmpDevPlanUpdateDto;

import java.util.List;

public interface EmpDevPlanServ {
    List<EmpDevPlanDto> getAllEmpDevPlan();
    EmpDevPlanDto getEmpDevPlanById(String id);
    EmpDevPlanDto createEmpDevPlan(EmpDevPlanCreateDto empDevPlanCreateDto);
    EmpDevPlanDto updateEmpDevPlan(EmpDevPlanUpdateDto empDevPlanUpdateDto);
    boolean deleteEmpDevPlan(String id);

}
