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
public class EmpAttitudeSkillGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("attitude_skill")
    private String attitudeSkill;

    @JsonProperty("score")
    private double score;

    @JsonProperty("assessment_year")
    private int assessmentYear;

    public static EmpAttitudeSkillGetDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Converting EmpAttitudeSkill map to DTO: {}", convertObject);

        EmpAttitudeSkillGetDto result = EmpAttitudeSkillGetDto.builder()
                .id((String) convertObject.get("ID"))
                .userId((String) convertObject.get("USER_ID"))
                .fullName((String) convertObject.get("FULL_NAME"))
                .attitudeSkill((String) convertObject.get("ATTITUDE_SKILL"))
                .score(convertObject.get("SCORE") != null
                        ? ((BigDecimal) convertObject.get("SCORE")).doubleValue()
                        : null)
                .assessmentYear(convertObject.get("ASSESSMENT_YEAR") != null ? Integer.parseInt(convertObject.get("ASSESSMENT_YEAR").toString()) : null)
                .build();
        return result;
    }
}
