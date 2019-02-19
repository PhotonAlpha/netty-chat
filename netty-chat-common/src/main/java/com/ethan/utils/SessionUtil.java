package com.ethan.utils;

import com.ethan.attribute.Attributes;
import com.ethan.session.Session;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0
 * @date 19/02/2019
 */
public class SessionUtil {
    private static final Map<String, Channel> userIdChannel = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        userIdChannel.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            userIdChannel.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {
        return userIdChannel.get(userId);
    }
}
