package com.lgp.zk.watchdemo;

/**
 * @AUTHOR lgp
 * @DATE 2018/9/10 17:29
 * @DESCRIPTION 监视者监听demo
 **/

import org.apache.zookeeper.*;

public class DataWatcher implements Watcher, Runnable {
    private static String hostPort = "localhost:2181";
    private static String zooDataPath = "/MyCreate";
    byte zoo_data[] = null;
    ZooKeeper zk;

    public DataWatcher() {
        try {
            zk = new ZooKeeper(hostPort, 2000, this);
            if (zk != null) {
                //Create the znode if it doesn't exist, with the following code:
                if (zk.exists(zooDataPath, this) == null) {
                    zk.create(zooDataPath, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printData() throws InterruptedException, KeeperException {
        zoo_data = zk.getData(zooDataPath, this, null);
        String zString = new String(zoo_data);
        // The following code prints the current content of the znode to the console:
        System.out.printf("\nCurrent Data @ ZK Path %s: %s", zooDataPath, zString);
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.printf("\nEvent Received: %s", event.toString());
        //We will process only events of type NodeDataChanged
        if (event.getType() == Event.EventType.NodeDataChanged) {
            try {
                printData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException, KeeperException {
        DataWatcher dataWatcher = new DataWatcher();
        dataWatcher.printData();
        dataWatcher.run();
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                while (true) {
                    wait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}