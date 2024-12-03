package co.id.ogya.lokakarya.dto.empattitudeskill;

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
public class EmpAttitudeSkillGetUIDYearDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("attitude_skill_id")
    private String attitudeSkillId;

    @JsonProperty("score")
    private double score;

    @JsonProperty("group_id")
    private String groupId;

    public static EmpAttitudeSkillGetUIDYearDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Converting EmpAttitudeSkill map to DTO: {}", convertObject);

        EmpAttitudeSkillGetUIDYearDto result = EmpAttitudeSkillGetUIDYearDto.builder()
                .id((String) convertObject.get("ID"))
                .attitudeSkillId((String) convertObject.get("ATTITUDE_SKILL_ID"))
                .score(convertObject.get("SCORE") != null
                        ? ((BigDecimal) convertObject.get("SCORE")).doubleValue()
                        : null)
                .groupId((String) convertObject.get("GROUP_ID"))
                .build();
        return result;
    }
}
