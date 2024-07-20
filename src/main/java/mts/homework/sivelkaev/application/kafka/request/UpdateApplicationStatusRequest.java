package mts.homework.sivelkaev.application.kafka.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateApplicationStatusRequest {
    @NotBlank
    @JsonProperty(value = "id", required = true)
    private Long id;

    @NotBlank
    @JsonProperty(value = "status", required = true)
    private String status;
}
