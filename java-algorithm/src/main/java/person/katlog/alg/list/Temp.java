package person.katlog.alg.list;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.charset.*;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class Temp {
    public static void main(String[] args) throws IOException {


        RandomAccessFile randomAccessFile = new RandomAccessFile ("filename", "r");
        // Set the file position
        randomAccessFile.seek (1000);
        // Create a channel from the file
        FileChannel fileChannel = randomAccessFile.getChannel( );
        // This will print "1000"
        System.out.println ("file pos: " + fileChannel.position( ));
        // Change the position using the RandomAccessFile object
        randomAccessFile.seek (500);
        // This will print "500"
        System.out.println ("file pos: " + fileChannel.position( ));
        // Change the position using the FileChannel object
        fileChannel.position (200);
        // This will print "200"
        System.out.println ("file pos: " + randomAccessFile.getFilePointer( ));
    }


    /**
     * □ Test nonblocking accept( ) using ServerSocketChannel.
     * □ Start this program, then "telnet localhost 1234" to
     * □ connect to it.
     */
    public static class ChannelAccept {

        public static final String GREETING = "Hello I must be going.\r\n";

        public static void main(String[] argv) throws Exception {
            int port = 1234; // default
            if (argv.length > 0) {
                port = Integer.parseInt(argv[0]);
            }

            ByteBuffer buffer = ByteBuffer.wrap(GREETING.getBytes());

            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(port));
            ssc.configureBlocking(false);

            while (true) {
                System.out.println("Waiting for connections");
                SocketChannel sc = ssc.accept();
                if (sc == null) {
                    // no connections, snooze a while
                    Thread.sleep (2000);
                } else {
                    System.out.println("Incoming connection from: "+
                            sc.socket().getRemoteSocketAddress());
                    buffer.rewind();
                    sc.write(buffer);
                    sc.close();
                }
            }
        }
    }

    public static class FileHole
    {
        public static void main (String [] argv) throws IOException
        {
            // Create a temp file, open for writing, and get  a FileChannel
            File temp = File.createTempFile ("holy", null);
            RandomAccessFile file = new RandomAccessFile (temp, "rw");
            FileChannel channel = file.getChannel( );

            // Create a working buffer
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect (100);
            putData (0, byteBuffer, channel);
            putData (5000000, byteBuffer, channel);
            putData (50000, byteBuffer, channel);

            // Size will report the largest position written, but
            // there are two holes in this file. This file will
            // not consume 5 MB on disk (unless the filesystem is
            // extremely brain-damaged)
            System.out.println ("Wrote temp file '" + temp.getPath( )
                    + "', size=" + channel.size( ));
            channel.close( );
            file.close( );
        }
        private static void putData (int position, ByteBuffer buffer, FileChannel channel)
                throws IOException
        {
            String string = "*<-- location " + position;
            buffer.clear( );
            buffer.put (string.getBytes ("US-ASCII"));
            buffer.flip( );
            channel.position (position);
            channel.write (buffer);
        }
    }


    /**
     □ Test locking with FileChannel.
     □ Run one copy of this code with arguments "-w /tmp/locktest.dat"
     □ and one or more copies with "-r /tmp/locktest.dat" to see the
     □ interactions of exclusive and shared locks. Note how too many
     □ readers can starve out the writer.
     □ Note: The filename you provide will be overwritten. Substitute
     □ an appropriate temp filename for your favorite OS.
     */
    public static class LockTest
    {
        private static final int SIZEOF_INT = 4;
        private static final int INDEX_START = 0;
        private static final int INDEX_COUNT = 10;
        private static final int INDEX_SIZE = INDEX_COUNT * SIZEOF_INT;
        private ByteBuffer buffer = ByteBuffer.allocate (INDEX_SIZE);
        private IntBuffer indexBuffer = buffer.asIntBuffer( );
        private Random rand = new Random( );

        public static void main(String[] argv) throws Exception {
            boolean writer = false;
            String filename;
            if (argv.length != 2) {
                System.out.println("Usage: [ -r | -w ] filename");
                return;
            }
            writer = argv[0].equals("-w");
            filename = argv[1];
            RandomAccessFile raf = new RandomAccessFile(filename, (writer) ? "rw" : "r");
            FileChannel fc = raf.getChannel();
            LockTest lockTest = new LockTest();
            if (writer) {
                lockTest.doUpdates(fc);
            } else {
                lockTest.doQueries(fc);
            }
        }
        // ----------------------------------------------------------------
        // Simulate a series of read-only queries while
        // holding a shared lock on the index area
        void doQueries(FileChannel fc) throws Exception {
            while (true) {
                println("trying for shared lock...");
                FileLock lock = fc.lock(INDEX_START, INDEX_SIZE, true);
                int reps = rand.nextInt(60) + 20;
                for (int i = 0; i < reps; i++) {
                    int n = rand.nextInt(INDEX_COUNT);
                    int position = INDEX_START + (n * SIZEOF_INT);
                    buffer.clear();
                    fc.read(buffer, position);
                    int value = indexBuffer.get(n);
                    println("Index entry " + n + "=" + value);
                    // Pretend to be doing some work
                    Thread.sleep (100);
                }
                lock.release();
                println("<sleeping>");
                Thread.sleep(rand.nextInt(3000) + 500);
            }
        }

        // Simulate a series of updates to the index area
        // while holding an exclusive lock
        void doUpdates(FileChannel fc) throws Exception {
            while (true) {
                println("trying for exclusive lock...");
                FileLock lock = fc.lock(INDEX_START, INDEX_SIZE, false);
                updateIndex(fc);
                lock.release();
                println("<sleeping>");
                Thread.sleep(rand.nextInt(2000) + 500);
            }
        }

        // Write new values to the index slots
        private int idxval = 1;

        private void updateIndex(FileChannel fc) throws Exception {
            // "indexBuffer" is an int view of "buffer"
            indexBuffer.clear();
            for (int i = 0; i < INDEX_COUNT; i++) {
                idxval++;
                println("Updating index " + i + "=" + idxval);
                indexBuffer.put(idxval);
                // Pretend that this is really hard work
                Thread.sleep (500);
            }
            // leaves position and limit correct for whole buffer
            buffer.clear( );
            fc.write(buffer, INDEX_START);
        }

        // ----------------------------------------------------------------
        private int lastLineLen = 0;

        // Specialized println that repaints the current line
        private void println(String msg) {
            System.out.print("\r ");
            System.out.print(msg);
            for (int i = msg.length(); i < lastLineLen; i++) {
                System.out.print(" ");
            }
            System.out.print("\r");
            System.out.flush();
            lastLineLen = msg.length();
        }
    }




}





