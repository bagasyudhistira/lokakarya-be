package co.id.ogya.lokakarya.dto.approle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AppRoleUpdateDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("rolename")
    private String rolename;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("updated_by")
    private String updatedBy;
}
