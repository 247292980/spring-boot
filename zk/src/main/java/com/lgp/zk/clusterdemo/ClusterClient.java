package com.lgp.zk.clusterdemo;

/**
 * @AUTHOR lgp
 * @DATE 2018/9/11 9:51
 * @DESCRIPTION 集群监听客户端，客户端结束，zk删除
 **/

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

import java.lang.management.ManagementFactory;

public class ClusterClient implements Watcher, Runnable {
    private static String membershipRoot = "/Members";
    ZooKeeper zk;

    public ClusterClient(String hostPort, Long pid) {
        String processId = pid.toString();
        try {
            zk = new ZooKeeper(hostPort, 2000, this);
            if (zk != null) {
                zk.create(membershipRoot + '/' + processId, processId.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public synchronized void close() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.printf("\nEvent Received: %s", event.toString());
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                while (true) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            this.close();
        }
    }

    public static void main(String[] args) {
        String hostPort = "localhost:2181";
        //Get the process id
        String name = ManagementFactory.getRuntimeMXBean().getName();
        int index = name.indexOf('@');
        Long processId = Long.parseLong(name.substring(0, index));
        new ClusterClient(hostPort, processId).run();
    }
}
