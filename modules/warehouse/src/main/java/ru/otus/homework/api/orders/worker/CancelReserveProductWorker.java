package ru.otus.homework.api.orders.worker;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.otus.homework.api.orders.service.ProductService;

import java.util.UUID;


@Slf4j
@Component
@RequiredArgsConstructor
public class CancelReserveProductWorker implements Worker {

    private final ProductService productService;

    @Override
    public TaskResult execute(Task task) {
        try {
            log.info("Input data: {}", task.getInputData());
            String orderId = (String) task.getInputData().get("orderId");
            productService.available(UUID.fromString(orderId));
            var result = new TaskResult(task);
            result.addOutputData("orderId", orderId);
            result.setStatus(TaskResult.Status.COMPLETED);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            var result = new TaskResult(task);
            result.setStatus(TaskResult.Status.FAILED);
            result.addOutputData("error", e.getMessage());
            return result;
        }
    }

    @Override
    public String getTaskDefName() {
        return "cancel_payment";
    }

}
