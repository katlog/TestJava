package org.person.dfw.nio.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by fw on 2019/5/6
 */
public class Test{
    public static void main(String[] args) {

        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(true);

            ServerSocket serverSocket = serverSocketChannel.socket();

            Selector open = Selector.open();
            serverSocketChannel.register(open, SelectionKey.OP_WRITE);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
