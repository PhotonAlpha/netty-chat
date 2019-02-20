package com.ethan.client.handle;

import com.ethan.response.LogoutResponsePacket;
import com.ethan.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @version 1.0
 * @date 01/02/2019
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket logoutResponsePacket) throws Exception {
        if (logoutResponsePacket.isSuccess()) {
            System.out.println("退出成功！");
        } else {
            System.err.println("退出失败！");
        }
        SessionUtil.unBindSession(ctx.channel());
    }
}
