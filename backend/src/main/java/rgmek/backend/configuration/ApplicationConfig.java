package rgmek.backend.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(
        Dadata dadata
) {
    public record Dadata(
            String apiKey,
            String secretKey
    ) {
    }
}
