package rgmek.backend.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import rgmek.backend.api.exceptions.ApiErrorResponse;
import rgmek.backend.api.exceptions.BadBodyException;
import rgmek.backend.clients.dadata.DadataClient;
import rgmek.backend.dto.dadata.suggestions.Suggestions;

@RestController
@RequiredArgsConstructor
@Tag(name = "Suggestions", description = "Предсказание адреса")
public class SuggestionsController {

    private final DadataClient dadataClient;

    @Operation(
            operationId = "addressFind",
            summary = "Поиск адреса",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно найдены совпадения!"),
                    @ApiResponse(responseCode = "400", description = "Плохое бади запроса!", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))
                    })
            }
    )
    @PostMapping(
            value = "/suggest",
            produces = {"application/json"}
    )
    public Suggestions addressFind(
            @Parameter(name = "address", description = "", required = true)
            @RequestParam String address
    ) {
        try {
            return dadataClient.getSuggestions(address);
        } catch (WebClientResponseException.BadRequest ex) {
            throw new BadBodyException(ex.getMessage());
        }
    }
}
