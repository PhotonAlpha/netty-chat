package com.ethan.client.console;

import com.ethan.request.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @version 1.0
 * @date 19/02/2019
 */
public class SendToUserConsoleCommand implements ConsoleCommand {
    private static final String USER_ID_DELIMITER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个用户：");
        String userId = scanner.next();
        System.out.print("消息内容：");
        String message = scanner.next();
        channel.writeAndFlush(new MessageRequestPacket(userId, message));
    }
}
