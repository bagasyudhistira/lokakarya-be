package co.id.ogya.lokakarya.dto.achievement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AchievementCreateDto {

    @JsonProperty("achievement")
    private String achievement;

    @JsonProperty("group_id")
    private String groupId;

    @JsonProperty("enabled")
    private boolean enabled;

    @JsonProperty("created_by")
    private String createdBy;
}
