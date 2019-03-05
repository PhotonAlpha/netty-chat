package com.ethan.client.handle;

import com.ethan.request.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @date 21/02/2019
 */
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {
    private static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HeartBeatTimerHandler channelRegistered");
        scheduleSendHeartBeat(ctx);
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HeartBeatTimerHandler channelActive()");
        // scheduleSendHeartBeat(ctx);
        // super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {
            System.out.println("Client HeartBeat ......" +ctx.channel().isActive());
            if (ctx.channel().isActive()) {
                ctx.writeAndFlush(new HeartBeatRequestPacket());
                scheduleSendHeartBeat(ctx);
            }
        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HeartBeatTimerHandler 客户端连接被关闭!");
        super.channelInactive(ctx);
    }
}
