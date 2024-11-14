package co.id.ogya.lokakarya.dto.achievement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AchievementGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("achievement")
    private String achievement;

    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("enabled")
    private boolean enabled;
}
