package me.xiaoying.bot.bot;

import me.xiaoying.bot.core.entity.*;

/**
 * Bot
 */
public class XiaoYingBot implements Bot {
    private final net.mamoe.mirai.Bot miriaBot;
    private final long id;
    private final String name;

    public XiaoYingBot(net.mamoe.mirai.Bot miraiBot) {
        this.miriaBot = miraiBot;
        this.id = miraiBot.getId();
        this.name = miraiBot.getNick();
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Friend getFriend(long id) {
        net.mamoe.mirai.contact.Friend friend;
        if ((friend = this.miriaBot.getFriend(id)) == null)
            return null;
        return new Friend(this, id, friend.getNick());
    }

    public Stranger getStranger(long id) {
        net.mamoe.mirai.contact.Stranger stranger;
        if ((stranger = this.miriaBot.getStranger(id)) == null)
            return null;
        return new Stranger(this, id, stranger.getNick());
    }

    public Group getGroup(long id) {
        net.mamoe.mirai.contact.Group group;
        if ((group = this.miriaBot.getGroup(id)) == null)
            return null;
        return new Group(this, id, group.getName());
    }

    public boolean isOnline() {
        return this.miriaBot.isOnline();
    }

    public void sendMessage(Group group, String message) {
        net.mamoe.mirai.contact.Group g = this.miriaBot.getGroup(group.getId());
        assert g != null;
        g.sendMessage(message);
    }

    public void sendMessage(User user, String message) {
        if (user == null)
            return;

        net.mamoe.mirai.contact.User u = null;
        if (user instanceof Friend) {
            net.mamoe.mirai.contact.Friend friend = this.miriaBot.getFriend(user.getId());
            u = friend;
        }
        if (user instanceof Stranger) {
            net.mamoe.mirai.contact.Stranger stranger = this.miriaBot.getStranger(user.getId());
            assert stranger != null;
            u = stranger;
        }
        if (u == null)
            return;
        u.sendMessage(message);
    }
}