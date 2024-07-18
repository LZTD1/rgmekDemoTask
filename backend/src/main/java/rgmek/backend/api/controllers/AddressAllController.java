package rgmek.backend.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rgmek.backend.api.exceptions.ApiErrorResponse;
import rgmek.backend.api.service.AddressAllService;
import rgmek.backend.dto.database.AdressAll;

@RestController
@RequiredArgsConstructor
@Tag(name = "AddressAll", description = "Взаимодействие с таблицой")
@CrossOrigin(origins = "http://localhost:3000")
public class AddressAllController {

    private final AddressAllService addressAllService;

    @Operation(
            operationId = "findByFias",
            summary = "Поиск адреса по фиас",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно найдены совпадения!"),
                    @ApiResponse(responseCode = "400", description = "Плохое бади запроса!", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))
                    })
            }
    )
    @PostMapping(value = "/findByFias", produces = {"application/json"})
    public AdressAll addressFind(
            @Parameter(name = "FIAS", description = "", required = true)
            @Valid @RequestParam String fias
    ) {
        return addressAllService.getAddressByFias(fias);
    }
}

