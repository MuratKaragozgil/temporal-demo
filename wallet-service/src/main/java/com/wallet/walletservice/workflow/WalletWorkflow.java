package com.wallet.walletservice.workflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

/**
 * @author Murat Karagozgil
 * @since 6.06.2022, Monday
 */
@WorkflowInterface
public interface WalletWorkflow {

    @WorkflowMethod(name = "withdraw")
    void withdraw(String accountId);

}
