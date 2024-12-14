package co.id.ogya.lokakarya.dto.approlemenu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class AppRoleMenuCreateDto {
    @JsonProperty("role_id")
    private String roleId;

    @JsonProperty("menu_id")
    private String menuId;
}
