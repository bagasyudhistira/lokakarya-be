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

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("year")
    private int year;

    @JsonProperty("score")
    private double score;

    @JsonProperty("status")
    private byte status;

    public AssessmentSummaryGetDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Mapping object to AssessmentSummaryGetDto: {}", convertObject);

        AssessmentSummaryGetDto result = AssessmentSummaryGetDto.builder()
                .id((String) convertObject.get("ID"))
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
                .build();

        return result;
    }

}
