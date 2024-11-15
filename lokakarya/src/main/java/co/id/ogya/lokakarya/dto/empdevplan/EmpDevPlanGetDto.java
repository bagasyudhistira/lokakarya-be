package co.id.ogya.lokakarya.dto.empdevplan;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class EmpDevPlanGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("plan")
    private String plan;

    @JsonProperty("assessment_year")
    private int assessmentYear;
}
