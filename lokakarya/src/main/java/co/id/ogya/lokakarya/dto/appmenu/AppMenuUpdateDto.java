package co.id.ogya.lokakarya.dto.appmenu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AppMenuUpdateDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("menu_name")
    private String menuName;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("updated_by")
    private String updatedBy;
}
