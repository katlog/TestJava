package org.person.dfw.util;

import com.google.common.base.Preconditions;
import org.apache.commons.lang.ArrayUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * Created by fw on 2019/7/2
 */
public class SnowFlakeIdWork {
    private static volatile SnowFlakeIdWork instance = null;
    private final long workerId;
    static final long TWEPOCH = 1320681600000L;
    private long sequence = 0L;
    static final long workerIdBits = 10L;
    static final long MAX_WORKER_ID = 1023L;
    static final long sequenceBits = 12L;
    static final long WORKER_ID_SHIFT = 12L;
    static final long TIMESTAMP_LEFT_SHIFT = 22L;
    static final long SEQUENCE_MASK = 4095L;
    private long lastTimestamp = -1L;

    public static SnowFlakeIdWork getInstance() {
        return getInstance(getIpSum());
    }

    public static long getIpSum() {
        long id = 0L;
        if (instance == null) {
            String ip = AddressHelper.getLocalhostIPV4();
            if (!ip.equals("ipv4_error")) {
                String[] strs = ip.split("\\.");
                String[] var4 = strs;
                int var5 = strs.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    String s = var4[var6];
                    id += (long)Integer.parseInt(s);
                }
            } else {
                id = System.currentTimeMillis() % 1024L;
            }
        }

        return id;
    }

    public static SnowFlakeIdWork getInstance(long workerId) {
        if (instance == null) {
            Class var2 = SnowFlakeIdWork.class;
            synchronized(SnowFlakeIdWork.class) {
                if (instance == null) {
                    instance = new SnowFlakeIdWork(workerId);
                }
            }
        }

        return instance;
    }

    private SnowFlakeIdWork(long workerId) {
        Preconditions.checkArgument(workerId < MAX_WORKER_ID && workerId >= 0L, "worker Id was %s but it could not be greater than %s or less than 0", new Object[]{workerId, 1023L});
        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) {
            this.sequence = this.sequence + 1L & SEQUENCE_MASK;
            if (this.sequence == 0L) {
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0L;
        }

        if (timestamp < this.lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
        } else {
            this.lastTimestamp = timestamp;
            return timestamp - TWEPOCH << TIMESTAMP_LEFT_SHIFT | this.workerId << WORKER_ID_SHIFT | this.sequence;
        }
    }

    public static long[] split(long id){
        long timeDiff = id >> TIMESTAMP_LEFT_SHIFT;
        long workId = id >> WORKER_ID_SHIFT & MAX_WORKER_ID;
        long sequence = id & SEQUENCE_MASK;
        return new long[]{timeDiff, workId, sequence};
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp;
        for(timestamp = this.timeGen(); timestamp <= lastTimestamp; timestamp = this.timeGen()) {
        }

        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

  static class AddressHelper {
        public static final String IPV4_ERROR = "ipv4_error";
        private static String ipv4;

        public AddressHelper() {
        }

        public static String getLocalhostIPV4() {
            if (ipv4 == null) {
                Class var0 = AddressHelper.class;
                synchronized (AddressHelper.class) {
                    if (ipv4 == null) {
                        ipv4 = "ipv4_error";

                        try {
                            InetAddress[] var1 = getInetAddresses("e(\\w)+\\d");
                            int var2 = var1.length;

                            for (int var3 = 0; var3 < var2; ++var3) {
                                InetAddress inetAddress = var1[var3];
                                if (inetAddress instanceof Inet4Address) {
                                    ipv4 = inetAddress.getHostAddress();
                                    System.out.println("scanning ip ---> " + ipv4);
                                }

                                if (!ipv4.contains(":")) {
                                    break;
                                }
                            }
                        } catch (Exception var6) {
                            ;
                        }
                    }
                }
            }

            return ipv4;
        }

        public static InetAddress[] getInetAddresses(String regex) throws SocketException {
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            Iterator var2 = Collections.list(nets).iterator();

            NetworkInterface netint;
            do {
                if (!var2.hasNext()) {
                    return new InetAddress[0];
                }

                netint = (NetworkInterface) var2.next();
            } while (!Pattern.matches(regex, netint.getDisplayName()));

            Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
            ArrayList<InetAddress> list = Collections.list(inetAddresses);
            InetAddress[] addrs = (InetAddress[]) list.toArray(new InetAddress[list.size()]);
            if (list.size() > 1 && ((InetAddress) list.get(0)).getHostAddress().contains(":")) {
                ArrayUtils.reverse(addrs);
            }

            return addrs;
        }
    }
}