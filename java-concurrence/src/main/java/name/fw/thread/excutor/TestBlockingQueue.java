package name.fw.thread.excutor;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * <table>
 * <tr>
 * <td >&nbsp;</td>
 * <td >抛出异常</td>
 * <td >返回特殊值</td>
 * <td >阻塞</td>
 * <td >超时</td>
 * </tr>
 * <tr>
 * <td >入队</td>
 * <td >add(e)</td>
 * <td >offer(e)</td>
 * <td >put(e)&nbsp;</td>
 * <td >offer(e, time, unit)</td>
 * </tr>
 * <tr>
 * <td >出队</td>
 * <td >remove()</td>
 * <td >poll()</td>
 * <td >take()</td>
 * <td >poll(time, unit)</td>
 * </tr>
 * <tr>
 * <td >检查</td>
 * <td >element()</td>
 * <td >peek()</td>
 * <td >&nbsp;</td>
 * <td >&nbsp;</td>
 * </tr>
 * </table>
 *
 * <p>
 * 异常：是指当阻塞队列满时候，再往队列里插入元素，会抛出IllegalStateException("Queue full")异常。当队列为空时，
 * 从队列里获取元素时会抛出NoSuchElementException异常 。
 *
 * <li>返回特殊值：插入方法会返回是否成功，成功则返回true。移除方法，则是从队列里拿出一个元素，如果没有则返回null
 * <li>一直阻塞：当阻塞队列满时，如果生产者线程往队列里put元素，队列会一直阻塞生产者线程，直到拿到数据，或者响应中断退出。当队列空时，消费者线程试图从队列里take元素，队列也会阻塞消费者线程，直到队列可用。
 * <li>超时退出：当阻塞队列满时，队列会阻塞生产者线程一段时间，如果超过一定的时间，生产者线程就会退出
 */
public class TestBlockingQueue {

    private BlockingQueue<Integer> container = new ArrayBlockingQueue<>(10);

    @Test
    public void put(){
        try {
            container.put(1);
            container.put(2);
            container.put(3);
            container.put(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
