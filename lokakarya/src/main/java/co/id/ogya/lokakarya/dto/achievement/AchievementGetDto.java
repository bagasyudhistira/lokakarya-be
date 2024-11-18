package co.id.ogya.lokakarya.dto.achievement;

import co.id.ogya.lokakarya.entities.Achievement;
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
public class AchievementGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("achievement")
    private String achievement;

    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("enabled")
    private boolean enabled;

    public AchievementGetDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Converting mapping to DTO: {}", convertObject);

        AchievementGetDto result = AchievementGetDto.builder()
                .id((String) convertObject.get("ID"))
                .achievement((String) convertObject.get("ACHIEVEMENT"))
                .groupName((String) convertObject.get("GROUP_NAME"))
                .enabled(convertObject.get("ENABLED") != null && (Boolean) convertObject.get("ENABLED"))
                .build();

        return result;
    }
}
