package com.ethan.response;

import com.ethan.protocol.command.Packet;

import static com.ethan.protocol.command.Command.HEARTBEAT_REQUEST;
import static com.ethan.protocol.command.Command.HEARTBEAT_RESPONSE;

/**
 * @version 1.0
 * @date 21/02/2019
 */
public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
