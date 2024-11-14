package co.id.ogya.lokakarya.dto.attitudeskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AttitudeSkillDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("attitude_skill")
    private String attitudeSkill;

    @JsonProperty("group_id")
    private String groupId;

    @JsonProperty("enabled")
    private byte enabled;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("updated_by")
    private String updatedBy;
}
