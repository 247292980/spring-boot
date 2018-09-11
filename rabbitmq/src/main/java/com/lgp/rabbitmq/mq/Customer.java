package com.lgp.rabbitmq.mq;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @AUTHOR lgp
 * @DATE 2018/9/7 17:34
 * @DESCRIPTION 消费者
 **/
public class Customer {
    private final static String QUEUE_NAME = "rabbitMQ.lgp";
    public static final Logger log = LoggerFactory.getLogger(Customer.class);

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ地址
        factory.setHost("localhost");
        factory.setUsername("liangguiping");
        factory.setPassword("123456789");
        factory.setPort(5672);
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            //创建一个通道
            //声明要关注的队列
            //队列持久化，在RabbitMQ重启保证队列不会丢失
            boolean durable = true;
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            System.out.println("Customer Waiting Received messages");
            //DefaultConsumer类实现了Consumer接口，通过传入一个频道，
            // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    log.info("Customer Received message={}", message);
                }
            };
            //自动回复队列应答 -- RabbitMQ中的消息确认机制
            channel.basicConsume(QUEUE_NAME, true, consumer);
        }
    }
}
