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
import ru.otus.homework.api.orders.controller.examples.OrderExamples;
import ru.otus.homework.api.orders.dto.Order;
import ru.otus.homework.api.orders.mapper.OrderMapper;
import ru.otus.homework.api.orders.service.OrderService;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@Tag(name = "Order", description = "Operations about user order")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Operation(
            operationId = "order_find",
            summary = "Find order",
            description = "Find order"
    )
    @GetMapping(
            path = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "user order",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Order.class),
                            examples = @ExampleObject(value = OrderExamples.GET)
                    )
            )
    })
    public Order find(
            @Parameter(description = "ID of order", example = "85407e18-5a3b-42a4-bdad-6750ef6607eb")
            @PathVariable("id") UUID id
    ) {
        return orderMapper.map(orderService.findById(id));
    }

}
