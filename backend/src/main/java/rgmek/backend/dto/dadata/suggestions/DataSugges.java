package rgmek.backend.dto.dadata.suggestions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataSugges {
    @JsonProperty("fias_id")
    private String fiasId;
}
