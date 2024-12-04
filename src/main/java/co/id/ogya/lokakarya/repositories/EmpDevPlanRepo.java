package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.EmpDevPlan;

import java.util.List;
import java.util.Map;

public interface EmpDevPlanRepo {
    List<EmpDevPlan> getEmpDevPlans();

    EmpDevPlan getEmpDevPlanById(String id);

    EmpDevPlan saveEmpDevPlan(EmpDevPlan empDevPlan);

    EmpDevPlan updateEmpDevPlan(EmpDevPlan empDevPlan);

    Boolean deleteEmpDevPlan(String id);

    List<Map<String, Object>> getEmpDevPlanGets();

    List<Map<String, Object>> getEmpDevPlanGetByUserId(String userId);

    List<Map<String, Object>> getEmpDevPlanGetByUserIdAssessmentYear(String userId, String assessmentYear);

    Boolean ifAnyEmpDevPlanExist(String userId, String devPlanId, int assessmentYear);
}
