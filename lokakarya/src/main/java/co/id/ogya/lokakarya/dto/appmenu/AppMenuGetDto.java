package co.id.ogya.lokakarya.dto.appmenu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AppMenuGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("menu_name")
    private String menuName;
}
