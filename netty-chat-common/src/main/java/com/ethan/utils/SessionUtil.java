package com.ethan.utils;

import com.ethan.attribute.Attributes;
import com.ethan.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0
 * @date 19/02/2019
 */
public class SessionUtil {
    private SessionUtil() {
    }

    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();

    private static final Map<String, ChannelGroup> groupIdChannelGroupMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            userIdChannelMap.remove(getSession(channel).getUserId());
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
        return userIdChannelMap.get(userId);
    }

    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup) {
        groupIdChannelGroupMap.put(groupId, channelGroup);
    }
    public static ChannelGroup getChannelGroup(String groupId) {
        return groupIdChannelGroupMap.get(groupId);
    }


    public static Map<String, Channel> getUserIdChannelMap() {
        return userIdChannelMap;
    }

    public static Map<String, ChannelGroup> getGroupIdChannelGroupMap() {
        return groupIdChannelGroupMap;
    }
}
