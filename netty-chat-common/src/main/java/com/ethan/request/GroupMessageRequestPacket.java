package com.ethan.request;

import com.ethan.protocol.command.Packet;
import lombok.Data;

import static com.ethan.protocol.command.Command.GROUP_MESSAGE_REQUEST;
import static com.ethan.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @version 1.0
 * @date 23/01/2019
 */
@Data
public class GroupMessageRequestPacket extends Packet {
    private String toGroupId;
    private String message;

    public GroupMessageRequestPacket(String toGroupId, String message) {
        this.toGroupId = toGroupId;
        this.message = message;
    }

    public GroupMessageRequestPacket(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }
}
