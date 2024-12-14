package co.id.ogya.lokakarya.dto.devplan;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class DevPlanCreateDto {
    @JsonProperty("plan")
    private String plan;

    @JsonProperty("enabled")
    private boolean enabled;

    @JsonProperty("created_by")
    private String createdBy;
}
