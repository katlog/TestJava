package name.katlog.util.web;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


/**
 * @moudle: IpUtil 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年10月16日 下午6:18:08
 *
 */
public class IpUtil {
	
	
	  public static String getOutNetIp()  {  
	        String localip = null;// 本地IP，如果没有配置外网IP则返回它  
	        String netip = null;// 外网IP  
	  
	        Enumeration<NetworkInterface> netInterfaces;
			try {
				netInterfaces = NetworkInterface.getNetworkInterfaces();
				InetAddress ip = null;  
				boolean finded = false;// 是否找到外网IP  
				while (netInterfaces.hasMoreElements() && !finded) {  
					NetworkInterface ni = netInterfaces.nextElement();  
					Enumeration<InetAddress> address = ni.getInetAddresses();  
					while (address.hasMoreElements()) {  
						ip = address.nextElement();  
						if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP  
							netip = ip.getHostAddress();  
							finded = true;  
							break;  
						} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()  
								&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP  
							localip = ip.getHostAddress();  
						}  
					}  
				}  
			} catch (SocketException e) {
				e.printStackTrace();
			}  
	  
	        if (netip != null && !"".equals(netip)) {  
	            return netip;  
	        } else {  
	            return localip;  
	        }  
	    }  
}
