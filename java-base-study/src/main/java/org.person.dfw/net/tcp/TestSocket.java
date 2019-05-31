package org.person.dfw.net.tcp;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by fw on 2019/2/25
 */
public class TestSocket {

    @Test
    /** 服务器向客户端发送信息 */
    public void serverSendMsg2Client() throws IOException, InterruptedException {

        ServerSocket server = new ServerSocket(1000);
        Socket serverClient = server.accept();

        PrintStream out = new PrintStream(serverClient.getOutputStream());
        out.println("要发给客户端的各种信息");
        out.close();

        System.out.println("发送完毕");

        serverClient.close();
        server.close();

        Thread.sleep(5000);
    }

    @Test
    /** 客户端接收服务端信息 */
    public void clientReceiveMsgFromServer() throws IOException {
        Socket client = new Socket("localhost", 1000);
        BufferedReader buf = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
        System.out.print(buf.readLine());
    }
}
