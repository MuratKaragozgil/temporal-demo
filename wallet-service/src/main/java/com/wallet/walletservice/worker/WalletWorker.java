package com.wallet.walletservice.worker;

import com.wallet.walletservice.activity.WalletActivityImpl;
import com.wallet.walletservice.workflow.WalletWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @author Murat Karagozgil
 * @since 6.06.2022, Monday
 */
@Component
public class WalletWorker implements InitializingBean {

    private static final String TASK_QUEUE = "TEST_QUEUE_MURAT2";

    // Get a Workflow service stub.
    WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
    /**
     * Get a Workflow service client which can be used to start, Signal, and Query Workflow Executions.
     */
    WorkflowClient client = WorkflowClient.newInstance(service);

    /**
     * Define the workflow factory. It is used to create workflow workers for a specific task queue.
     */
    WorkerFactory factory = WorkerFactory.newInstance(client);

    /**
     * Define the workflow worker. Workflow workers listen to a defined task queue and process workflows and activities.
     */
    Worker worker = factory.newWorker(TASK_QUEUE);

    @Override
    public void afterPropertiesSet() {
        // Workflows are stateful. So you need a type to create instances.
        worker.registerWorkflowImplementationTypes(WalletWorkflowImpl.class);
        // Activities are stateless and thread safe. So a shared instance is used.
        worker.registerActivitiesImplementations(new WalletActivityImpl());
        factory.start();
    }

    @PreDestroy
    public void closeCallBack() {
        System.out.println("Close Callback!");
        factory.suspendPolling();
        factory.shutdown();
        factory.shutdownNow();
    }

}
