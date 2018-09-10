package com.lgp.rabbitmq.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @AUTHOR lgp
 * @DATE 2018/9/7 17:27
 * @DESCRIPTION rabbitmq的消息生产者
 **/
public class Producer {
    public final static String QUEUE_NAME = "rabbitMQ.lgp";
    public static final Logger log = LoggerFactory.getLogger(Producer.class);

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost("localhost");
        factory.setUsername("liangguiping");
        factory.setPassword("123456789");
        factory.setPort(5672);
        //创建一个新的连接
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            //创建一个通道
            //  声明一个队列
//            public com.rabbitmq.client.AMQP.Queue.DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments) throws IOException
            boolean durable = true;
            boolean exclusive = false;
            boolean auto_delete = false;
//            Map<String, Object> arguments = new HashMap<String, Object>();
//            // 统一设置队列中的所有消息的过期时间
//            arguments.put("x-message-ttl", 30000);
//            // 设置超过多少毫秒没有消费者来访问队列，就删除队列的时间
//            arguments.put("x-expires", 20000);
//            // 设置队列的最新的N条消息，如果超过N条，前面的消息将从队列中移除掉
//            arguments.put("x-max-length", 4);
//            // 设置队列的内容的最大空间，超过该阈值就删除之前的消息
//            arguments.put("x-max-length-bytes", 1024);
//            // 将删除的消息推送到指定的交换机，一般x-dead-letter-exchange和x-dead-letter-routing-key需要同时设置
//            arguments.put("x-dead-letter-exchange", "exchange.dead");
//            // 将删除的消息推送到指定的交换机对应的路由键
//            arguments.put("x-dead-letter-routing-key", "routingkey.dead");
//            // 设置消息的优先级，优先级大的优先被消费
//            arguments.put("x-max-priority", 10);
            channel.queueDeclare(QUEUE_NAME, durable, exclusive, auto_delete, null);
            String message = "Hello RabbitMQ";
            //发送消息到队列中
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            log.info("Producer Send message={}", message);
            //关闭通道和连接
            channel.close();
        }

    }
}
