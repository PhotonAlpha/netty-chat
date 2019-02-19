package com.ethan.server.handle;

import com.ethan.request.MessageRequestPacket;
import com.ethan.response.MessageResponsePacket;
import com.ethan.session.Session;
import com.ethan.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @version 1.0
 * @date 01/02/2019
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    @SuppressWarnings("Duplicates")
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequest) throws Exception {
        // message business
        System.out.println("MessageRequestHandler Server Receive Message:" + messageRequest.getMessage());

        // 1.拿到消息发送方的会话信息
        Session session = SessionUtil.getSession(ctx.channel());
        // 2.通过消息发送方的会话信息构造要发送的消息
        MessageResponsePacket messageResponse = new MessageResponsePacket();
        messageResponse.setFromUserId(session.getUserId());
        messageResponse.setFromUserName(session.getUserName());
        // 获取收到的信息
        messageResponse.setMessage(messageRequest.getMessage());
        // ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponse);
        // 3.拿到消息接收方的 channel
        Channel toUserChannel = SessionUtil.getChannel(messageRequest.getToUserId());

        // 4.将消息发送给消息接收方
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            messageResponse.setReceivable(true);
            toUserChannel.writeAndFlush(messageResponse);

            MessageResponsePacket userFeedback = new MessageResponsePacket();
            userFeedback.setMessage("发送成功!");
            userFeedback.setFromUserId(session.getUserId());
            userFeedback.setFromUserName(session.getUserName());
            Channel fromUserChannel = SessionUtil.getChannel(session.getUserId());
            fromUserChannel.writeAndFlush(userFeedback);
        } else {
            System.err.println("[" + messageRequest.getToUserId() + "] 不在线，发送失败!");

            MessageResponsePacket userFeedback = new MessageResponsePacket();
            userFeedback.setMessage("[" + messageRequest.getToUserId() + "] 不在线，发送失败!");
            userFeedback.setFromUserId(session.getUserId());
            userFeedback.setFromUserName(session.getUserName());
            Channel fromUserChannel = SessionUtil.getChannel(session.getUserId());
            fromUserChannel.writeAndFlush(userFeedback);
        }
        // ctx.channel().writeAndFlush(messageResponse);
    }
}
