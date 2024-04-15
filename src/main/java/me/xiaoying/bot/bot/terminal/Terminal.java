package me.xiaoying.bot.bot.terminal;

import me.xiaoying.bot.bot.command.Console;
import me.xiaoying.bot.core.Xyb;

import java.util.Scanner;

/**
 * Terminal
 */
public class Terminal {
    public void start() {
        new Thread(() -> {
            Xyb.getLogger().info("For help, type \"help\" or \"?\"");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String scan = scanner.nextLine();

                boolean result = Xyb.getServer().getCommandManager().dispatch(new Console(), scan);
                if (result)
                    continue;

                Xyb.getLogger().info("Unknown command. Type \"/help\" for help.");
            }
        }).start();
    }
}