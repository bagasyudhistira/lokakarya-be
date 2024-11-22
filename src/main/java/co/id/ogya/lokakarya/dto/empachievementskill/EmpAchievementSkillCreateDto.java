package co.id.ogya.lokakarya.dto.empachievementskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class EmpAchievementSkillCreateDto {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("achievement_id")
    private String achievementId;

    @JsonProperty("score")
    private double score;

    @JsonProperty("assessment_year")
    private int assessmentYear;

    @JsonProperty("created_by")
    private String createdBy;
}
