package co.id.ogya.lokakarya.dto.empdevplan;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class EmpDevPlanCreateDto {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("dev_plan_id")
    private String devPlanId;

    @JsonProperty("assessment_year")
    private int assessmentYear;

    @JsonProperty("too_bright")
    private String tooBright;

    @JsonProperty("created_by")
    private String createdBy;
}
