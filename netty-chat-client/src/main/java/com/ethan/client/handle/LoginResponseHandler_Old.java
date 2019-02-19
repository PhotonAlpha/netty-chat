package com.ethan.client.handle;

import com.ethan.protocol.command.PacketCodeC;
import com.ethan.request.LoginRequestPacket;
import com.ethan.response.LoginResponsePacket;
import com.ethan.utils.LocalDateUtil;
import com.ethan.utils.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @version 1.0
 * @date 01/02/2019
 */
public class LoginResponseHandler_Old extends SimpleChannelInboundHandler<LoginResponsePacket> {
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
        if (loginResponsePacket.isSuccess()) {
            System.out.println(LocalDateUtil.getNowDateTimeStr() + " login successful!");
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            System.out.println("login failed!" + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭!");
        super.channelInactive(ctx);
    }
}
