package co.id.ogya.lokakarya.dto.assessmentsummary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@Slf4j
public class AssessmentSummaryJointGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("division_name")
    private String divisionName;

    @JsonProperty("status")
    private byte status;

    @JsonProperty("score")
    private double score;

    @JsonProperty("approved_at")
    private LocalDateTime approvedAt;

    @JsonProperty("approved_by")
    private String approvedBy;

    public static AssessmentSummaryJointGetDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Mapping object to AssessmentSummaryGetDto: {}", convertObject);

        AssessmentSummaryJointGetDto result = AssessmentSummaryJointGetDto.builder()
                .id((String) convertObject.get("ID"))
                .userId((String) convertObject.get("USER_ID"))
                .fullName((String) convertObject.get("FULL_NAME"))
                .divisionName((String) convertObject.get("DIVISION_NAME"))
                .score(convertObject.get("SCORE") != null
                        ? ((Number) convertObject.get("SCORE")).doubleValue()
                        : 0.0)
                .status(convertObject.get("STATUS") != null
                        ? ((Number) convertObject.get("STATUS")).byteValue()
                        : 0)
                .approvedAt((LocalDateTime) convertObject.get("APPROVED_AT"))
                .approvedBy((String) convertObject.get("APPROVER_NAME"))
                .build();

        return result;
    }
}
