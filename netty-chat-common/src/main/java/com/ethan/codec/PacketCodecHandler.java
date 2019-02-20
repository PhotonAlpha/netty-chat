package com.ethan.codec;

import com.ethan.protocol.command.Packet;
import com.ethan.protocol.command.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * @version 1.0
 * @date 20/02/2019
 */
@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, Packet> {
    public static final PacketCodecHandler INSTANCE = new PacketCodecHandler();

    private PacketCodecHandler() {

    }


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        System.out.println("PacketCodecHandler:--> call decode!");
        out.add(PacketCodeC.INSTANCE.decode(byteBuf));
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, List<Object> out) throws Exception {
        System.out.println("PacketCodecHandler:--> call encode!");
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        PacketCodeC.INSTANCE.encode(byteBuf, packet);
        out.add(byteBuf);
    }

}
