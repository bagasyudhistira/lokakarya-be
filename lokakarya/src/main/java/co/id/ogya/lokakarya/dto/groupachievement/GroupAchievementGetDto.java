package co.id.ogya.lokakarya.dto.groupachievement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class GroupAchievementGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("percentage")
    private double percentage;

    @JsonProperty("enabled")
    private boolean enabled;
}
