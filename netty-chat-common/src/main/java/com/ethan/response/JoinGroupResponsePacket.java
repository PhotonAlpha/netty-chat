package com.ethan.response;

import com.ethan.protocol.command.Packet;
import lombok.Data;

import static com.ethan.protocol.command.Command.JOIN_GROUP_RESPONSE;

/**
 * @version 1.0
 * @date 19/02/2019
 */
@Data
public class JoinGroupResponsePacket extends Packet {
    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_RESPONSE;
    }
}
