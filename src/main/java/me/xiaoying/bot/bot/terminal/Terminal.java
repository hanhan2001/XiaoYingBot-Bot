package me.xiaoying.bot.bot.terminal;

import me.xiaoying.bot.bot.listener.LoggerListener;
import me.xiaoying.bot.core.Xyb;
import me.xiaoying.logger.event.EventHandle;

import java.util.Scanner;

/**
 * Terminal
 */
public class Terminal {
    private String lastLog = null;

    public void start() {
        new Thread(() -> {
            Xyb.getLogger().info("For help, type \"help\" or \"?\"");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String scan = scanner.nextLine();

//                boolean result = AuthorizeApplication.getCommandService().performCommand(scan);
//                if (result)
//                    continue;

                Xyb.getLogger().info("Unknown command. Type \"/help\" for help.");
            }
        }).start();
    }
}