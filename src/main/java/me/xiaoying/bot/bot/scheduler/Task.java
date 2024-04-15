package me.xiaoying.bot.bot.scheduler;

import me.xiaoying.bot.core.plugin.JavaPlugin;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * Scheduler Task
 */
public class Task {
    private final JavaPlugin plugin;
    private final TaskType type;
    private Date runTime;
    private final Runnable runnable;
    private long delay = 0;
    private boolean finish = false;
    private ScheduledFuture<?> scheduledFuture;

    public Task(JavaPlugin plugin, TaskType type, Runnable runnable) {
        this.plugin = plugin;
        this.type = type;
        this.runnable = runnable;
        this.runTime = new Date();
    }

    public Task(JavaPlugin plugin, TaskType type, Runnable runnable, long delay) {
        this.plugin = plugin;
        this.type = type;
        this.runnable = runnable;
        this.runTime = new Date();
        this.delay = delay;
    }

    public JavaPlugin getPlugin() {
        return this.plugin;
    }

    public boolean isFinish() {
        return this.finish;
    }

    public TaskType getType() {
        return this.type;
    }

    public boolean ready() {
        return new Date().getTime() - this.runTime.getDate() >= this.delay;
    }

    public void setRunTime(Date runtime) {
        this.runTime = runtime;
    }

    public void setScheduledFuture(ScheduledFuture<?> scheduledFuture) {
        this.scheduledFuture = scheduledFuture;
    }

    public ScheduledFuture<?> getScheduledFuture() {
        return this.scheduledFuture;
    }

    public void run() {
        this.runnable.run();
        this.finish = true;
    }

    public enum TaskType {
        SYNC_REPEAT,
        SYNC_RUN,
        ASYNC_REPEAT,
        ASYNC_RUN;
    }
}