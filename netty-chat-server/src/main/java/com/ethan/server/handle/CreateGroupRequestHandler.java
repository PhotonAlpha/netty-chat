package com.ethan.server.handle;

import com.ethan.request.CreateGroupRequestPacket;
import com.ethan.request.LoginRequestPacket;
import com.ethan.response.CreateGroupResponsePacket;
import com.ethan.response.LoginResponsePacket;
import com.ethan.session.Session;
import com.ethan.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @date 01/02/2019
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
       System.out.println("开始创建群:-->" +createGroupRequestPacket);
       List<String> userIdList = createGroupRequestPacket.getUserIdList();
       Set<String> userIdListSet = new HashSet<>(userIdList);
        String managerUserId = SessionUtil.getSession(ctx.channel()).getUserId();
        if (!userIdListSet.contains(managerUserId)) {
            userIdList.add(managerUserId);
        }

       // 1. 创建一个 channel 分组
       ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
       // 2. 筛选出待加入群聊的用户的 channel 和 userName
       List<String> userNameList = userIdList.stream().filter(userId -> SessionUtil.getChannel(userId) != null)
           .map(userId -> {
                Channel channel = SessionUtil.getChannel(userId);
                channelGroup.add(channel);
                return SessionUtil.getSession(channel).getUserName();
           }).collect(Collectors.toList());
       // 3. 创建群聊创建结果的响应
        String groupId = SessionUtil.randomId();
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupId(groupId);
        createGroupResponsePacket.setUserNameList(userNameList);

        // 4. 给每个客户端发送拉群通知
        channelGroup.writeAndFlush(createGroupResponsePacket);
        System.out.print("群创建成功，id 为[" + createGroupResponsePacket.getGroupId() + "], ");
        System.out.println("群里面有：" + createGroupResponsePacket.getUserNameList());

        // 5. 保存群组相关的信息
        SessionUtil.bindChannelGroup(groupId, channelGroup);
    }

    private boolean valid(LoginRequestPacket loginRequest) {
        if ("pwd".equals(loginRequest.getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("LoginRequestHandler channel 被关闭：channelInactive()");
        SessionUtil.unBindSession(ctx.channel());
    }
}
