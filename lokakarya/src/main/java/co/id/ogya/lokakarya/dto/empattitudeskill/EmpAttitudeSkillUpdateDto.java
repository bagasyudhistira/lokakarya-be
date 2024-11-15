package co.id.ogya.lokakarya.dto.empattitudeskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class EmpAttitudeSkillUpdateDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("attitude_skill_id")
    private String attitudeSkillId;

    @JsonProperty("score")
    private double score;

    @JsonProperty("assessment_year")
    private int assessmentYear;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("updated_by")
    private String updatedBy;
}
