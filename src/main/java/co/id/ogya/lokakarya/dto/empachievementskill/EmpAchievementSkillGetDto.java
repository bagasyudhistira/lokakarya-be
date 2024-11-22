package co.id.ogya.lokakarya.dto.empachievementskill;

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

    public static EmpAchievementSkillGetDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Mapping object to EmpAchievementSkillGetDto: {}", convertObject);

        EmpAchievementSkillGetDto result = EmpAchievementSkillGetDto.builder()
                .id((String) convertObject.get("ID"))
                .fullName((String) convertObject.get("FULL_NAME"))
                .notes((String) convertObject.get("NOTES"))
                .achievement((String) convertObject.get("ACHIEVEMENT"))
                .score(convertObject.get("SCORE") != null
                        ? Double.parseDouble(convertObject.get("SCORE").toString())
                        : 0.0)
                .assessmentYear(convertObject.get("ASSESSMENT_YEAR") != null
                        ? Integer.parseInt(convertObject.get("ASSESSMENT_YEAR").toString())
                        : 0)
                .build();

        return result;
    }

}
