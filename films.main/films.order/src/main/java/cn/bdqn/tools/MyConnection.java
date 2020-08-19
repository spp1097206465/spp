package cn.bdqn.tools;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MyConnection {
    public static Connection getConnection()throws Exception{
        ConnectionFactory connectionFactory =new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        Connection connection =connectionFactory.newConnection();
        return connection;
    }
}
