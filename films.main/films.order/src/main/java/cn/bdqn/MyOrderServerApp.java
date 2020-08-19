package cn.bdqn;

import cn.bdqn.filters.MyFeignInteceptor;
import cn.bdqn.mq.PayServiceMQ;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@EnableSwagger2
@SpringBootApplication
@EnableFeignClients
@EnableTransactionManagement
@EnableScheduling
public class MyOrderServerApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MyOrderServerApp.class, args);
        PayServiceMQ bean = context.getBean(PayServiceMQ.class);
        bean.revicePayOrder();
    }

    @Bean
    public MyFeignInteceptor myFeignInteceptor() {
        return new MyFeignInteceptor();
    }
}
