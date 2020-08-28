package com.cc.springboot.rocketmq.demo.producer;

import com.cc.springboot.rocketmq.demo.message.Demo07Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 发送事务消息
 *
 * @author wangchen
 * @createDate 2020/08/28
 */
@Component
public class Demo07Producer {

    private static final String TX_PRODUCER_GROUP = "demo07-producer-group";

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送事务消息
     *
     * @param id
     * @author wangchen
     * @createDate 2020/8/28
     **/
    public SendResult sendMessageInTransaction(Integer id){
        Demo07Message demo07Message = new Demo07Message();
        demo07Message.setId(id);
        Message<Demo07Message> message = MessageBuilder.withPayload(demo07Message).build();
        //发送事务消息
        return rocketMQTemplate.sendMessageInTransaction(TX_PRODUCER_GROUP,Demo07Message.TOPIC,message,id);
    }

    @RocketMQTransactionListener(txProducerGroup = TX_PRODUCER_GROUP)
    public class TransactionListenerImpl implements RocketMQLocalTransactionListener{
        private Logger logger = LoggerFactory.getLogger(getClass());

        /**
         * 执行本地事务
         *
         * @param msg, arg
         * @author wangchen
         * @createDate 2020/8/28
         **/
        @Override
        public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
            logger.info("[executeLocalTransaction][执行本地事务: msg: {},arg: {}]",msg,arg);
            return RocketMQLocalTransactionState.UNKNOWN;
        }

        /**
         * 回查本地事务
         *
         * @param msg
         * @author wangchen
         * @createDate 2020/8/28
         **/
        @Override
        public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
            logger.info("[checkLocalTransaction][回查本地事务: msg: {},arg: {}]",msg);
            return RocketMQLocalTransactionState.COMMIT;
        }
    }
}
