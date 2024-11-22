package co.id.ogya.lokakarya.dto.division;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class DivisionGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("division_name")
    private String divisionName;
}