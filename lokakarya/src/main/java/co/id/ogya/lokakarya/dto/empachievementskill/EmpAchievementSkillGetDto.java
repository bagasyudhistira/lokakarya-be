package co.id.ogya.lokakarya.dto.empachievementskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class EmpAchievementSkillGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("achievement")
    private String achievement;

    @JsonProperty("score")
    private double score;

    @JsonProperty("assessment_year")
    private int assessmentYear;
}
