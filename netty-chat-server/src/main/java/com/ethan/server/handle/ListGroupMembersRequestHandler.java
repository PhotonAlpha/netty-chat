package com.ethan.server.handle;

import com.ethan.request.ListGroupMemberRequestPacket;
import com.ethan.response.ListGroupMemberResponsePacket;
import com.ethan.session.Session;
import com.ethan.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @date 20/02/2019
 */
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMemberRequestPacket> {
    public final static ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();

    private ListGroupMembersRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMemberRequestPacket requestPacket) throws Exception {
        // 1. 获取群的 ChannelGroup
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        // 2. 遍历群成员的 channel，对应的 session，构造群成员的信息
        List<Session> sessionList = channelGroup.stream().map(channel -> SessionUtil.getSession(channel)).collect(Collectors.toList());

        // 3. 构建获取成员列表响应写回到客户端
        ListGroupMemberResponsePacket listGroupMemberResponsePacket = new ListGroupMemberResponsePacket();
        listGroupMemberResponsePacket.setGroupId(groupId);
        listGroupMemberResponsePacket.setSessionList(sessionList);
        ctx.channel().writeAndFlush(listGroupMemberResponsePacket);
    }
}
