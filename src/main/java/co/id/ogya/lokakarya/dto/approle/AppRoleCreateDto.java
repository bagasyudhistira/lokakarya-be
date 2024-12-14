package co.id.ogya.lokakarya.dto.approle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AppRoleCreateDto {
    @JsonProperty("rolename")
    private String rolename;

    @JsonProperty("created_by")
    private String createdBy;
}
