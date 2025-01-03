package co.id.ogya.lokakarya.dto.empsuggestion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@Slf4j
public class EmpSuggestionGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("suggestion")
    private String suggestion;

    @JsonProperty("assessment_year")
    private int assessmentYear;

    public static EmpSuggestionGetDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Mapping object to EmpSuggestionGetDto: {}", convertObject);

        EmpSuggestionGetDto result = EmpSuggestionGetDto.builder()
                .id((String) convertObject.get("ID"))
                .userId((String) convertObject.get("USER_ID"))
                .fullName((String) convertObject.get("FULL_NAME"))
                .suggestion((String) convertObject.get("SUGGESTION"))
                .assessmentYear((Integer) convertObject.get("ASSESSMENT_YEAR"))
                .build();

        return result;
    }
}
