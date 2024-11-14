package co.id.ogya.lokakarya.dto.technicalskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class TechnicalSkillGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("technical_skill")
    private String technicalSkill;

    @JsonProperty("enabled")
    private boolean enabled;
}
