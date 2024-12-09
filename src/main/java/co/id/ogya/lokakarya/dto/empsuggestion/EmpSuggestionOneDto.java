package co.id.ogya.lokakarya.dto.empsuggestion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@Slf4j
public class EmpSuggestionOneDto {
    @JsonProperty("suggestion")
    private String suggestion;

    public static EmpSuggestionOneDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Mapping object to EmpSuggestionOneDto: {}", convertObject);

        EmpSuggestionOneDto result = EmpSuggestionOneDto.builder()
                .suggestion((String) convertObject.get("ID"))
                .build();

        return result;
    }
}
