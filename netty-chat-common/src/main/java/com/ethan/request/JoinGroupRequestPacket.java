package com.ethan.request;

import com.ethan.protocol.command.Packet;
import lombok.Data;

import static com.ethan.protocol.command.Command.JOIN_GROUP_REQUEST;

/**
 * @version 1.0
 * @date 19/02/2019
 */
@Data
public class JoinGroupRequestPacket extends Packet {
    private String groupId;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_REQUEST;
    }
}
