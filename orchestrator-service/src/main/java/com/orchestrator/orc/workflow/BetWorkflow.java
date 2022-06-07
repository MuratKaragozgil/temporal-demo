package com.orchestrator.orc.workflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

/**
 * @author Murat Karagozgil
 * @since 6.06.2022, Monday
 */
@WorkflowInterface
public interface BetWorkflow {

    @WorkflowMethod(name = "bet")
    String bet(String accountId);

}
