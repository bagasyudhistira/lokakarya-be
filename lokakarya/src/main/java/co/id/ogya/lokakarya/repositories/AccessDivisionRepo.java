package co.id.ogya.lokakarya.repositories;

import co.id.ogya.lokakarya.entities.AccessDivision;

import java.util.List;

public interface AccessDivisionRepo {
    List<AccessDivision> getAccessDivisions();
    AccessDivision getAccessDivisionById(String id);
    AccessDivision saveAccessDivision(AccessDivision accessDivision);
    AccessDivision updateAccessDivision(AccessDivision accessDivision);
    Boolean deleteAccessDivision(String id);
}
