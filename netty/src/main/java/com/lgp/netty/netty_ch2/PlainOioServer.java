package com.lgp.netty.netty_ch2;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.*;

/**
 * @AUTHOR lgp
 * @DESCRIPTION 原生的oio 服务器，oio其实就是bio了，同步阻塞io
 **/
public class PlainOioServer {
    public void serve(int port) throws IOException {
//        绑定服务器到指定的端口
        final ServerSocket socket = new ServerSocket(port);
        try {
            while (true) {
                //2 创建一个连接
                final Socket clientSocket = socket.accept();
                System.out.println("Accepted connection from " + clientSocket);
                /*通过线程池创建线程，远好过直接创建线程，因为减小了线程创建的资源和调度时间*/
                ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("PlainOioServer-pool").build();
                ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());
                //3 创建一个新的线程来处理连接
                singleThreadPool.execute(() -> {
                    OutputStream out;
                    try {
                        out = clientSocket.getOutputStream();
                        //4 将消息传送给客户端
                        out.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));
                        out.flush();
                        //5 一旦消息被写入和刷新时（即处理完请求） 就关闭连接，看起来像是http，但这是tcp的连接
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        try {
                            clientSocket.close();
                        } catch (IOException ex) {
                            // ignore on close
                        }
                    }
                });
                singleThreadPool.shutdown();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
