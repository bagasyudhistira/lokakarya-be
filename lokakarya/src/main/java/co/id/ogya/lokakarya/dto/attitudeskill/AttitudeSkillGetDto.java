package co.id.ogya.lokakarya.dto.attitudeskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AttitudeSkillGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("attitude_skill")
    private String attitudeSkill;

    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("enabled")
    private boolean enabled;
}
