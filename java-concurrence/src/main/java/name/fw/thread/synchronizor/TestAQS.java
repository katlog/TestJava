package name.fw.thread.synchronizor;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by fw on 2020/5/19
 */
public class TestAQS {
    class OneLactch{
        private Sync sync = new Sync();
        public void signal() {
            sync.releaseShared(1);
        }
        public void await() throws InterruptedException {
            sync.acquireSharedInterruptibly(1);
        }

        class Sync extends AbstractQueuedSynchronizer{
            @Override
            protected int tryAcquireShared(int arg) {
                return getState() == 1 ? 1 : -1;
            }

            @Override
            protected boolean tryReleaseShared(int arg) {
                setState(1);
                return true;
            }
        }
    }
}
