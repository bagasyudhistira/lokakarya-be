package co.id.ogya.lokakarya.dto.emptechnicalskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@Slf4j
public class EmpTechnicalSkillGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("technical_skill")
    private String technicalSkill;

    @JsonProperty("score")
    private double score;

    @JsonProperty("assessment_year")
    private int assessmentYear;

    public static EmpTechnicalSkillGetDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Mapping object to EmpTechnicalSkillGetDto: {}", convertObject);

        EmpTechnicalSkillGetDto result = EmpTechnicalSkillGetDto.builder()
                .id((String) convertObject.get("ID"))
                .userId((String) convertObject.get("USER_ID"))
                .fullName((String) convertObject.get("FULL_NAME"))
                .technicalSkill((String) convertObject.get("TECHNICAL_SKILL"))
                .score(convertObject.get("SCORE") != null
                        ? ((BigDecimal) convertObject.get("SCORE")).doubleValue()
                        : null)
                .assessmentYear((Integer) convertObject.get("ASSESSMENT_YEAR"))
                .build();

        return result;
    }
}
