package cn.bdqn.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    //添加选课任务交换机
    public static final String EX_LEARNING_ADDCHOOSECOURSE = "ex_learning_addchoosecourse";

    //添加选课消息队列
    public static final String XC_LEARNING_ADDCHOOSECOURSE = "xc_learning_addchoosecourse";

    //完成添加选课消息队列
    public static final String XC_LEARNING_FINISHADDCHOOSECOURSE = "xc_learning_finishaddchoosecourse";

    //添加选课路由key
    public static final String XC_LEARNING_ADDCHOOSECOURSE_KEY = "addchoosecourse";
    //完成添加选课路由key
    public static final String XC_LEARNING_FINISHADDCHOOSECOURSE_KEY = "finishaddchoosecourse";


    /**
     * 交换机配置
     *
     * @return the exchange
     */
    @Bean(EX_LEARNING_ADDCHOOSECOURSE)
    public Exchange EX_DECLARE() {
        return
                ExchangeBuilder.directExchange(EX_LEARNING_ADDCHOOSECOURSE).durable(true).build();
    }

    //声明队列
    @Bean(XC_LEARNING_FINISHADDCHOOSECOURSE)
    public Queue QUEUE_XC_LEARNING_FINISHADDCHOOSECOURSE() {
        Queue queue = new Queue(XC_LEARNING_FINISHADDCHOOSECOURSE);
        return queue;
    }

    //声明队列
    @Bean(XC_LEARNING_ADDCHOOSECOURSE)
    public Queue QUEUE_XC_LEARNING_ADDCHOOSECOURSE() {
        Queue queue = new Queue(XC_LEARNING_ADDCHOOSECOURSE);
        return queue;
    }

    /**
     * 绑定队列到交换机   .
     *
     * @return the binding
     */
    @Bean
    public Binding
    BINDING_QUEUE_FINISHADDCHOOSECOURSE() {
        return
                BindingBuilder.bind(this.QUEUE_XC_LEARNING_FINISHADDCHOOSECOURSE()).to(this.EX_DECLARE()).with(XC_LEARNING_FINISHADDCHOOSECOURSE_KEY).noargs();
    }

    @Bean
    public Binding BINDING_QUEUE_ADDCHOOSECOURSE() {
        return
                BindingBuilder.bind(this.QUEUE_XC_LEARNING_ADDCHOOSECOURSE()).to(this.EX_DECLARE()).with(XC_LEARNING_ADDCHOOSECOURSE_KEY).noargs();
    }


}