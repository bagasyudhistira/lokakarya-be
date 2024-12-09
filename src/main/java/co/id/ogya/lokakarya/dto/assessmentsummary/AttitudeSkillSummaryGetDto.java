package co.id.ogya.lokakarya.dto.assessmentsummary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

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
                .sumScore((Double) convertObject.get("SUM_SCORE"))
                .percentage((Double) convertObject.get("PERCENTAGE"))
                .build();

        return result;
    }
}
