package co.id.ogya.lokakarya.dto.devplan;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class DevPlanGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("plan")
    private String plan;

    @JsonProperty("enabled")
    private boolean enabled;
}
