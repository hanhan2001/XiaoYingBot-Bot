package me.xiaoying.bot.bot;

import me.xiaoying.bot.bot.command.LoginCommand;
import me.xiaoying.bot.bot.listener.LoggerListener;
import me.xiaoying.bot.bot.scheduler.Scheduler;
import me.xiaoying.bot.bot.terminal.Terminal;
import me.xiaoying.bot.core.Xyb;
import me.xiaoying.bot.core.server.Server;
import me.xiaoying.logger.Logger;
import me.xiaoying.logger.LoggerFactory;
import me.xiaoying.logger.event.EventHandle;

import java.io.File;

public class Application {
    private static LoggerFactory loggerFactory;
    private static Logger logger;
    private static Server server;
    private static Scheduler scheduler;
    private static Terminal terminal;

    public static void main(String[] args) {
        loggerFactory = new LoggerFactory();
        logger = loggerFactory.getLogger();

        logger.info("Starting server...");
        initialize();

        // 注册 Logger 事件
        // 为了花哨搞的玩意
        EventHandle.registerEvent(new LoggerListener());
        // Terminal
        terminal = new Terminal();
        terminal.start();
    }

    // 初始化
    public static void initialize() {
        logger.info("Initializing...");

        server = new BotServer();
        scheduler = new Scheduler();

        // 加载插件
        logger.info("Loading plugins...");
        File plugins = new File("./plugins");
        if (!plugins.exists()) plugins.mkdirs();
        server.getPluginManager().loadPlugins(new File("./plugins"));

        // 注册命令
        server.getCommandManager().registerCommand("xiaoyingbot", new LoginCommand("login"));

        Xyb.setServer(server);
    }

    public void shutdown() {
        server.stop();
    }

    public static Logger getLogger() {
        return logger;
    }

    public static Scheduler getScheduler() {
        return scheduler;
    }

    public static LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}