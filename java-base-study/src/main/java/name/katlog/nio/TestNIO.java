package name.katlog.nio;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.nio.channels.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;
/**
 *通道和缓冲区（Channels and Buffers）
 *      可从Channel把数据写到Buffer中，也可以把数据从Buffer写入到Channel
 *      Channel种类
 *          FileChannel
 *          DatagramChannel
 *          SocketChannel
 *          ServerSocketChannel
 *      Buffer种类：
 *          ByteBuffer
 *          CharBuffer
 *          DoubleBuffer
 *          FloatBuffer
 *          IntBuffer
 *          LongBuffer
 *          ShortBuffer
 *          MappedBytesBuffer  【用于和内存映射的文件】
 */
public class TestNIO {
	
	ByteBuffer byteBuffer = ByteBuffer.allocate(10);
	
	@Test
	public void buffer(){
	    
	    /**
	     * 静态构造方法：allocate()分配缓冲区空间
	     */
	    IntBuffer intBuffer = IntBuffer.allocate(10);
		System.out.println("写入数据前,"+"position:"+intBuffer.position()+"、limit："+intBuffer.limit()+"、capacity:"+intBuffer.capacity());
		/**
		 * 常用方法：put()写入数据
		 */
		intBuffer.put(1);
		intBuffer.put(new int[]{2,3,4});
		System.out.println("写入数据后,"+"position:"+intBuffer.position()+"、limit："+intBuffer.limit()+"、capacity:"+intBuffer.capacity());
		/**
		 * Buffer filp()重设缓冲区，在写之前调用，改变缓冲区指针
		 *     反转此缓冲区。先将限制设置为当前位置，后将位置设置为 0。若已定义了标记，则丢弃该标记。
		 *     在一系列通道读取或放置操作之后，调用此方法为一系列通道写入或相对获取操作做好准备
		 * */
		intBuffer.flip();
		System.out.println("flip后,"+"position:"+intBuffer.position()+"、limit："+intBuffer.limit()+"、capacity:"+intBuffer.capacity());
		/**hasRemianing() 判断当前位置position和限制limit之间是否内容*/
		System.out.print("缓冲区中的内容：");
		while(intBuffer.hasRemaining()) {
		    /**get()获取数据*/
			System.out.print(intBuffer.get());
			System.out.print(intBuffer.hasRemaining()?"":"\n");;
		}
		
		/**
		 * Buffer.rewind()方法将position置为0，这样我们可以重复读取buffer中的数据。limit保持不变。
		 * 
		 * clear() and compact()
		 *        一旦从buffer中读取完数据，需要复用buffer为下次写数据做准备。只需要调用clear或compact方法。
		 *        clear方法会重置position为0，limit为capacity，也就是整个Buffer清空。实际上Buffer中数据并没有清空，只是把标记为修改了
		 *        
		 *        如果Buffer还有一些数据没有读取完，调用clear就会导致这部分数据被“遗忘”，因为我们没有标记这部分数据未读
		 *        针对这种情况，若需保留未读数据，则可用compact。 因此compact和clear的区别就在于对未读数据的处理，是保留这部分数据还是一起清空
		 *  mark() and reset()      
		 *        通过mark方法可以标记当前的position，通过reset来恢复mark的位置
		 */
	}
	/**
	 * 子缓冲区   拿来干啥用呢？
	 */
	@Test public void slice(){
	    IntBuffer intBuffer = IntBuffer.allocate(10);
	    IntBuffer subBuffer = null;
	    for (int i = 0; i < 10; i++) 
	        intBuffer.put(2*i+1);
	    /**posiont(int p)将主缓冲区指针设置到第3个元素上*/
	    intBuffer.position(2);
	    /**limit(int l) 将主缓冲区的限制limit设置为6*/
	    intBuffer.limit(6);
	    /**slice() 根据缓冲区的position和limit开辟子缓冲区*/
	    subBuffer = intBuffer.slice();
	    /**
	     * 因为是共享数据，主缓冲区和子缓冲区的内容是一样的
	     *     两者的区别主要，在子缓冲区的范围是主缓冲区的一部分
	     */
	    System.out.println("修改前：");
	    System.out.println("主缓冲区："+Arrays.toString(intBuffer.array()));
	    System.out.println("子缓冲区:"+Arrays.toString(subBuffer.array()));
	    System.out.println("修改后：");
	    for (int i = 0; i < subBuffer.capacity(); i++) {
            subBuffer.put(subBuffer.get(i)-1);
        }
	    System.out.println("主缓冲区："+Arrays.toString(intBuffer.array()));
        System.out.println("子缓冲区:"+Arrays.toString(subBuffer.array()));
	}
	
