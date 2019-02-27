package com.ethan.client.handle;

import com.ethan.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @version 1.0
 * @date 21/02/2019
 */
public class HeartBeatResponseHandler extends SimpleChannelInboundHandler<HeartBeatResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HeartBeatResponsePacket responsePacket) throws Exception {
        System.out.println(responsePacket.getCommand());
    }
}
