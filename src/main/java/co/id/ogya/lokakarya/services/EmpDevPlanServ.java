package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.empdevplan.EmpDevPlanCreateDto;
import co.id.ogya.lokakarya.dto.empdevplan.EmpDevPlanDto;
import co.id.ogya.lokakarya.dto.empdevplan.EmpDevPlanGetDto;
import co.id.ogya.lokakarya.dto.empdevplan.EmpDevPlanUpdateDto;

import java.util.List;

public interface EmpDevPlanServ {
    List<EmpDevPlanDto> getAllEmpDevPlan();

    EmpDevPlanDto getEmpDevPlanById(String id);

    EmpDevPlanDto createEmpDevPlan(EmpDevPlanCreateDto empDevPlanCreateDto);

    EmpDevPlanDto updateEmpDevPlan(EmpDevPlanUpdateDto empDevPlanUpdateDto);

    boolean deleteEmpDevPlan(String id);

    List<EmpDevPlanGetDto> getAllEmpDevPlanGets();

    List<EmpDevPlanGetDto> getEmpDevPlanGetByUserId(String userId);

    List<EmpDevPlanGetDto> getEmpDevPlanGetByUserIdAssessmentYear(String userId, String assessmentYear);

    Boolean ifAnyEmpDevPlanExist(String userId, String devPlanId, int assessmentYear);
}
