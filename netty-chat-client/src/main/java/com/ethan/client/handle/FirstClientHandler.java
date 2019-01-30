/*
 * System Name         : GEBNexGen
 * Program Id          : netty-chat
 * Author              : tmpil9
 * Date                : 18/01/2019
 * Copyright (c) United Overseas Bank Limited Co.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * United Overseas Bank Limited Co. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * United Overseas Bank Limited Co.
 */

package com.ethan.client.handle;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author tmpil9
 * @version 1.0
 * @date 18/01/2019
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client write out data:-->" + new Date());
        // 1. fetch data
        ByteBuf buffer = getByteBuf(ctx);
        // 2. write data
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + "Client Get Response:-->" +byteBuf.toString(Charset.forName("utf-8")));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        // 1. 获取二进制抽象 Bytebuf
        ByteBuf buffer = ctx.alloc().buffer();
        // 2. prepare data
        byte[] bytes = "Hello Ethan".getBytes(Charset.forName("utf-8"));
        // 3. fill data
        buffer.writeBytes(bytes);
        return buffer;
    }
}
