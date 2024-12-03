package co.id.ogya.lokakarya.dto.emptechnicalskill;

import co.id.ogya.lokakarya.dto.empachievementskill.EmpAchievementSkillGetUIDYearDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@Slf4j
public class EmpTechnicalSkillGetUIDYearDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("technical_skill_id")
    private String technicalSkillId;

    @JsonProperty("skill")
    private String skill;

    @JsonProperty("score")
    private double score;

    public static EmpTechnicalSkillGetUIDYearDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Converting EmpAttitudeSkill map to DTO: {}", convertObject);

        EmpTechnicalSkillGetUIDYearDto result = EmpTechnicalSkillGetUIDYearDto.builder()
                .id((String) convertObject.get("ID"))
                .technicalSkillId((String) convertObject.get("TECHNICAL_SKILL_ID"))
                .skill((String) convertObject.get("SKILL"))
                .score(convertObject.get("SCORE") != null
                        ? ((BigDecimal) convertObject.get("SCORE")).doubleValue()
                        : null)
                .build();
        return result;
    }
}
