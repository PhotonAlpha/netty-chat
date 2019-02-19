package com.ethan.server.handle;

import com.ethan.protocol.command.PacketCodeC;
import com.ethan.request.MessageRequestPacket;
import com.ethan.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @version 1.0
 * @date 01/02/2019
 */
public class MessageRequestHandler_Old extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    @SuppressWarnings("Duplicates")
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequest) throws Exception {
            // message business
            System.out.println("received client message:-->" + messageRequest.getMessage());

            MessageResponsePacket messageResponse = new MessageResponsePacket();
            messageResponse.setMessage("I Received [" + messageRequest.getMessage() + "] Response ");
            // ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponse);
            ctx.channel().writeAndFlush(messageResponse);
    }
}