package rgmek.backend.clients.dadata;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import rgmek.backend.configuration.ApplicationConfig;
import rgmek.backend.dto.dadata.suggestions.Suggestions;

@Service
public class DadataClient {
    private final static String URL = "http://suggestions.dadata.ru";
    private final WebClient client;
    private final ApplicationConfig.Dadata dadata;

    public DadataClient(ApplicationConfig applicationConfig) {
        this.client = WebClient.create(URL);
        this.dadata = applicationConfig.dadata();
    }

    public Suggestions getSuggestions(String address) {
        return client
                .post()
                .uri("/suggestions/api/4_1/rs/suggest/address")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Token %s".formatted(dadata.apiKey()))
                .header("X-Secret", dadata.secretKey())
                .bodyValue("{ \"query\": \"%s\" }".formatted(address))
                .retrieve()
                .bodyToMono(Suggestions.class)
                .block();
    }
}
