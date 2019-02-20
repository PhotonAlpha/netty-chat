package com.ethan.client.console;

import com.ethan.request.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @version 1.0
 * @date 19/02/2019
 */
public class LogoutConsoleCommand implements ConsoleCommand {
    private static final String USER_ID_DELIMITER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();

        channel.writeAndFlush(logoutRequestPacket);
    }
}
