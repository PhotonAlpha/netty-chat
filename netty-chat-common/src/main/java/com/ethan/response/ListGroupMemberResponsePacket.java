package com.ethan.response;

import com.ethan.protocol.command.Packet;
import com.ethan.session.Session;
import lombok.Data;

import java.util.List;

import static com.ethan.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

/**
 * @version 1.0
 * @date 19/02/2019
 */
@Data
public class ListGroupMemberResponsePacket extends Packet {
    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
