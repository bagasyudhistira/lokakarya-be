package co.id.ogya.lokakarya.dto.approlemenu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Slf4j
public class AppRoleMenuGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("rolename")
    private String rolename;

    @JsonProperty("menu_name")
    private String menuName;

    public static AppRoleMenuGetDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Mapping object to AppRoleMenuGetDto: {}", convertObject);

        AppRoleMenuGetDto result = AppRoleMenuGetDto.builder()
                .id((String) convertObject.get("ID"))
                .rolename((String) convertObject.get("ROLENAME"))
                .menuName((String) convertObject.get("MENU_NAME"))
                .build();

        return result;
    }
}
