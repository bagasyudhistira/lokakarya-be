package co.id.ogya.lokakarya.dto.assessmentsummary;

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
public class AttitudeSkillSummaryGetDto {
    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("sum_score")
    private Double sumScore;

    @JsonProperty("percentage")
    private Double percentage;

    public static AttitudeSkillSummaryGetDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Mapping object to AttitudeSkillSummaryGetDto: {}", convertObject);

        AttitudeSkillSummaryGetDto result = AttitudeSkillSummaryGetDto.builder()
                .groupName((String) convertObject.get("GROUP_NAME"))
                .sumScore(convertObject.get("SUM_SCORE") != null
                        ? ((BigDecimal) convertObject.get("SUM_SCORE")).doubleValue()
                        : null)
                .percentage(convertObject.get("PERCENTAGE") != null
                        ? ((BigDecimal) convertObject.get("PERCENTAGE")).doubleValue()
                        : null)
                .build();

        return result;
    }
}
