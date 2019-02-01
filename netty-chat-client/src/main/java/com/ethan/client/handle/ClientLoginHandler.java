package com.ethan.client.handle;

import com.ethan.protocol.command.Packet;
import com.ethan.protocol.command.PacketCodeC;
import com.ethan.request.LoginRequestPacket;
import com.ethan.response.LoginResponsePacket;
import com.ethan.response.MessageResponsePacket;
import com.ethan.utils.LocalDateUtil;
import com.ethan.utils.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

/**
 * @author tmpil9
 * @version 1.0
 * @date 22/01/2019
 */
public class ClientLoginHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("start login to server:-->" + LocalDateUtil.getNowDateTimeStr());

        // create login entity
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket
                .setUserId(UUID.randomUUID().toString())
                .setUsername("Ethan")
                .setPassword("pwd");

        //encode
        // ?????ctx.alloc()
        ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

        // write data
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        // System.out.println(LocalDateUtil.getNowDateTimeStr() + "Client Get Response" + byteBuf.toString());

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        System.out.println("channelRead():-->" + packet);
        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            if (loginResponsePacket.isSuccess()) {
                System.out.println(LocalDateUtil.getNowDateTimeStr() + " login successful!");
                LoginUtil.markAsLogin(ctx.channel());
            } else {
                System.out.println("login failed!" + loginResponsePacket.getReason());
            }
        } else if (packet instanceof MessageResponsePacket) {
            MessageResponsePacket responsePacket = (MessageResponsePacket) packet;
            System.out.println(LocalDateUtil.getNowDateTimeStr() + "capture server's messgae-->" + responsePacket.getMessage());

        }

    }
}
