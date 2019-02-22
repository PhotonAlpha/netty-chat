package com.ethan.request;

import com.ethan.protocol.command.Packet;

import static com.ethan.protocol.command.Command.HEARTBEAT_REQUEST;

/**
 * @version 1.0
 * @date 21/02/2019
 */
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
