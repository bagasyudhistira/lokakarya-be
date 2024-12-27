package co.id.ogya.lokakarya.dto.assessmentsummary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AssessmentSummaryDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("year")
    private int year;

    @JsonProperty("score")
    private double score;

    @JsonProperty("status")
    private byte status;

    @JsonProperty("approved_at")
    private LocalDateTime approvedAt;

    @JsonProperty("approved_by")
    private String approvedBy;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("updated_by")
    private String updatedBy;
}
