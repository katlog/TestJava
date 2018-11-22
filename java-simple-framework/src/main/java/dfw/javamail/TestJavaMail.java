/**  
 * @Title: TestJavaMail.java
 * @Package: org.person.dfw.javamail
 * @Description: TODO
 * @author: chensl
 * @date: 2017年3月18日 下午3:32:02
 * @version: V1.0  
 */
package dfw.javamail;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @moudle: TestJavaMail
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2017年3月18日 下午3:32:02
 *
 */
public class TestJavaMail {
	public static void main(String[] args) throws AddressException, MessagingException {
	    Properties props = new Properties();  
        // 开启debug调试  
        props.setProperty("mail.debug", "true");  
        // 发送服务器需要身份验证  
        props.setProperty("mail.smtp.auth", "true");  
        // 设置邮件服务器主机名  
        props.setProperty("mail.host", "smtp.163.com");  
        // 发送邮件协议名称  
        props.setProperty("mail.transport.protocol", "smtp");  
          
        // 设置环境信息  
        Session session = Session.getInstance(props, new Authenticator() {  
            // 在session中设置账户信息，Transport发送邮件时会使用  
            protected PasswordAuthentication getPasswordAuthentication() {  
                return new PasswordAuthentication("java_mail_001", "javamail");  
            }  
        });  
          
        // 创建邮件对象  
        Message msg = new MimeMessage(session);  
        // 发件人  
        msg.setFrom(new InternetAddress("java_mail_001@163.com"));  
        // 多个收件人  
        msg.setRecipients(RecipientType.TO, InternetAddress.parse("java_mail_002@163.com,java_mail_003@163.com"));  
        // 抄送人  
        msg.setRecipient(RecipientType.CC, new InternetAddress("java_mail_001@163.com"));  
        // 暗送人  
        msg.setRecipient(RecipientType.BCC, new InternetAddress("java_mail_004@163.com"));  
          
        // 主题  
        msg.setSubject("中文主题");  
        // HTML内容  
        msg.setContent("<div align=\"center\">你好啊</div>", "text/html;charset=utf-8");  
          
        // 连接邮件服务器、发送邮件、关闭连接，全干了  
        Transport.send(msg); 
	}
}
