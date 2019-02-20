package com.ethan.server.handle;

import com.ethan.request.QuitGroupRequestPacket;
import com.ethan.response.QuitGroupResponsePacket;
import com.ethan.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @version 1.0
 * @date 20/02/2019
 */
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    public final static QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    private QuitGroupRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket requestPacket) throws Exception {
        // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 移除
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.remove(ctx.channel());

        // 2. 构造退群响应发送给客户端
        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();
        quitGroupResponsePacket.setGroupId(groupId);
        quitGroupResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(quitGroupResponsePacket);
    }
}
