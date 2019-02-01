/*
 * System Name         : GEBNexGen
 * Program Id          : netty-chat
 * Author              : tmpil9
 * Date                : 31/01/2019
 * Copyright (c) United Overseas Bank Limited Co.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * United Overseas Bank Limited Co. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * United Overseas Bank Limited Co.
 */

package com.ethan.server.proxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author tmpil9
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
