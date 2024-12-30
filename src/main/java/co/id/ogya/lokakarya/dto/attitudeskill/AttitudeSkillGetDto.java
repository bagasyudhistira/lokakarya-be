package co.id.ogya.lokakarya.dto.attitudeskill;

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
public class AttitudeSkillGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("attitude_skill")
    private String attitudeSkill;

    @JsonProperty("enabled")
    private boolean enabled;

    @JsonProperty("group_id")
    private String groupId;

    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("group_enabled")
    private boolean groupEnabled;

    @JsonProperty("percentage")
    private double percentage;

    public static AttitudeSkillGetDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Mapping object to AttitudeSkillGetDto: {}", convertObject);

        AttitudeSkillGetDto result = AttitudeSkillGetDto.builder()
                .id((String) convertObject.get("ID"))
                .attitudeSkill((String) convertObject.get("ATTITUDE_SKILL"))
                .enabled(convertObject.get("ENABLED") != null
                        ? Boolean.parseBoolean(convertObject.get("ENABLED").toString())
                        : false)
                .groupId((String) convertObject.get("GROUP_ID"))
                .groupName((String) convertObject.get("GROUP_NAME"))
                .enabled(convertObject.get("GROUP_ENABLED") != null
                        ? Boolean.parseBoolean(convertObject.get("ENABLED").toString())
                        : false)
                .percentage((double) convertObject.get("PERCENTAGE"))
                .build();

        return result;
    }

}
