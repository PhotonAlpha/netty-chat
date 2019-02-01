package com.ethan.server.proxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @version 1.0
 * @date 31/01/2019
 */
public class OutBoundHandlerStaticProxy extends ChannelOutboundHandlerAdapter {
    private ChannelOutboundHandlerAdapter outboundHandlerAdapter;

    public OutBoundHandlerStaticProxy(ChannelOutboundHandlerAdapter outboundHandlerAdapter) {
        this.outboundHandlerAdapter = outboundHandlerAdapter;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println(outboundHandlerAdapter.getClass().getName() + "OutBoundHandler write Start:-->" + System.currentTimeMillis());
        outboundHandlerAdapter.write(ctx, msg, promise);
        System.out.println(outboundHandlerAdapter.getClass().getName() + "OutBoundHandler write End:-->" + System.currentTimeMillis());
    }
}
