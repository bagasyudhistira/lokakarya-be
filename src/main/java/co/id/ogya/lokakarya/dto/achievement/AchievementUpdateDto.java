package co.id.ogya.lokakarya.dto.achievement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AchievementUpdateDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("achievement")
    private String achievement;

    @JsonProperty("group_id")
    private String groupId;

    @JsonProperty("enabled")
    private boolean enabled;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("updated_by")
    private String updatedBy;
}
