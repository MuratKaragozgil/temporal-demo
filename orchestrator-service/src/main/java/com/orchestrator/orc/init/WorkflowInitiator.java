package com.orchestrator.orc.init;

import com.orchestrator.orc.workflow.BetWorkflow;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowException;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import io.temporal.serviceclient.WorkflowServiceStubs;

/**
 * @author Murat Karagozgil
 * @since 6.06.2022, Monday
 */
public class WorkflowInitiator {

    private static final String TASK_QUEUE = "TEST_QUEUE_MURAT2";

    public static void main(String[] args) {
        // gRPC stubs wrapper that talks to the local docker instance of temporal service.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        // client that can be used to start and signal workflows
        WorkflowClient client = WorkflowClient.newInstance(service);

        System.out.println("Worker started for task queue: " + TASK_QUEUE);

        // now we can start running instances of our saga - its state will be persisted
        WorkflowOptions options = WorkflowOptions.newBuilder().setTaskQueue(TASK_QUEUE).build();
        WorkflowStub workflowStub = client.newUntypedWorkflowStub("withdraw", options);
        try {
            WorkflowExecution execution = workflowStub.start("Murat12345");
            System.out.println("Bitti");
        } catch (WorkflowException e) {
            // Expected
        }
    }

}
