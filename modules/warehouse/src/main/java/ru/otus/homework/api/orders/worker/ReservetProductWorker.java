package ru.otus.homework.api.orders.worker;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.otus.homework.api.orders.entity.ProductEntity;
import ru.otus.homework.api.orders.mapper.ProductMapper;
import ru.otus.homework.api.orders.service.ProductService;
import ru.otus.homework.api.orders.service.WorkFlowService;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservetProductWorker implements Worker {

    private final ProductService productService;
    private final WorkFlowService workFlowService;
    private final ProductMapper productMapper;

    @Override
    public TaskResult execute(Task task) {
        try {
            log.info("Input data: {}", task.getInputData());
            ProductEntity product = productService.reserve(productMapper.map(task.getInputData()));
            var result = new TaskResult(task);
            result.setOutputData(productMapper.map(product));
            result.setStatus(TaskResult.Status.COMPLETED);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            String orderId = (String) task.getInputData().get("orderId");
            workFlowService.startCompensationFlow(UUID.fromString(orderId));
            var result = new TaskResult(task);
            result.setStatus(TaskResult.Status.FAILED);
            result.addOutputData("error", e.getMessage());
            return result;
        }
    }

    @Override
    public String getTaskDefName() {
        return "reserve_product";
    }

}
