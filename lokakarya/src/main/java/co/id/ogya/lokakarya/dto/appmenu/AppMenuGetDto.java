package co.id.ogya.lokakarya.dto.appmenu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@Slf4j
public class AppMenuGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("menu_name")
    private String menuName;

    public static AppMenuGetDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Mapping object to AppMenuGetDto: {}", convertObject);

        AppMenuGetDto result = AppMenuGetDto.builder()
                .id((String) convertObject.get("ID"))
                .menuName((String) convertObject.get("MENU_NAME"))
                .build();

        return result;
    }

}
