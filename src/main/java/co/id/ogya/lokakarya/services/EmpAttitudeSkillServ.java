package co.id.ogya.lokakarya.services;

import co.id.ogya.lokakarya.dto.empattitudeskill.EmpAttitudeSkillCreateDto;
import co.id.ogya.lokakarya.dto.empattitudeskill.EmpAttitudeSkillDto;
import co.id.ogya.lokakarya.dto.empattitudeskill.EmpAttitudeSkillGetDto;
import co.id.ogya.lokakarya.dto.empattitudeskill.EmpAttitudeSkillUpdateDto;

import java.util.List;

public interface EmpAttitudeSkillServ {
    List<EmpAttitudeSkillDto> getAllEmpAttitudeSkill();

    EmpAttitudeSkillDto getEmpAttitudeSkillById(String id);

    List<EmpAttitudeSkillGetDto> getAllEmpAttitudeSkillGet();

    EmpAttitudeSkillGetDto getEmpAttitudeSkillGetById(String id);

    List<EmpAttitudeSkillGetDto> getAllEmpAttitudeSkillGetByUserId(String userId);

    EmpAttitudeSkillDto createEmpAttitudeSkill(EmpAttitudeSkillCreateDto empAttitudeSkillCreateDto);

    EmpAttitudeSkillDto updateEmpAttitudeSkill(EmpAttitudeSkillUpdateDto empAttitudeSkillUpdateDto);

    boolean deleteEmpAttitudeSkill(String id);

    Boolean ifAnyEmpAttitudeSkillExist(String userId, String attitudeSkillId, int assessmentYear);
}
