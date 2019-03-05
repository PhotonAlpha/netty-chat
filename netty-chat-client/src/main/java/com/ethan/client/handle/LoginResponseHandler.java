package com.ethan.client.handle;

import com.ethan.response.LoginResponsePacket;
import com.ethan.session.Session;
import com.ethan.utils.LocalDateUtil;
import com.ethan.utils.LoginUtil;
import com.ethan.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @version 1.0
 * @date 01/02/2019
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    @SuppressWarnings("Duplicates")
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LoginResponseHandler start login to server:-->" + LocalDateUtil.getNowDateTimeStr());

        // create login entity
        /*LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket .setUserId(UUID.randomUUID().toString())
                .setUsername("Ethan")
                .setPassword("pwd");*/

        // 2.encode
        // ?????ctx.alloc()
        // ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

        // 3.write data
        // ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        String userId = loginResponsePacket.getUserId();
        String userName = loginResponsePacket.getUserName();

        if (loginResponsePacket.isSuccess()) {
            System.out.println("[" + userName + "]登录成功，你的userId 为: " + userId);
            SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
        } else {
            System.out.println("login failed!" + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭!");
        // SessionUtil.getUserIdChannelMap().size();
        // SessionUtil.getGroupIdChannelGroupMap().size();
        System.out.println("user length :" +SessionUtil.getUserIdChannelMap().size());
        System.out.println("group length :" +SessionUtil.getGroupIdChannelGroupMap().size());
        super.channelInactive(ctx);
    }
}
