package co.id.ogya.lokakarya.dto.empsuggestion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class EmpSuggestionCreateDto {
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("suggestion")
    private String suggestion;

    @JsonProperty("assessment_year")
    private int assessmentYear;

    @JsonProperty("created_by")
    private String createdBy;
}
