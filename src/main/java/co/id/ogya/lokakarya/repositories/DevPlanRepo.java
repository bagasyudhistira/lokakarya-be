package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.DevPlan;

import java.util.List;

public interface DevPlanRepo {
    List<DevPlan> getDevPlans();

    DevPlan getDevPlanById(String id);

    DevPlan saveDevPlan(DevPlan devPlan);

    DevPlan updateDevPlan(DevPlan devPlan);

    Boolean deleteDevPlan(String id);

    DevPlan getDevPlanByName(String planName);
}
