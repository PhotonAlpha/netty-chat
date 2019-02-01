package com.ethan.server.handle;

import com.ethan.protocol.command.Packet;
import com.ethan.protocol.command.PacketCodeC;
import com.ethan.request.LoginRequestPacket;
import com.ethan.request.MessageRequestPacket;
import com.ethan.response.LoginResponsePacket;
import com.ethan.response.MessageResponsePacket;
import com.ethan.utils.LocalDateUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author tmpil9
 * @version 1.0
 * @date 22/01/2019
 */
public class ServerLoginHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Listening client request-->" + LocalDateUtil.getNowDateTimeStr());

        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginRequestPacket) {
            // login process
            LoginRequestPacket loginRequest = (LoginRequestPacket) packet;
            System.out.println(loginRequest);
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            if (valid(loginRequest)) {
                loginResponsePacket.setSuccess(true);
                System.out.println("channelRead():--> login successful!" + LocalDateUtil.getNowDateTimeStr());
            } else {
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("invalid username or password");
                System.out.println("login failed!");
            }

            // login response
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        } else if (packet instanceof MessageRequestPacket) {
            // message business
            MessageRequestPacket messageRequest = (MessageRequestPacket) packet;
            System.out.println("received client message:-->" + messageRequest.getMessage());

            MessageResponsePacket messageResponse = new MessageResponsePacket();
            messageResponse.setMessage("I Received [" + messageRequest.getMessage() + "] Response ");
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponse);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
    }

    private boolean valid(LoginRequestPacket loginRequest) {
        if ("pwd".equals(loginRequest.getPassword())) {
            return true;
        }
        return false;
    }
}
