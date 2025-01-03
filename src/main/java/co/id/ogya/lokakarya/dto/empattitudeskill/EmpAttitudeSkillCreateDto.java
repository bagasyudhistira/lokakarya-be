package co.id.ogya.lokakarya.dto.empattitudeskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class EmpAttitudeSkillCreateDto {
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("attitude_skill_id")
    private String attitudeSkillId;

    @JsonProperty("score")
    private double score;

    @JsonProperty("assessment_year")
    private int assessmentYear;

    @JsonProperty("created_by")
    private String createdBy;
}
