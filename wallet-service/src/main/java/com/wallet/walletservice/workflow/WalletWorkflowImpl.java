package com.wallet.walletservice.workflow;

import com.wallet.walletservice.activity.WalletActivity;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.failure.ActivityFailure;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;

import java.time.Duration;

/**
 * @author Murat Karagozgil
 * @since 6.06.2022, Monday
 */
public class WalletWorkflowImpl implements WalletWorkflow {

    private final ActivityOptions options = ActivityOptions //
        .newBuilder() //
        .setStartToCloseTimeout(Duration.ofHours(1)) //
        // disable retries for example to run faster
        .setRetryOptions( //
            RetryOptions //
                .newBuilder() //
                .setMaximumAttempts(1) //
                .build() //
        ) //
        .build();

    private final WalletActivity activities = Workflow.newActivityStub(WalletActivity.class, options);

    @Override
    public void withdraw(String accountId) {
        // Configure SAGA to run compensation activities in parallel
        Saga.Options sagaOptions = new Saga.Options.Builder().setParallelCompensation(true).build();
        Saga saga = new Saga(sagaOptions);
        try {
            String result = activities.doWithdraw(accountId);
            saga.addCompensation(activities::cancelWithdraw, accountId);

            System.out.println("Result : " + result);
        } catch (ActivityFailure e) {
            saga.compensate();
            throw e;
        }
    }

}
