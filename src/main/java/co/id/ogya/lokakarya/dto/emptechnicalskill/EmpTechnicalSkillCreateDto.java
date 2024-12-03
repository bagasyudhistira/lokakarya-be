package co.id.ogya.lokakarya.dto.emptechnicalskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class EmpTechnicalSkillCreateDto {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("technical_skill_id")
    private String technicalSkillId;

    @JsonProperty("skill")
    private String skill;

    @JsonProperty("score")
    private double score;

    @JsonProperty("assessment_year")
    private int assessmentYear;

    @JsonProperty("created_by")
    private String createdBy;
}
