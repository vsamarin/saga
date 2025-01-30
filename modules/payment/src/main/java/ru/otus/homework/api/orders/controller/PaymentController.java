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
import ru.otus.homework.api.orders.controller.examples.PaymentExamples;
import ru.otus.homework.api.orders.dto.Payment;
import ru.otus.homework.api.orders.mapper.PaymentMapper;
import ru.otus.homework.api.orders.service.PaymentService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@Tag(name = "Payment", description = "Operations about payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @Operation(
            operationId = "find_payment",
            summary = "Find payment",
            description = "Find payment"
    )
    @GetMapping(
            path = "/{orderId}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "payment",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Payment.class),
                            examples = @ExampleObject(value = PaymentExamples.GET)
                    )
            )
    })
    public Payment find(
            @Parameter(description = "ID of order", example = "85407e18-5a3b-42a4-bdad-6750ef6607eb")
            @PathVariable("orderId") UUID orderId
    ) {
        return paymentMapper.mapTo(paymentService.findByOrderId(orderId));
    }

}