	/**
	 * 创建只读缓冲区
	 */
	@Test(expected=ReadOnlyBufferException.class) public void readOnly(){
	    IntBuffer intBuffer = IntBuffer.allocate(10);
        for (int i = 0; i < 10; i++) 
            intBuffer.put(2*i+1);
        IntBuffer intBuffer2 = intBuffer.asReadOnlyBuffer();
        //会抛出ReadOnlyBufferException异常
        intBuffer2.put(1);
	}
	
	/**
	 * 仅ByteBuffer可创建直接缓冲区，这样jvm将尽最大努力直接对其操作执行本机的IO操作，
	 *     即在每次调用基础操作系统的一个本机IO操作前/后，jvm都会尽量避免将缓冲区的内容复制到中间缓冲区(或从中间缓冲区复制内容)
	 */
	@Test public void allocateDirect(){
	    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10);
        for (int i = 0; i < 10; i++) 
            byteBuffer.put((byte)(2*i+1));
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            System.out.print(byteBuffer.get()+"、");
        }
        //不知道为什么这个 的array没法执行
        System.out.println("直接缓冲区："+Arrays.toString(byteBuffer.array()));
	}
	
	@Test
	public void testChannel(){
		try {
			FileInputStream fis = new FileInputStream("basic/org/person/dfw/nio/channel.apk");
			FileOutputStream fos = new FileOutputStream("basic/org/person/dfw/nio/channel_copy.apk");
			FileChannel in = fis.getChannel();
			FileChannel out = fos.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
			int temp = 0;
			while ((temp = in.read(buffer))!=-1) {
				buffer.flip();
				out.write(buffer);
				buffer.clear();
			}
			out.close();
			in.close();
			fos.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testMappedByteBuffer() throws IOException{
		FileInputStream fis = new FileInputStream("D:\\processor.txt");
		FileChannel channel = fis.getChannel();
		MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0, new File("D:\\processor.txt").length());
		byte[] data = new byte[(int) new File("D:\\processor.txt").length()];
		
		int foot = 0 ;
		while (map.hasRemaining()) {
			data[foot++] = map.get();
		}
		System.out.println(new String(data));
		channel.close();fis.close();
	}
	
	/*
	@Test
	public void testFileLock() throws IOException, InterruptedException{
//		FileInputStream fis = new FileInputStream("D:\\target.txt");
//		FileChannel channel = fis.getChannel();
		FileOutputStream fos = new FileOutputStream("D:\\target.txt");
		FileChannel channel = fos.getChannel();
		FileLock lock = channel.tryLock();
		if (lock!=null) {
			Thread.sleep(3000);
			lock.release();
		}
		
	}*/
	
	@Test
	public void testSelector1(){
		//Selector的作用就是用来轮询每个注册的Channel，一旦发现Channel有注册的事件发生，便获取事件然后进行处理
		//用单线程处理一个Selector，然后通过Selector.select()方法来获取到达事件，在获取了到达事件之后，就可以逐个地对这些事件进行响应处理
		//能够检测多个注册的通道上是否有事件发生，若有便获取事件然后针对每个事件进行相应的响应处理。这样一来，只用一个单线程就可以管理多个通道，
		//也就是管理多个连接。这样使得只有在连接真正有读写事件发生时，才会调用函数来进行读写，就大大地减少了系统开销，
		//并且不必为每个连接都创建一个线程，不用去维护多个线程，并且避免了多线程之间的上下文切换导致的开销
		
		try {
			Selector selector = Selector.open();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	@Test
	public void socketServer() throws IOException, InterruptedException {
		ServerSocketChannel socketChannel = ServerSocketChannel.open();
		socketChannel.configureBlocking(false);
		socketChannel.bind(new InetSocketAddress(18000));

		Selector selector = Selector.open();
		socketChannel.register(selector, SelectionKey.OP_ACCEPT);

		new Thread(() -> {
			while (true) {
				try {
					selector.select();
					Set<SelectionKey> selectionKeys = selector.selectedKeys();
					Iterator<SelectionKey> iterator = selectionKeys.iterator();
					while (iterator.hasNext()) {
						SelectionKey next = iterator.next();
						if (next.isAcceptable()) {
							ServerSocketChannel  channel = (ServerSocketChannel) next.channel();
							ByteBuffer byteBuffer = ByteBuffer.allocate(1204);
							SocketChannel socketChannel1 = channel.accept();
							socketChannel1.read(byteBuffer);
							byteBuffer.flip();
							System.out.println("new String(byteBuffer.array()) = " + new String(byteBuffer.array()));
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

		// new Thread(() -> {
		// 	try {
        //
		// 		Socket socket = new Socket(InetAddress.getByName("localhost"), 18000);
		// 		OutputStream outputStream = socket.getOutputStream();
        //
		// 		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        //
		// 		writer.write("ahihsioahdioahsdhi\n");
		// 		// writer.flush();
		// 		// writer.write("dasfafasfd\n");
		// 		// writer.flush();
		// 		writer.close();
		// 	} catch (IOException e) {
		// 		e.printStackTrace();
		// 	}
		// }).start();

		new Thread(() -> {
			try {
				SocketChannel socketChannel1 = SocketChannel.open(new InetSocketAddress("localhost", 18000));
				if (socketChannel1.isConnected()) {
					System.out.println("socketChannel1 = " + socketChannel1);

					socketChannel1.write(ByteBuffer.wrap("SADASDADSAS".getBytes()));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();


		Thread.sleep(10000);
	}


//	int port = 8080;
//	protected Selector getSelector() throws IOException {
//		Selector open = Selector.open();
//		
//		//创建通道，非阻塞模式的socket通道
//		ServerSocketChannel socketChannel = ServerSocketChannel.open();
//		socketChannel.configureBlocking(false);
//		
//		//socket通道创建一个socket，绑定一个ip地址
//		ServerSocket socket = socketChannel.socket();
//		InetSocketAddress iAddress = new InetSocketAddress(port);
//		socket.bind(iAddress);
//		
//		//向selector中注册感兴趣的时间
//		SelectionKey register = socketChannel.register(open, SelectionKey.OP_ACCEPT);
//		
//		return open;
//		
//	}
//
//	public void listen() throws IOException{
//		while (true) {
//			Selector selector = getSelector();
//			selector.select();
//			Set<SelectionKey> selectedKeys = selector.selectedKeys();
////			selectedKeys.
//		
//		}
//		
//	}
//	
//	protected void process(SelectionKey key) throws IOException {
//		//接受情况
//		if (key.isAcceptable()) {
//			ServerSocketChannel channel = (ServerSocketChannel) key.channel();
//			SocketChannel accept = channel.accept();
//			
//		}
//	}
	/*@Test
	public void testSelector2() throws IOException{
		int[] ports = new int[]{8000,8001,8002,8003,8004};
		
		Selector selector = Selector.open();
		
		for (int i : ports) {
			InetSocketAddress inetSocketAddress = new InetSocketAddress(i);
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.bind(inetSocketAddress);
			ServerSocket serverSocket = serverSocketChannel.socket();
			
			System.out.println("服务端在"+i+"口监听");
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		}
		
		
		while (selector.select()>0) {
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			for (SelectionKey selectionKey : selectedKeys) {
				
				if (selectionKey.isAcceptable()) {
					ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
					SocketChannel socketChannel = serverSocketChannel.accept();
					socketChannel.configureBlocking(false);
					byteBuffer.put(("当前时间为"+new Date()).getBytes());
					byteBuffer.flip();
					socketChannel.write(byteBuffer);
					socketChannel.close();
					
				}
				
			}
		}
		
	}
	
	@Test
	public void socket() throws IOException{
		ServerSocket serverSocket = new ServerSocket(8001);
		while (true) {
			Socket socket = serverSocket.accept();
			if (socket.isConnected()) {
				OutputStream outputStream = socket.getOutputStream();
				outputStream.write(new byte[]{12,12});
			}
		}
	}
	
	@Test
	public void testServerSocketChannel(){
		
	}
	
	
	public static void main(String[] args) {
		ArraysUtils.formatPrintMap(ReflectUtils.getMethodNoneParam(IntBuffer.allocate(10)));
	}*/
	
	
	
}
