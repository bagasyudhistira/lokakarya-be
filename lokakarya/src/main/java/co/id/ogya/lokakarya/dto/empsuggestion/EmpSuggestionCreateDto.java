package co.id.ogya.lokakarya.dto.empsuggestion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class EmpSuggestionCreateDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("suggestion")
    private String suggestion;

    @JsonProperty("assessment_year")
    private int assessmentYear;

    @JsonProperty("created_by")
    private String createdBy;
}
