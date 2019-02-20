package com.ethan.server.handle;

import com.ethan.request.JoinGroupRequestPacket;
import com.ethan.response.JoinGroupResponsePacket;
import com.ethan.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @version 1.0
 * @date 20/02/2019
 */
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    public final static JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    private JoinGroupRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket requestPacket) throws Exception {
        // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 添加进去
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.add(ctx.channel());

        // 2. 构造加群响应发送给客户端
        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
        joinGroupResponsePacket.setSuccess(true);
        joinGroupResponsePacket.setGroupId(groupId);
        ctx.channel().writeAndFlush(joinGroupResponsePacket);
    }
}

