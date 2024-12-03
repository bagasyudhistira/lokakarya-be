package co.id.ogya.lokakarya.dto.empachievementskill;

import co.id.ogya.lokakarya.dto.empattitudeskill.EmpAttitudeSkillGetUIDYearDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@Slf4j
public class EmpAchievementSkillGetUIDYearDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("achievement_id")
    private String achievementId;

    @JsonProperty("score")
    private double score;

    @JsonProperty("group_id")
    private String groupId;

    public static EmpAchievementSkillGetUIDYearDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Converting EmpAttitudeSkill map to DTO: {}", convertObject);

        EmpAchievementSkillGetUIDYearDto result = EmpAchievementSkillGetUIDYearDto.builder()
                .id((String) convertObject.get("ID"))
                .achievementId((String) convertObject.get("ACHIEVEMENT_ID"))
                .score(convertObject.get("SCORE") != null
                        ? ((BigDecimal) convertObject.get("SCORE")).doubleValue()
                        : null)
                .groupId((String) convertObject.get("GROUP_ID"))
                .build();
        return result;
    }
}
