package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.EmpDevPlan;

import java.util.List;

public interface EmpDevPlanRepo {
    List<EmpDevPlan> getEmpDevPlans();
    EmpDevPlan getEmpDevPlanById(String id);
    EmpDevPlan saveEmpDevPlan(EmpDevPlan empDevPlan);
    EmpDevPlan updateEmpDevPlan(EmpDevPlan empDevPlan);
    Boolean deleteEmpDevPlan(String id);
}
