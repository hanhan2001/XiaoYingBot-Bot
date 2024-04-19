package me.xiaoying.bot.bot.command;

import me.xiaoying.bot.bot.Application;
import me.xiaoying.bot.bot.LoginSolver;
import me.xiaoying.bot.bot.utils.LongUtil;
import me.xiaoying.bot.core.Xyb;
import me.xiaoying.bot.core.command.Command;
import me.xiaoying.bot.core.command.CommandSender;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.auth.BotAuthorization;
import net.mamoe.mirai.utils.BotConfiguration;

/**
 * Command Login
 */
public class LoginCommand extends Command {
    public LoginCommand(String name) {
        super(name, "login bot", "/login qq password [protocol]", null);
    }

    @Override
    public void execute(CommandSender sender, String[] strings) {
        if (!(sender instanceof Console))
            return;

        // login qq password
        if (strings == null || strings.length < 2) {
            Xyb.getLogger().info(this.getUsage());
            return;
        }

        long account;
        String password = null;
        if (!LongUtil.isLong(strings[1])) {
            Xyb.getLogger().info(this.getUsage());
            return;
        }

        BotConfiguration.MiraiProtocol protocol = BotConfiguration.MiraiProtocol.ANDROID_PAD;
        switch (strings[0].toUpperCase()) {
            case "ANDROID_PHONE": {
                protocol = BotConfiguration.MiraiProtocol.ANDROID_PHONE;
                break;
            }
            case "ANDROID_PAD": {
                protocol = BotConfiguration.MiraiProtocol.ANDROID_PAD;
                break;
            }
            case "ANDROID_WATCH": {
                protocol = BotConfiguration.MiraiProtocol.ANDROID_WATCH;
                break;
            }
            case "IPAD": {
                protocol = BotConfiguration.MiraiProtocol.IPAD;
                break;
            }
            case "MACOS": {
                protocol = BotConfiguration.MiraiProtocol.MACOS;
                break;
            }
        }
        account = Long.parseLong(strings[1]);
        if (strings.length == 3)
            password = strings[2];

        Bot bot;

        if (protocol == BotConfiguration.MiraiProtocol.ANDROID_WATCH)
            bot = BotFactory.INSTANCE.newBot(account, BotAuthorization.byQRCode());
        else
            bot = BotFactory.INSTANCE.newBot(account, password);

        // 设备信息存储位置
        bot.getConfiguration().fileBasedDeviceInfo("./devices.json");
        bot.getConfiguration().setLoginSolver(new LoginSolver());

        // 取消日志
        bot.getConfiguration().noBotLog();
        bot.getConfiguration().noNetworkLog();

        // 列表缓存
        bot.getConfiguration().enableContactCache();

        // 协议选择

        bot.getConfiguration().setProtocol(protocol);

        // 设置https协议，已解决SSl peer shut down incorrectly异常
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2,SSLv3");

        bot.login();

        Application.getScheduler().scheduleAsyncDelayedTask(null, bot::join);
    }
}