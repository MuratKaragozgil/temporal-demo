package com.wallet.walletservice.activity;

/**
 * @author Murat Karagozgil
 * @since 6.06.2022, Monday
 */
public class WalletActivityImpl implements WalletActivity {

    @Override
    public String doWithdraw(String accountId) {
        return "Did doWithdraw : " + accountId;
    }

    @Override
    public String cancelWithdraw(String accountId) {
        return "Did cancelWithdraw : " + accountId;
    }

}
