package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.attitudeskill.AttitudeSkillCreateDto;
import co.id.ogya.lokakarya.dto.attitudeskill.AttitudeSkillDto;
import co.id.ogya.lokakarya.dto.attitudeskill.AttitudeSkillGetDto;
import co.id.ogya.lokakarya.dto.attitudeskill.AttitudeSkillUpdateDto;

import java.util.List;

public interface AttitudeSkillServ {
    List<AttitudeSkillDto> getAllAttitudeSkill();

    List<AttitudeSkillGetDto> getAllAttitudeSkillGet();

    List<AttitudeSkillGetDto> getAllAttitudeSkillGetPerPage(int page, int pageSize);

    AttitudeSkillDto getAttitudeSkillById(String id);

    AttitudeSkillGetDto getAttitudeSkillGetById(String id);

    AttitudeSkillDto createAttitudeSkill(AttitudeSkillCreateDto attitudeSkillCreateDto);

    AttitudeSkillDto updateAttitudeSkill(AttitudeSkillUpdateDto attitudeSkillUpdateDto);

    boolean deleteAttitudeSkill(String id);

    AttitudeSkillDto getAttitudeSkillByName(String attitudeSkillName);

    Long countAllAttitudeSkill();

    List<AttitudeSkillGetDto> sortAllAttitudeSkillGetOrderBy(String column, String order, int page, int pageSize);

    List<AttitudeSkillGetDto> sorchAllAttitudeSkillGet(String keyword, String column, String order, int page, int pageSize);
}
