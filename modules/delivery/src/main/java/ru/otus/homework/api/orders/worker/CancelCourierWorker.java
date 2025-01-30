package ru.otus.homework.api.orders.worker;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.otus.homework.api.orders.service.DeliveryService;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class CancelCourierWorker implements Worker {

    private final DeliveryService deliveryService;

    @Override
    public TaskResult execute(Task task) {
        log.info("Input data: {}", task.getInputData());
        String orderId = (String) task.getInputData().get("orderId");
        deliveryService.cancel(UUID.fromString(orderId));
        var result = new TaskResult(task);
        result.addOutputData("orderId", orderId);
        result.setStatus(TaskResult.Status.COMPLETED);
        return result;
    }

    @Override
    public String getTaskDefName() {
        return "cancel_payment";
    }

}
