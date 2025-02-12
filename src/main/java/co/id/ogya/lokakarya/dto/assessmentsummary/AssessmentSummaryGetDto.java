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
public class AssessmentSummaryGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("year")
    private int year;

    @JsonProperty("score")
    private double score;

    @JsonProperty("status")
    private byte status;

    @JsonProperty("division_name")
    private String divisionName;

    @JsonProperty("approved_at")
    private LocalDateTime approvedAt;

    @JsonProperty("approver_name")
    private String approverName;

    public static AssessmentSummaryGetDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Mapping object to AssessmentSummaryGetDto: {}", convertObject);

        AssessmentSummaryGetDto result = AssessmentSummaryGetDto.builder()
                .id((String) convertObject.get("ID"))
                .userId((String) convertObject.get("USER_ID"))
                .fullName((String) convertObject.get("FULL_NAME"))
                .year(convertObject.get("YEAR") != null
                        ? ((Number) convertObject.get("YEAR")).intValue()
                        : 0)
                .score(convertObject.get("SCORE") != null
                        ? ((Number) convertObject.get("SCORE")).doubleValue()
                        : 0.0)
                .status(convertObject.get("STATUS") != null
                        ? ((Number) convertObject.get("STATUS")).byteValue()
                        : 0)
                .divisionName((String) convertObject.get("DIVISION_NAME"))
                .approvedAt((LocalDateTime) convertObject.get("APPROVED_AT"))
                .approverName((String) convertObject.get("APPROVER_NAME"))
                .build();

        return result;
    }

}
