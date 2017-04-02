package com.netease.nim.demo.rts.doodle;

import java.util.List;

/**
 * Created by AllStar on 2017/3/18.
 */
public interface TransactionObserver {
    void onTransaction(List<Transaction> transactions);
}
