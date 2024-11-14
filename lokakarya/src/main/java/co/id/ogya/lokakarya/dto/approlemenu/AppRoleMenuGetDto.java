package co.id.ogya.lokakarya.dto.approlemenu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class AppRoleGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("rolename")
    private String rolename;

    @JsonProperty("menu_name")
    private String menuName;
}
