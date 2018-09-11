package com.lgp.zk.watchdemo;

/**
 * @AUTHOR lgp
 * @DATE 2018/9/11 9:24
 * @DESCRIPTION 开起监视者 datawatcher后开起更新者 dataupdater
 **/

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.UUID;

public class DataUpdater implements Watcher {
    private static String hostPort = "localhost:2181";
    private static String zooDataPath = "/MyCreate";
    ZooKeeper zk;

    public DataUpdater() throws IOException {
        try {
            zk = new ZooKeeper(hostPort, 2000, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // updates the znode path /MyConfig every 5 seconds with a new UUID string.
    public void run() throws Exception {
        while (true) {
            String uuid = UUID.randomUUID().toString();
            byte zoo_data[] = uuid.getBytes();
            zk.setData(zooDataPath, zoo_data, -1);
            Thread.sleep(5000); // Sleep for 5 secs
        }
    }

    public static void main(String[] args) throws Exception {
        DataUpdater dataUpdater = new DataUpdater();
        dataUpdater.run();
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.printf("\nEvent Received: %s", event.toString());
    }
}


