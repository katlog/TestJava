package io.github.viscent.mtia.ch2;

import lombok.Data;

/**
 * Created by fw on 2020/1/7
 */
public class AtomicityExample {
    private HostInfo hostInfo;

    public void updateHostInfo(String ip, int port) {
        // 以下操作不是原子操作
        hostInfo.setIp(ip); // 语句①
        hostInfo.setPort(port); // 语句②
    }
    public void connectToHost() {
        String ip = hostInfo.getIp();
        int port = hostInfo.getPort();
        connectToHost(ip, port);
    }

    private void connectToHost(String ip, int port) {
        // ...
    }

    @Data
    public static class HostInfo {
        private String ip;
        private int port;
    }
}
