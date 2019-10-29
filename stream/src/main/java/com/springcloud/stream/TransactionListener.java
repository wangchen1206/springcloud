package com.springcloud.stream;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

/**
 * @Author: cc
 * @Date: 2019/10/29 16:36
 */
@RocketMQTransactionListener(txProducerGroup = "myTxProducerGroup", corePoolSize = 5,
        maximumPoolSize = 10)
public class TransactionListener implements RocketMQLocalTransactionListener {
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object arg) {
        Object num = message.getHeaders().get("test");
        if ("1".equals(num)) {
            System.out.println(
                    "executer: " + new String((byte[]) message.getPayload()) + " unknown");
            return RocketMQLocalTransactionState.UNKNOWN;
        }
        else if ("2".equals(num)) {
            System.out.println(
                    "executer: " + new String((byte[]) message.getPayload()) + " rollback");
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        System.out.println(
                "executer: " + new String((byte[]) message.getPayload()) + " commit");
        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        System.out.println("check: " + new String((byte[]) message.getPayload()));
        return RocketMQLocalTransactionState.COMMIT;
    }
}
