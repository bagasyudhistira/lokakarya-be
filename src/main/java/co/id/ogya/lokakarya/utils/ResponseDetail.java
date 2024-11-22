package co.id.ogya.lokakarya.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@Schema
public class ResponseDetail {
    @JsonProperty("status")
    @Schema(example = "200", description = "status code")
    private int status;
    @JsonProperty("message")
    @Schema(example = "Success", description = "response message")
    private String message;
    @JsonProperty("detail_message")
    @Schema(example = "Success get data", description = "detailed response message")
    private Object detailMessage;
    @JsonProperty("execution_time")
    @Schema(example = "00:00:10.523", description = "time execution per request (in format time)")
    private String executionTime;
    @JsonProperty("detail_info")
    @Schema(example = "OK", description = "detail info")
    private Object detailInfo;
}
