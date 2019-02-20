package com.ethan.client.console;

import com.ethan.request.ListGroupMemberRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @version 1.0
 * @date 19/02/2019
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        ListGroupMemberRequestPacket listGroupMemberRequestPacket = new ListGroupMemberRequestPacket();

        System.out.print("输入 groupId，获取群成员列表：");
        String groupId = scanner.next();

        listGroupMemberRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(listGroupMemberRequestPacket);
    }
}
