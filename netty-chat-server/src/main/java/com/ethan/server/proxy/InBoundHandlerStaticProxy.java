package com.ethan.server.proxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @version 1.0
 * @date 31/01/2019
 */
public class InBoundHandlerStaticProxy extends ChannelInboundHandlerAdapter {
    private ChannelInboundHandlerAdapter inboundHandlerAdapter;

    public InBoundHandlerStaticProxy(ChannelInboundHandlerAdapter inboundHandlerAdapter) {
        this.inboundHandlerAdapter = inboundHandlerAdapter;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(inboundHandlerAdapter.getClass().getName() + "channelRead start:-->" +System.currentTimeMillis());
        inboundHandlerAdapter.channelRead(ctx, msg);
        System.out.println(inboundHandlerAdapter.getClass().getName() + "channelRead end:-->" +System.currentTimeMillis());
    }
}
