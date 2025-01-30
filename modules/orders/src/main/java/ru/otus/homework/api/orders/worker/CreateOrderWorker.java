package ru.otus.homework.api.orders.worker;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.otus.homework.api.orders.entity.OrderEntity;
import ru.otus.homework.api.orders.mapper.OrderMapper;
import ru.otus.homework.api.orders.service.OrderService;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateOrderWorker implements Worker {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Override
    public TaskResult execute(Task task) {
        var result = new TaskResult(task);
        var inputData = task.getInputData();
        log.info("Input data: {}", inputData);
        OrderEntity order = orderService.create(orderMapper.map(inputData));
        result.setOutputData(orderMapper.mapTo(order));
        result.setStatus(TaskResult.Status.COMPLETED);
        return result;
    }

    @Override
    public String getTaskDefName() {
        return "create_order";
    }

}
