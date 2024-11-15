package co.id.ogya.lokakarya.dto.appuserrole;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AppUserRoleGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("rolename")
    private String rolename;

    @JsonProperty("full_name")
    private String fullName;
}
