package co.id.ogya.lokakarya.dto.empdevplan;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@Slf4j
public class EmpDevPlanGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("plan")
    private String plan;

    @JsonProperty("too_bright")
    private String tooBright;

    @JsonProperty("assessment_year")
    private int assessmentYear;

    public static EmpDevPlanGetDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Mapping object to EmpDevPlanGetDto: {}", convertObject);

        EmpDevPlanGetDto result = EmpDevPlanGetDto.builder()
                .id((String) convertObject.get("ID"))
                .userId((String) convertObject.get("USER_ID"))
                .fullName((String) convertObject.get("FULL_NAME"))
                .plan((String) convertObject.get("PLAN"))
                .tooBright((String) convertObject.get("TOO_BRIGHT"))
                .assessmentYear((Integer) convertObject.get("ASSESSMENT_YEAR"))
                .build();

        return result;
    }

}
