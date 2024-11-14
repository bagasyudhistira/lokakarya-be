package co.id.ogya.lokakarya.dto.appuserrole;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AppUserRoleDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("role_id")
    private String roleId;

    @JsonProperty("user_id")
    private String userId;
}
