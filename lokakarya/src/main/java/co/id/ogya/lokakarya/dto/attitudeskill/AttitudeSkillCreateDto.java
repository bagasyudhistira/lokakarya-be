package co.id.ogya.lokakarya.dto.attitudeskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AttitudeSkillCreateDto {

    @JsonProperty("attitude_skill")
    private String attitudeSkill;

    @JsonProperty("group_id")
    private String groupId;

    @JsonProperty("enabled")
    private boolean enabled;

    @JsonProperty("created_by")
    private String createdBy;
}
