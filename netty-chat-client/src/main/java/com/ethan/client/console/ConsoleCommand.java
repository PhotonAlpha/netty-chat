package com.ethan.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @version 1.0
 * @date 19/02/2019
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
