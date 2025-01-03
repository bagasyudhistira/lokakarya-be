package co.id.ogya.lokakarya.dto.approle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AppRoleGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("rolename")
    private String rolename;
}
