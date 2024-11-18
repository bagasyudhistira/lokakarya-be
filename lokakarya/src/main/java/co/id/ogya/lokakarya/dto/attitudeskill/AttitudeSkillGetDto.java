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

    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("enabled")
    private boolean enabled;
    public AttitudeSkillGetDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Mapping object to AttitudeSkillGetDto: {}", convertObject);

        AttitudeSkillGetDto result = AttitudeSkillGetDto.builder()
                .id((String) convertObject.get("ID"))
                .attitudeSkill((String) convertObject.get("ATTITUDE_SKILL"))
                .groupName((String) convertObject.get("GROUP_NAME"))
                .enabled(convertObject.get("ENABLED") != null
                        ? Boolean.parseBoolean(convertObject.get("ENABLED").toString())
                        : false)
                .build();

        return result;
    }

}
