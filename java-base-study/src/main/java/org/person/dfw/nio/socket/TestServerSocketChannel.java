package org.person.dfw.nio.socket;

/**
 * @moudle: TestServerSocketChannel
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年10月20日 下午2:47:13
 *
 */
public class TestServerSocketChannel {

//	public static void main(String args[]) throws Exception {
//		int ports[] = { 8000, 8001, 8002, 8003, 8005, 8006 }; 	// 表示五个监听端口
//		Selector selector = Selector.open(); 					// 通过open()方法找到Selector
//		for (int i = 0; i < ports.length; i++) {
//			ServerSocketChannel initSer = ServerSocketChannel.open();// 打开服务器的通道
//			initSer.configureBlocking(false); 					// 服务器配置为非阻塞
//			ServerSocket initSock = initSer.socket();
//			InetSocketAddress address = new InetSocketAddress(ports[i]);// 实例化绑定地址
//			initSock.bind(address); 							// 进行服务的绑定
//			initSer.register(selector, SelectionKey.OP_ACCEPT); // 等待连接
//			System.out.println("服务器运行，在" + ports[i] + "端口监听。");
//		}
//
//		// 要接收全部生成的key，并通过连接进行判断是否获取客户端的输出
//		int keysAdd = 0;
//		while ((keysAdd = selector.select()) > 0) { 			// 选择一组键，并且相应的通道已经准备就绪
//			Set<SelectionKey> selectedKeys = selector.selectedKeys();// 取出全部生成的key
//			Iterator<SelectionKey> iter = selectedKeys.iterator();
//			while (iter.hasNext()) {
//				SelectionKey key = iter.next(); 				// 取出每一个key
//				if (key.isAcceptable()) {
//					ServerSocketChannel server = (ServerSocketChannel) key.channel();
//					SocketChannel client = server.accept(); 	// 接收新连接
//					client.configureBlocking(false);			// 配置为非阻塞
//					ByteBuffer outBuf = ByteBuffer.allocateDirect(1024);
//					outBuf.put(("当前的时间为：" + new Date()).getBytes()); // 向缓冲区中设置内容
//					outBuf.flip();
//					client.write(outBuf); 						// 输出内容
//					client.close();								// 关闭
//				}
//			}
//			selectedKeys.clear(); 								// 清楚全部的key
//		}
//	}
//
//	protected Selector getSelector(int port) throws IOException {
//		Selector sel = Selector.open();				// 创建Selector对象
//		// 创建可选择通道，并配置为非阻塞模式
//		ServerSocketChannel server = ServerSocketChannel.open();
//		server.configureBlocking(false);
//		// 绑定通道到指定端口
//		ServerSocket socket = server.socket();
//		InetSocketAddress address = new InetSocketAddress(port);
//		socket.bind(address);
//		// 向Selector中注册感兴趣的事件
//		server.register(sel, SelectionKey.OP_ACCEPT);
//		return sel;
//	}
//
//	protected Selector selector;
//	protected ByteBuffer buffer;
//	public void listen(Selector selector, int port) {
//		System.out.println("listen on " + port);
//		try {
//			while (true) {
//				selector.select();		// 该调用会阻塞，直到至少有一个事件发生
//				Set<SelectionKey> keys = selector.selectedKeys();
//				Iterator<SelectionKey> iter = keys.iterator();
//				while (iter.hasNext()) {
//					SelectionKey key = (SelectionKey) iter.next();
//					iter.remove();
//					process(key);
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	protected void process(SelectionKey key) throws IOException {
//		// 接收请求
//		if (key.isAcceptable()) {
//			ServerSocketChannel server = (ServerSocketChannel) key.channel();
//			SocketChannel channel = server.accept();
//			channel.configureBlocking(false);
//			channel.register(selector, SelectionKey.OP_READ);
//		}
//		// 读信息
//		else if (key.isReadable()) {
//			SocketChannel channel = (SocketChannel) key.channel();
//			int count = channel.read(buffer);
//			if (count > 0) {
//				buffer.flip();
//				CharBuffer charBuffer = decoder.decode(buffer);
//				name = charBuffer.toString();
//				SelectionKey sKey = channel.register(selector, SelectionKey.OP_WRITE);
//				sKey.attach(name);
//			} else {
//				channel.close();
//			}
//			buffer.clear();
//		}
//		// 写事件
//		else if (key.isWritable()) {
//			SocketChannel channel = (SocketChannel) key.channel();
//			String name = (String) key.attachment();
//			ByteBuffer block = encoder.encode(CharBuffer.wrap("Hello " + name));
//			if (block != null) {
//				channel.write(block);
//			} else {
//				channel.close();
//			}
//		}
//	}
}
