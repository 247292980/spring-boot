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
            //队列持久化，在RabbitMQ重启保证队列不会丢失
            boolean durable = true;
            boolean auto_delete = false;
//            public com.rabbitmq.client.AMQP.Queue.DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments) throws IOException
            channel.queueDeclare(QUEUE_NAME, durable, false, auto_delete, null);
            String message = "Hello RabbitMQ";
            //发送消息到队列中
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            log.info("Producer Send message={}", message);
            //关闭通道和连接
            channel.close();
        }

    }
}
