package discorddb.jsondb;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Thread Management Class<br>
 * Manages the thread pooling system that will be used to update json database files.
 */
class ThreadManagement {

    private static final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(8);

    // Configure ScheduledThreadPoolExecutor
    static {
        executor.setMaximumPoolSize(10);
    }

    /**
     * Configure the static {@link ScheduledThreadPoolExecutor} instance to match the queue of your application.<br>
     * You are responsible to know how much your server can handle.
     * @param corePoolSize set the minimum number of workers to keep alive without timing out.
     * @param maxPoolSize set the maximum number of workers to keep alive without timing out.
     */
    public static void configureExecutor(int corePoolSize, int maxPoolSize) {
        executor.setCorePoolSize(corePoolSize);
        executor.setMaximumPoolSize(maxPoolSize);
    }

    /**
     * Execute a Runnable inside the thread
     * @param runnable {@link Runnable} action
     */
    protected static void execute(Runnable runnable) {
        executor.execute(runnable);
    }

}
