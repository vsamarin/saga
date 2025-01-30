package ru.otus.homework.api.orders.controller;

import io.swagger.v3.oas.annotations.Operation;
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
import ru.otus.homework.api.orders.service.WorkFlowService;


@RestController
@RequestMapping("/api/v1/workflow")
@RequiredArgsConstructor
@Tag(name = "WorkFlow", description = "Operations aboutWorkFlow")
public class WorkFlowController {

    private final WorkFlowService workFlowService;

    @Operation(
            operationId = "flow_start",
            summary = "Start flow",
            description = "Start flow"
    )
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Start flow",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Order.class),
                            examples = @ExampleObject(value = OrderExamples.CREATE)
                    )
            )
    })
    public String create(
            @Schema(example = OrderExamples.CREATE)
            @RequestBody Order order
    ) {
        return workFlowService.startDirectFlow(order);
    }

}
