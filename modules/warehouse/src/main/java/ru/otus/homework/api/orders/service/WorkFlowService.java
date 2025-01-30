package ru.otus.homework.api.orders.service;

import com.netflix.conductor.client.http.WorkflowClient;
import com.netflix.conductor.common.metadata.workflow.StartWorkflowRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkFlowService {

    private final WorkflowClient workflowClient;
    private final Environment environment;

    public void startCompensationFlow(UUID orderId) {
        StartWorkflowRequest request = new StartWorkflowRequest();
        request.setName("compensation_workflow");
        request.setVersion(1);
        request.setCorrelationId("api-triggered");
        HashMap<String, Object> inputMap = new HashMap<>();
        inputMap.put("orderId", orderId);
        request.setInput(inputMap);

        String TASK_DOMAIN_PROPERTY = "conductor.worker.all.domain";
        String domain = environment.getProperty(TASK_DOMAIN_PROPERTY, String.class, "");

        if (!domain.isEmpty()) {
            Map<String, String> taskToDomain = new HashMap<>();
            taskToDomain.put("*", domain);
            request.setTaskToDomain(taskToDomain);
        }

        String workflowId;
        try {
            workflowId = workflowClient.startWorkflow(request);
            log.info("Workflow id: {}", workflowId);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

}
