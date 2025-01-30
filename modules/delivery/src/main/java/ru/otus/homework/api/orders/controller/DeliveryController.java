package ru.otus.homework.api.orders.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework.api.orders.controller.examples.DeliveryExamples;
import ru.otus.homework.api.orders.dto.Courier;
import ru.otus.homework.api.orders.mapper.CourierMapper;
import ru.otus.homework.api.orders.service.DeliveryService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/delivery")
@RequiredArgsConstructor
@Tag(name = "Delivery", description = "Operations about couriers")
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final CourierMapper courierMapper;

    @Operation(
            operationId = "find_courier",
            summary = "Find courier",
            description = "Find courier"
    )
    @GetMapping(
            path = "/{orderId}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "courier",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Courier.class),
                            examples = @ExampleObject(value = DeliveryExamples.GET)
                    )
            )
    })
    public Courier find(
            @Parameter(description = "ID of order", example = "85407e18-5a3b-42a4-bdad-6750ef6607eb")
            @PathVariable("orderId") UUID orderId
    ) {
        return courierMapper.map(deliveryService.findByOrderId(orderId));
    }

}
