package discorddb;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Manages the thread pooling system that will be used to udpate database files.
 */
class ThreadManagement {

    private static final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(8);

    // Configure ScheduledThreadPoolExecutor
    static {
        executor.setMaximumPoolSize(10);
    }

    /**
     * Execute a Runnable inside the thread
     * @param runnable {@link Runnable} action
     */
    public static void execute(Runnable runnable) {
        executor.execute(runnable);
    }

}
