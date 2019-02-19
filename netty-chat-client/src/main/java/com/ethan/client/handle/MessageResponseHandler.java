package com.ethan.client.handle;

import com.ethan.response.MessageResponsePacket;
import com.ethan.utils.LocalDateUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @version 1.0
 * @date 01/02/2019
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket responsePacket) throws Exception {
        if (responsePacket.isReceivable()) {
            String fromUserId = responsePacket.getFromUserId();
            String fromUserName = responsePacket.getFromUserName();
            System.out.println("来自:" + fromUserId + ":" + fromUserName + " 的消息->" + responsePacket.getMessage());
        } else {
            System.out.println(responsePacket.getMessage());
        }

    }
}
