package co.id.ogya.lokakarya.dto.groupattitudeskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class GroupAttitudeSkillGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("percentage")
    private double percentage;

    @JsonProperty("enabled")
    private boolean enabled;
}
