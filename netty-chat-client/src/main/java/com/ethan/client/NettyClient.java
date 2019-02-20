package com.ethan.client;

import com.ethan.client.console.ConsoleCommandManager;
import com.ethan.client.console.LoginConsoleCommand;
import com.ethan.client.handle.CreateGroupResponseHandler;
import com.ethan.client.handle.GroupMessageResponseHandler;
import com.ethan.client.handle.JoinGroupResponseHandler;
import com.ethan.client.handle.ListGroupMembersResponseHandler;
import com.ethan.client.handle.LoginResponseHandler;
import com.ethan.client.handle.LoginResponseHandler_Old;
import com.ethan.client.handle.LogoutResponseHandler;
import com.ethan.client.handle.MessageResponseHandler;
import com.ethan.client.handle.QuitGroupResponseHandler;
import com.ethan.codec.PacketDecoder;
import com.ethan.codec.PacketEncoder;
import com.ethan.codec.Spliter;
import com.ethan.request.LoginRequestPacket;
import com.ethan.request.MessageRequestPacket;
import com.ethan.utils.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @date 16/01/2019
 */
public class NettyClient {
    private static final int MAX_RETRY = 5;

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .attr(AttributeKey.newInstance("clientName"), "Netty Client")
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                // open the TCP Heartbeat mechanism
                .option(ChannelOption.SO_KEEPALIVE, true)
                // Nagle algorithm, "true" is closed.
                .option(ChannelOption.TCP_NODELAY, true)
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        System.out.println("initChannel():-->client 启动...");
                        ch.pipeline().addLast(new Spliter());
                        // ch.pipeline().addLast(new FirstClientHandler());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new LogoutResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new CreateGroupResponseHandler());
                        ch.pipeline().addLast(new JoinGroupResponseHandler());
                        ch.pipeline().addLast(new QuitGroupResponseHandler());
                        ch.pipeline().addLast(new ListGroupMembersResponseHandler());
                        ch.pipeline().addLast(new GroupMessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                        // -----------old version----------------------
                        // specify the read and write data
                        // ch.pipeline().addLast(new ClientLoginHandler());
                    }
                });
        connect(bootstrap, "127.0.0.1", 8080, MAX_RETRY);
    }
    /**
     *
     * @description: try to reconnect with time, 1, 2, 4, 8, 16...
     * @date 18/01/2019 11:05 AM
     */
    @SuppressWarnings("Duplicates")
    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("connect success!");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else if (retry == 0) {
                System.out.println("connect times out, descard connect.");
            } else {
                int order = (MAX_RETRY - retry) + 1;
                // the idle of the connect time
                int delay = 1 << order;
                System.out.println(new Date() + ":connect failed. retry " + order + "reconnect...");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    loginConsoleCommand.exec(scanner, channel);
                } else {
                    consoleCommandManager.exec(scanner, channel);
                }
            }
        }).start();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
