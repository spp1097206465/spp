package cn.bdqn.service;

import cn.bdqn.util.MyConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import org.springframework.stereotype.Service;

@Service
public class PayService {

    public void sendOrder(String orderId){
        System.out.println("执行发送mq"+orderId);
        try {
            Connection connection = MyConnection.getConnection();
            Channel channel = connection.createChannel();
            String queueName = "myQueue";
            channel.queueDeclare(queueName, true, false, false, null);
            channel.basicPublish("", queueName, null, orderId.getBytes());
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
