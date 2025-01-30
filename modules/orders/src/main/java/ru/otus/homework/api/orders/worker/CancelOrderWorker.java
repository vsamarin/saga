package ru.otus.homework.api.orders.worker;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.otus.homework.api.orders.service.OrderService;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class CancelOrderWorker implements Worker {

    private final OrderService orderService;

    @Override
    public TaskResult execute(Task task) {
        var result = new TaskResult(task);
        var inputData = task.getInputData();
        log.info("Input data: {}", inputData);
        String orderId = (String) inputData.get("orderId");
        orderService.cancel(UUID.fromString(orderId));
        result.addOutputData("orderId", orderId);
        result.setStatus(TaskResult.Status.COMPLETED);
        return result;
    }

    @Override
    public String getTaskDefName() {
        return "cancel_order";
    }

}
