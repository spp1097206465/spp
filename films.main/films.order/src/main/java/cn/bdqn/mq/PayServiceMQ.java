package cn.bdqn.mq;

import com.rabbitmq.client.*;
import com.zb.config.RabbitMQConfig;
import com.zb.entity.TbOrder;
import com.zb.mapper.TbOrderMapper;
import com.zb.tools.MyConnection;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class PayServiceMQ {

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @RabbitListener(queues = "user.order.queue")
    public void reviceOrderStatus(TbOrder tbOrder, Message message ,Channel channel){
        try {
            TbOrder tbOrderById = tbOrderMapper.getTbOrderById(Long.parseLong(tbOrder.getId()));
            System.out.println("查询到的状态:"+tbOrderById.getOrderStatus());
            if(tbOrderById.getOrderStatus().equals("0")){
                TbOrder order =new TbOrder();
                order.setId(tbOrder.getId());
                order.setOrderStatus("2");
                try {
                    tbOrderMapper.updateTbOrder(order);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(tbOrder.getId()+"当前订单失效");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void revicePayOrder(){
        try {
            Connection connection = MyConnection.getConnection();
            Channel channel = connection.createChannel();
            String queueName = "myQueue";
            channel.queueDeclare(queueName, true, false, false, null);

            DefaultConsumer consumer =new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String msg =new String(body,"UTF-8");
                    System.out.println("接收到的数据:"+msg);
                    TbOrder tbOrder =new TbOrder();
                    tbOrder.setId(msg);
                    tbOrder.setOrderStatus("1");
                    tbOrder.setPayStatus("1");
                    try {
                        tbOrderMapper.updateTbOrder(tbOrder);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            channel.basicConsume(queueName,true,consumer);
            System.out.println("启动监听状态端 ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = RabbitMQConfig.XC_LEARNING_FINISHADDCHOOSECOURSE)
    public void reviceFinishGoods(Map<String , Object> data){

        System.out.println("返回的状态是："+data.get("status"));
        //验证
        //删除任务表中的数据
    }

}
