package com.ethan.server.handle;

import com.ethan.request.LoginRequestPacket;
import com.ethan.response.LoginResponsePacket;
import com.ethan.session.Session;
import com.ethan.utils.LocalDateUtil;
import com.ethan.utils.LoginUtil;
import com.ethan.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @version 1.0
 * @date 01/02/2019
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    @SuppressWarnings("Duplicates")
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequest) throws Exception {
        // login process
        // System.out.println(loginRequest);
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequest.getVersion());
        loginResponsePacket.setUserName(loginRequest.getUsername());

        if (valid(loginRequest)) {
            loginResponsePacket.setSuccess(true);
            loginResponsePacket.setUserId(SessionUtil.randomId());
            System.out.println(loginResponsePacket);
            System.out.println("[" + loginRequest.getUsername() + "]登录成功");

            SessionUtil.bindSession(new Session(loginResponsePacket.getUserId(), loginResponsePacket.getUserName()), ctx.channel());

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

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        final String username = SessionUtil.getSession(ctx.channel()).getUserName();
        System.err.println("LoginRequestHandler channel 被关闭：channelInactive() 用户:"+username+"下线");
        SessionUtil.unBindSession(ctx.channel());
    }
}
