/*
 * Copyright 2020 Conductor Authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package ru.otus.homework.api.orders.config;

import com.netflix.conductor.client.automator.TaskRunnerConfigurer;
import com.netflix.conductor.client.http.ConductorClient;
import com.netflix.conductor.client.http.TaskClient;
import com.netflix.conductor.client.http.WorkflowClient;
import com.netflix.conductor.client.metrics.MetricsCollector;
import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.sdk.workflow.executor.WorkflowExecutor;
import com.netflix.conductor.sdk.workflow.executor.task.AnnotatedWorkerExecutor;
import io.orkes.conductor.client.ApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Configuration
public class ConductorClientConfig {

    @Bean
    public ConductorClient conductorClient() {
        return ApiClient.builder()
                //.useEnvVariables(true)
                .basePath("https://developer.orkescloud.com/api")
                .credentials("spk39518fc1b-def0-11ef-aeb1-c259128d4f69",
                        "qezQQ336JPvw1azvCCfbOyXy6FuCnEKpIEaZotxLPdfcoqkr")
                .build();
    }

    @Bean
    public TaskClient taskClient(ConductorClient client) {
        return new TaskClient(client);
    }

    @Bean
    public AnnotatedWorkerExecutor annotatedWorkerExecutor(TaskClient taskClient) {
        return new AnnotatedWorkerExecutor(taskClient);
    }

    @Bean(initMethod = "init", destroyMethod = "shutdown")
    public TaskRunnerConfigurer taskRunnerConfigurer(Environment env,
                                                     TaskClient taskClient,
                                                     List<Worker> workers,
                                                     Optional<MetricsCollector> metricsCollector) {
        Map<String, Integer> taskThreadCount = new HashMap<>();
        for (Worker worker : workers) {
            String key = "conductor.worker." + worker.getTaskDefName() + ".threadCount";
            int threadCount = env.getProperty(key, Integer.class, 10);
            log.info("Using {} threads for {} worker", threadCount, worker.getTaskDefName());
            taskThreadCount.put(worker.getTaskDefName(), threadCount);
        }


        TaskRunnerConfigurer.Builder builder = new TaskRunnerConfigurer.Builder(taskClient, workers)
                .withTaskThreadCount(taskThreadCount)
                .withThreadCount(1)
                .withSleepWhenRetry((int) Duration.ofMillis(500).toMillis())
                .withUpdateRetryCount(3)
                .withTaskToDomain(new HashMap<>())
                .withShutdownGracePeriodSeconds(10)
                .withTaskPollTimeout(100);
        metricsCollector.ifPresent(builder::withMetricsCollector);
        return builder.build();
    }

    @Bean
    public WorkflowExecutor workflowExecutor(ConductorClient client, AnnotatedWorkerExecutor annotatedWorkerExecutor) {
        return new WorkflowExecutor(client, annotatedWorkerExecutor);
    }

    @Bean
    public WorkflowClient workflowClient(ConductorClient client) {
        return new WorkflowClient(client);
    }
}
