package edu.xtu.androidbase.weaher.util;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by huilin on 2016/12/24.
 */

public class ThreadManager {

    public ThreadPollProxy poll;


    private ThreadManager() {

        poll = new ThreadPollProxy(5, 5, 500L);
    }

    public static ThreadManager getInstance(){
        return ThreadManagerHolder.INSTANCE;
    }

    private static class ThreadManagerHolder {
        public static ThreadManager INSTANCE = new ThreadManager();
    }


    public class ThreadPollProxy {
        private ThreadPoolExecutor poll;
        private int corePollSize;
        private int maxPollSize;
        private long keepTime;


        public ThreadPollProxy(int corePollSize, int maxPollSize, long keepTime) {
            this.corePollSize = corePollSize;
            this.maxPollSize = maxPollSize;
            this.keepTime = keepTime;
        }

        public void execute(Runnable runnable) {
            if (poll == null) {
                createThreadPoll();
            }
            poll.execute(runnable);
        }

        public void cancel(Runnable runnable) {
            if (poll != null && !poll.isShutdown() && !poll.isTerminated()) {
                poll.remove(runnable);
            }
        }

        private void createThreadPoll() {
            poll = new ThreadPoolExecutor(corePollSize, maxPollSize, keepTime, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(10));
        }

    }

}
