package com.ethan.request;

import com.ethan.protocol.command.Packet;
import lombok.Data;

import static com.ethan.protocol.command.Command.LIST_GROUP_MEMBERS_REQUEST;

/**
 * @version 1.0
 * @date 19/02/2019
 */
@Data
public class ListGroupMemberRequestPacket extends Packet {
    private String groupId;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_REQUEST;
    }
}
