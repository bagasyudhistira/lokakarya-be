package co.id.ogya.lokakarya.dto.devplan;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class DevPlanUpdateDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("plan")
    private String plan;

    @JsonProperty("enabled")
    private boolean enabled;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("updated_by")
    private String updatedBy;
}
