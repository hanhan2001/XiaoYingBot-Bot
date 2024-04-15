package me.xiaoying.bot.bot.listener;

import me.xiaoying.logger.event.EventHandler;
import me.xiaoying.logger.event.Listener;
import me.xiaoying.logger.event.terminal.TerminalLogEndEvent;
import me.xiaoying.logger.event.terminal.TerminalWantLogEvent;

/**
 * Listener Logger
 */
public class LoggerListener implements Listener {
    private String back = null;

    @EventHandler
    public void onTerminalWantLog(TerminalWantLogEvent event) {
        System.out.print("\r");
        this.back = null;
    }

    @EventHandler
    public void onTerminalLogEnd(TerminalLogEndEvent event) {
        if ("> ".equalsIgnoreCase(this.back))
            return;

        System.out.print("> ");
        this.back = "> ";
    }
}