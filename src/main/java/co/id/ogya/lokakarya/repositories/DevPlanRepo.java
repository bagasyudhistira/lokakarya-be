package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.DevPlan;

import java.util.List;

public interface DevPlanRepo {
    List<DevPlan> getDevPlans();

    List<DevPlan> getDevPlansPerPage(int page, int pageSize);

    DevPlan getDevPlanById(String id);

    DevPlan saveDevPlan(DevPlan devPlan);

    DevPlan updateDevPlan(DevPlan devPlan);

    Boolean deleteDevPlan(String id);

    DevPlan getDevPlanByName(String planName);

    Long countDevPlans();

    List<DevPlan> sortDevPlans(String order, int page, int pageSize);

    List<DevPlan> searchDevPlans(String keyword, int page, int pageSize);
}
