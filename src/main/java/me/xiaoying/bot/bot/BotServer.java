package me.xiaoying.bot.bot;

import me.xiaoying.bot.core.command.CommandManager;
import me.xiaoying.bot.core.command.SimpleCommandManager;
import me.xiaoying.bot.core.plugin.PluginManager;
import me.xiaoying.bot.core.plugin.SimplePluginManager;
import me.xiaoying.bot.core.server.Server;

/**
 * Bot Server
 */
public class BotServer implements Server {
    private final PluginManager pluginManager = new SimplePluginManager(this);
    private final CommandManager commandManager = new SimpleCommandManager();

    @Override
    public String getName() {
        return "BotServer";
    }

    @Override
    public PluginManager getPluginManager() {
        return this.pluginManager;
    }

    @Override
    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    @Override
    public void run() {

    }

    @Override
    public void stop() {
        Application.getLoggerFactory().saveLog();
    }
}