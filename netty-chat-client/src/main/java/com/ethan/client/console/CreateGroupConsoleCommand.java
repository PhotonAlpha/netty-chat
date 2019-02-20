package com.ethan.client.console;

import com.ethan.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @version 1.0
 * @date 19/02/2019
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {
    private static final String USER_ID_DELIMITER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        System.out.print("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");
        String userIds = scanner.next();
        createGroupRequestPacket.setUserIdList(Arrays.asList(userIds.split(USER_ID_DELIMITER)));
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
