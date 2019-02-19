package com.ethan.client.handle;

import com.ethan.response.MessageResponsePacket;
import com.ethan.utils.LocalDateUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @version 1.0
 * @date 01/02/2019
 */
public class MessageResponseHandler_Old extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket responsePacket) throws Exception {
        System.out.println(LocalDateUtil.getNowDateTimeStr() + "capture server's messgae-->" + responsePacket.getMessage());
    }
}
