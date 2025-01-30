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
import ru.otus.homework.api.orders.controller.examples.WarehouseExamples;
import ru.otus.homework.api.orders.dto.Product;
import ru.otus.homework.api.orders.mapper.ProductMapper;
import ru.otus.homework.api.orders.service.ProductService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/warehouse")
@RequiredArgsConstructor
@Tag(name = "Warehouse", description = "Operations about products")
public class WarehouseController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Operation(
            operationId = "find_product",
            summary = "Find product",
            description = "Find product"
    )
    @GetMapping(
            path = "/{orderId}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "product",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Product.class),
                            examples = @ExampleObject(value = WarehouseExamples.GET)
                    )
            )
    })
    public Product find(
            @Parameter(description = "ID of order", example = "85407e18-5a3b-42a4-bdad-6750ef6607eb")
            @PathVariable("orderId") UUID orderId
    ) {
        return productMapper.mapTo(productService.findByIdOrderId(orderId));
    }

}
