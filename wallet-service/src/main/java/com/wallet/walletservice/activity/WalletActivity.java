package com.wallet.walletservice.activity;

import io.temporal.activity.ActivityInterface;

/**
 * @author Murat Karagozgil
 * @since 6.06.2022, Monday
 */

@ActivityInterface
public interface WalletActivity {

    String doWithdraw(String accountId);

    String cancelWithdraw(String accountId);

}
