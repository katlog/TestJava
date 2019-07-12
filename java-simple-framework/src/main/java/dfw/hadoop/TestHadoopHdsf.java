package dfw.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.junit.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

/**
 * Created by fw on 2019/6/10
 */
public class TestHadoopHdsf {

    public static final String HDFS_HOST_PATH = "hdfs://host/path";

    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    /** 让Java程序能识别Hadoop的hdfs URL方案还需要一些额外的工作。
     * 这里采用的方法是通过FsUrlStreamHandlerFactory实例调用java.net.URL对象的setURLStreamHandlerFactory方法
     * 每个Java虚拟机只能调用一次这个方法，因此通常在静态方法中调用
     * */
    @Test
    public void read(){
        InputStream in = null;
        try {
            in = new URL(HDFS_HOST_PATH).openStream();
            IOUtils.copyBytes(in, System.out, 4096, false);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(in);
        }
    }

    /** 用FileSystemAPI来打开一个文件的输入流 */
    @Test
    public void readByFileSystemApi() throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(HDFS_HOST_PATH), conf);
        FSDataInputStream in = null;
        try {
            in = fs.open(new Path(HDFS_HOST_PATH));
            IOUtils.copyBytes(in, System.out, 4096, false);
            // go back to the start of the file
            in.seek(0);
            IOUtils.copyBytes(in, System.out, 4096, false);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(in);
        }
    }

    @Test
    public void write() throws IOException {
        String localSrc = "";
        String dst = "";
        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));
        Configuration conf = new Configuration();

        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        OutputStream out = fs.create(new Path(dst), new Progressable() {
            public void progress() {
                System.out.print(".");
            }
        });
        IOUtils.copyBytes(in, out, 4096, true);
    }

    @Test
    public void mkdirs() throws IOException {
        Configuration conf = new Configuration();

        FileSystem fs = FileSystem.get(URI.create(HDFS_HOST_PATH), conf);
        fs.mkdirs(new Path(URI.create(HDFS_HOST_PATH)));
    }

    @Test
    public void fileStatusForFile() throws IOException {

        Configuration conf = new Configuration();
        if (System.getProperty("test.build.data") == null) {
            System.setProperty("test.build.data", "/tmp");
        }
        // MiniDFSCluster cluster = new MiniDFSCluster.Builder(conf).build();
        // FileSystem  fs = cluster.getFileSystem();
        FileSystem  fs = null;
        OutputStream out = fs.create(new Path("/dir/file"));
        out.write("content".getBytes("UTF-8"));
        out.close();

        Path file = new Path("/dir/file");
        FileStatus stat = fs.getFileStatus(file);

        assertThat(stat.getPath().toUri().getPath(), is("/dir/file"));
        assertThat(stat.isDirectory(), is(false));
        assertThat(stat.getLen(), is(7L));
        assertThat(stat.getModificationTime(),is(lessThanOrEqualTo(System.currentTimeMillis())));
        assertThat(stat.getReplication(), is((short) 1));
        assertThat(stat.getBlockSize(), is(128 * 1024 * 1024L));
        assertThat(stat.getOwner(), is(System.getProperty("user.name")));
        assertThat(stat.getGroup(), is("supergroup"));
        assertThat(stat.getPermission().toString(), is("rw-r--r--"));
    }


    @Test
    public void delete() throws IOException {
        Configuration conf = new Configuration();

        FileSystem fs = FileSystem.get(URI.create(HDFS_HOST_PATH), conf);
        fs.delete(new Path(URI.create(HDFS_HOST_PATH)),true);
    }
}
