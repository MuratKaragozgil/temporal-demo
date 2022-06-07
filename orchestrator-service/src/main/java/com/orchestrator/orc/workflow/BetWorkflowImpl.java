package com.orchestrator.orc.workflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.ActivityStub;
import io.temporal.workflow.Workflow;

import java.time.Duration;

/**
 * @author Murat Karagozgil
 * @since 6.06.2022, Monday
 */
public class BetWorkflowImpl implements BetWorkflow {

    @Override
    public String bet(String accountId) {
        String type = Workflow.getInfo().getWorkflowType();
        System.out.println("Type is here : " + type);

        ActivityStub activity = Workflow.newUntypedActivityStub( //
            ActivityOptions //
                .newBuilder() //
                .setStartToCloseTimeout(Duration.ofSeconds(10)) //
                .build() //
        );

        System.out.println("Type is here " + type);

        // Execute the dynamic Activity. Note that the provided Activity name is not
        // explicitly registered with the Worker
        return activity.execute("doWithdraw", String.class, accountId);
    }

}
