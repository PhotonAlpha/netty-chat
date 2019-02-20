package com.ethan.server.handle;

import com.ethan.request.GroupMessageRequestPacket;
import com.ethan.response.GroupMessageResponsePacket;
import com.ethan.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @version 1.0
 * @date 20/02/2019
 */
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    public final static GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    private GroupMessageRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket requestPacket) throws Exception {
        // 1.拿到 groupId 构造群聊消息的响应
        String groupId = requestPacket.getToGroupId();

        GroupMessageResponsePacket messageResponsePacket = new GroupMessageResponsePacket();
        messageResponsePacket.setFromGroupId(groupId);
        messageResponsePacket.setFromUser(SessionUtil.getSession(ctx.channel()));
        messageResponsePacket.setMessage(requestPacket.getMessage());

        // 2. 拿到群聊对应的 channelGroup，写到每个客户端
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.writeAndFlush(messageResponsePacket);

    }
}
