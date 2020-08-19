package cn.bdqn.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyDlxConfig {
    //第一个死信队列
    private static final String ORDER_DELAY_QUEUE = "user.order.delay.queue";
    public static final String ORDER_DELAY_EXCHANGE = "user.order.delay.exchange";
    public static final String ORDER_DELAY_ROUTING_KEY = "order.delay";

    //死信路由到的第二个正常队列
    public static final String ORDER_QUEUE_NAME = "user.order.queue";
    public static final String ORDER_EXCHANGE_NAME = "user.order.exchange";
    public static final String ORDER_ROUTING_KEY = "order";

    /*死信的队列 死信的绑定*/
    @Bean
    public Queue delayOrderQueue() {
        Map<String, Object> params = new HashMap<>();
        params.put("x-dead-letter-exchange", ORDER_EXCHANGE_NAME);
        params.put("x-dead-letter-routing-key", ORDER_ROUTING_KEY);
        return new Queue(ORDER_DELAY_QUEUE, true, false, false, params);
    }

    @Bean
    public DirectExchange orderDelayExchange() {
        return new DirectExchange(ORDER_DELAY_EXCHANGE);
    }

    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(delayOrderQueue()).to(orderDelayExchange()).with(ORDER_DELAY_ROUTING_KEY);
    }

    /*第二个队列*/
    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE_NAME, true);
    }

    @Bean
    public TopicExchange orderTopicExchange() {
        return new TopicExchange(ORDER_EXCHANGE_NAME);
    }

    @Bean
    public Binding orderBinding() {

        return BindingBuilder.bind(orderQueue()).to(orderTopicExchange()).with(ORDER_ROUTING_KEY);
    }
}
