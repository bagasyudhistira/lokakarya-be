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

    @JsonProperty("enabled")
    private boolean enabled;

    @JsonProperty("group_id")
    private String groupId;

    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("group_enabled")
    private boolean groupEnabled;

    @JsonProperty("percentage")
    private double percentage;

    public static AchievementGetDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Converting mapping to DTO: {}", convertObject);

        AchievementGetDto result = AchievementGetDto.builder()
                .id((String) convertObject.get("ID"))
                .achievement((String) convertObject.get("ACHIEVEMENT"))
                .enabled(convertObject.get("ENABLED") != null
                        ? Boolean.parseBoolean(convertObject.get("ENABLED").toString())
                        : false)
                .groupId((String) convertObject.get("GROUP_ID"))
                .groupName((String) convertObject.get("GROUP_NAME"))
                .enabled(convertObject.get("GROUP_ENABLED") != null
                        ? Boolean.parseBoolean(convertObject.get("ENABLED").toString())
                        : false)
                .percentage(convertObject.get("PERCENTAGE") != null
                        ? ((Number) convertObject.get("PERCENTAGE")).doubleValue()
                        : 0.0)
                .build();

        return result;
    }
}
