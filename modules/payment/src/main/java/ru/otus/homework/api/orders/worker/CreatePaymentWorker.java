package ru.otus.homework.api.orders.worker;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.otus.homework.api.orders.entity.PaymentEntity;
import ru.otus.homework.api.orders.mapper.PaymentMapper;
import ru.otus.homework.api.orders.service.PaymentService;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreatePaymentWorker implements Worker {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @Override
    public TaskResult execute(Task task) {
        try {
            log.info("Input data: {}", task.getInputData());
            PaymentEntity payment = paymentService.create(paymentMapper.map(task.getInputData()));
            var result = new TaskResult(task);
            result.setOutputData(paymentMapper.map(payment));
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
        return "create_payment";
    }

}
