package co.id.ogya.lokakarya.dto.groupachievement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class GroupAchievementCreateDto {
    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("percentage")
    private double percentage;

    @JsonProperty("enabled")
    private boolean enabled;

    @JsonProperty("created_by")
    private String createdBy;
}
