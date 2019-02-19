package com.ethan.server.handle;

import com.ethan.protocol.command.PacketCodeC;
import com.ethan.request.LoginRequestPacket;
import com.ethan.response.LoginResponsePacket;
import com.ethan.utils.LocalDateUtil;
import com.ethan.utils.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @version 1.0
 * @date 01/02/2019
 */
public class LoginRequestHandler_Old extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    @SuppressWarnings("Duplicates")
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequest) throws Exception {
        // login process
        System.out.println(loginRequest);
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequest.getVersion());
        if (valid(loginRequest)) {
            loginResponsePacket.setSuccess(true);
            System.out.println("channelRead():--> login successful!" + LocalDateUtil.getNowDateTimeStr());
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("invalid username or password");
            System.out.println("login failed!");
        }

        // login response
        // ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequest) {
        if ("pwd".equals(loginRequest.getPassword())) {
            return true;
        }
        return false;
    }
}
