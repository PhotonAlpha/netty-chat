package com.ethan.server.handle;

import com.ethan.request.LoginRequestPacket;
import com.ethan.response.LoginResponsePacket;
import com.ethan.response.LogoutResponsePacket;
import com.ethan.session.Session;
import com.ethan.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @version 1.0
 * @date 01/02/2019
 */
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestHandler> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestHandler msg) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(logoutResponsePacket);
    }
}
