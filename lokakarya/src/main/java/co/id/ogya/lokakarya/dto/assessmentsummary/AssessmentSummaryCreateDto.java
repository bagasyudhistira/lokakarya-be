package co.id.ogya.lokakarya.dto.assessmentsummary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AssessmentSummaryCreateDto {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("year")
    private int year;

    @JsonProperty("score")
    private double score;

    @JsonProperty("status")
    private byte status;

    @JsonProperty("created_by")
    private String createdBy;
}
